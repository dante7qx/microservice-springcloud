FROM dante2012/java:oraclejre-1.8.0_212 

LABEL MAINTAINER="sunchao.zh <dante@dante7qx@126.com>"

ENV JAVA_OPTS="-Xms1024m -Xmx1024m -XX:MetaspaceSize=64m -XX:MaxMetaspaceSize=128m"

ARG apUser=spirit
ARG logHome=/home/ap/logs

RUN set -eux; \
    groupadd -r ${apUser} --gid=1000; \
    useradd -r -g ${apUser} --uid=1000 ${apUser}; \
    mkdir -p ${logHome}; \
    chown -R ${apUser}:${apUser} ${logHome}

COPY target/micro-kubernetes-provider-0.0.1-SNAPSHOT.jar app.jar

USER ${apUser}

EXPOSE 8080

VOLUME ["${logHome}"]

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]