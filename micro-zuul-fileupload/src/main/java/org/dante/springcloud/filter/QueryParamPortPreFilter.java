package org.dante.springcloud.filter;

import static com.netflix.zuul.context.RequestContext.getCurrentContext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

import javax.servlet.http.HttpServletRequest;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.dante.springcloud.util.RequestParamterUtils;
import org.dante.springcloud.vo.ReqParamVO;
import org.dante.springcloud.wrapper.ContentCachingRequestWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

/**
 * @author Spencer Gibb
 */
public class QueryParamPortPreFilter extends ZuulFilter {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(QueryParamPortPreFilter.class);
	private static final String CONTENT_DISPOSITION = "Content-Disposition: form-data;";
	private static final String CONTENT_TYPE = "Content-Type:";
	private static final String FILENAME_KEY = "filename=";
	private static final String PARAMNAME_KEY = "name=";

	public int filterOrder() {
		// run after PreDecorationFilter
		return 0;
	}

	public String filterType() {
		return "pre";
	}

	@Override
	public boolean shouldFilter() {
		return true;
	}

	public Object run() {
		RequestContext ctx = getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		final String method = request.getMethod();
        if (HttpMethod.GET.matches(method)) {
        	String parameters = RequestParamterUtils.getRequestQueryString(request);
			System.out.println(parameters);
        } else if (HttpMethod.POST.matches(method)) {
        	try {
	        	if(ServletFileUpload.isMultipartContent(request)) {
	        		ContentCachingRequestWrapper requestWrapper = (ContentCachingRequestWrapper) request;
					BufferedReader reader = new BufferedReader(new StringReader(new String(requestWrapper.getBody(), "UTF-8")));
					ReqParamVO ReqParamVO = extractReqParam(reader);
					System.out.println(ReqParamVO);
	            }  else {
	            	String parameters = RequestParamterUtils.getRequestPostStr(request);
	            	System.out.println(parameters);
	            }
        	} catch (Exception e) {
        		LOGGER.error("Fetch Post request error.", e);
			}
        }
		/*
		// put the serviceId in `RequestContext`
		String port = request.getParameter("port");
		try {
			LOGGER.info("request port is {}", port);
			URL url = UriComponentsBuilder.fromUri(ctx.getRouteHost().toURI())
					.port(new Integer(port))
					.build().toUri().toURL();
			ctx.setRouteHost(url);
		} catch (Exception e) {
			HttpServletResponse response = ctx.getResponse();
			try {
				response.setContentType("text/html;charset=utf-8");
				response.setCharacterEncoding("UTF-8");
				PrintWriter pw = response.getWriter();
				pw.write("<h2>您的访问不合法，请检查！</h2>");
				pw.flush();
				pw.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
		//	ReflectionUtils.rethrowRuntimeException(e);
		}
		*/
		return null;
	}
	
	private ReqParamVO extractReqParam(BufferedReader reader) {
		StringBuilder reqParambuilder = new StringBuilder();
		StringBuilder valBuilder = new StringBuilder();
    	StringBuilder fileBuilder = new StringBuilder();
		try {
	    	String line = reader.readLine();
	    	boolean formData = false;
	    	boolean fileData = false;
	    	String tmpLine = null;
	    	while(line != null) {
	    		if(line.startsWith(CONTENT_DISPOSITION)) {
	    			formData = true;
	    			fileData = false;
	    			tmpLine = line;
	    			if(line.indexOf(FILENAME_KEY) == -1) {
	    				reqParambuilder.append(extractParamName(line, PARAMNAME_KEY)).append("=");
	    			} 
	    			line = reader.readLine();
	    			continue;
	    		}
	    		if(line.startsWith(CONTENT_TYPE)) {
	    			fileData = true;
	    			fileBuilder.append(extractParamName(tmpLine, PARAMNAME_KEY)).append("=").append(extractParamName(tmpLine, FILENAME_KEY));
	    			line = reader.readLine(); 
	    			continue;
	    		}
	    		if("".equals(line) || fileData) {
	    			line = reader.readLine();
	    			continue;
	    		}
	    		if(formData && !fileData) {
	    			reqParambuilder.append(line).append("&");
	    			valBuilder.append(line).append("&");
	    			formData = false;
	    		}
	    		line = reader.readLine();
	    	}
	    	reqParambuilder.append(fileBuilder);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(reader != null) {
					reader.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return new ReqParamVO(reqParambuilder.toString(), valBuilder.toString(), fileBuilder.toString()); 
	}
	
	private String extractParamName(String contentDisposition, String key) {
		if (contentDisposition == null) {
			return null;
		}
		int startIndex = contentDisposition.indexOf(key);
		if (startIndex == -1) {
			return null;
		}
		String filename = contentDisposition.substring(startIndex + key.length());
		if (filename.startsWith("\"")) {
			int endIndex = filename.indexOf("\"", 1);
			if (endIndex != -1) {
				return filename.substring(1, endIndex);
			}
		}
		else {
			int endIndex = filename.indexOf(";");
			if (endIndex != -1) {
				return filename.substring(0, endIndex);
			}
		}
		return filename;
	}
}
