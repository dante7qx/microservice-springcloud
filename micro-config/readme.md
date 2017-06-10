Encryption and Decryption（加密和解密）

对称加密

1、用java Cryptography Extension (JCE) 替换
$JAVA_HOME/jre/lib/security下的 local_policy.jar、US_export_policy.jar

2、curl localhost:8888/encrypt -d mysecret，获得结果 682bc583f4641835fa2db009355293665d2647dade3375c0ee201de2a49f7bda
	localhost:8888 -> Config Server
	mysecret -> 要加密的key

3、在Config Server中配置
	encrypt:
	  key: mysecret
	  
4、Config Client中的 mysecret 的值就是 '{cipher}682bc583f4641835fa2db009355293665d2647dade3375c0ee201de2a49f7bda'
	注意：当使用.properties时，不要加'',即 {cipher}682bc583f4641835fa2db009355293665d2647dade3375c0ee201de2a49f7bda

5、解密
	curl localhost:8888/decrypt -d 7cf98d80b269c31df3f95b27469b23d160750ea54fa63e512dc6a1dc9e534f64

非对称加密
	1、适用JDK的keytool工具生成Key Store
		 -certreq            生成证书请求
		 -changealias        更改条目的别名
		 -delete             删除条目
		 -exportcert         导出证书
		 -genkeypair         生成密钥对
		 -genseckey          生成密钥
		 -gencert            根据证书请求生成证书
		 -importcert         导入证书或证书链
		 -importpass         导入口令
		 -importkeystore     从其他密钥库导入一个或所有条目
		 -keypasswd          更改条目的密钥口令
		 -list               列出密钥库中的条目
		 -printcert          打印证书内容
		 -printcertreq       打印证书请求的内容
		 -printcrl           打印 CRL 文件的内容
		 -storepasswd        更改密钥库的存储口令

在命令行中执行，会生成 server.jks
keytool -genkeypair -alias mytestkey -keyalg RSA \
  -dname "CN=Web Server,OU=Unit,O=Organization,L=City,S=State,C=US" \
  -keypass changeme -keystore server.jks -storepass letmein
  	
  	2、在Config Server中配置
	  	encrypt:
		  keyStore:
		    location: classpath:/server.jks
		    password: letmein
		    alias: mytestkey
		    secret: changeme
	3、接下来的步骤同对称加密

自动刷新加载
	1、在Config Client中加入注解 @RefreshScope
	2、Post请求 Config Server 的 /refresh

加载远程配置
	/{name}/{profile}/{label}
	"name" = ${spring.application.name}
	"profile" = ${spring.profiles.active} (actually Environment.getActiveProfiles())
	"label" = "master"
	
配置服务与注册中心联用
	1、Config Client，设置 spring.cloud.config.discovery.enabled=true
	2、配置Config Server，spring.cloud.config.discovery.serviceId=Config Server 的 Spring application name
