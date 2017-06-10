package com.hnasys.boot.web;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import java.net.URI;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BootWebApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void contextLoads() {
	}

	@Test
	public void homePage() {
		try {
			mockMvc.perform(get("/")).andExpect(content().string(containsString("欢迎来到首页")));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void login() throws Exception {
		mockMvc.perform(get("/login")).andExpect(content().string(containsString("欢迎登陆")));
	}

	@Test
	public void toLogin() throws Exception {
		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		params.set("username", "但丁");
		params.set("password", "dx72kds");
		mockMvc.perform(post(new URI("/tologin")).params(params))
				.andExpect(content().string(containsString("<label>但丁</label>，欢迎来到首页!")));
	}
}
