FROM java:8

LABEL author="dante"
LABEL email="sunchao.0129@163.com"

ENV JAVA_OPTS="-Xms1g -Xmx1g"
ENV TZ=Asia/Shanghai
ENV APPS_HOME=/AppServer

RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone \
	&& mkdir -p $APPS_HOME/ \
	&& rm /bin/sh && ln -s /bin/bash /bin/sh 

WORKDIR $APPS_HOME/	 
	 
ADD micro-eureka-k8s-0.0.1-SNAPSHOT.jar app.jar	 
ADD init.sh init.sh

RUN sh -c 'touch app.jar'

EXPOSE 8761

ENTRYPOINT ["/bin/bash","-c","source init.sh"] 


