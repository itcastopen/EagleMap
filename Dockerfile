FROM openjdk:8-jdk
LABEL maintainer="研究院研发组 <research@itcast.cn>"
 
# 时区修改为东八区
ENV TZ=Asia/Shanghai
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
WORKDIR /app
EXPOSE 8484
ADD ./deploy/eagle-map-server.tar.gz /app
ENTRYPOINT ["java","-server","-Xms512m","-Xmx512m","-Xmn256m","-XX:MetaspaceSize=64m","-XX:MaxMetaspaceSize=128m","-XX:-OmitStackTraceInFastThrow","-XX:+HeapDumpOnOutOfMemoryError","-XX:HeapDumpPath=/app/eagle-map-server/logs/java_heapdump.hprof","-XX:-UseLargePages","-Deagle.home=/app/eagle-map-server","-jar","/app/eagle-map-server/target/eagle-map-server.jar","--spring.config.additional-location=/app/eagle-map-server/conf/","--logging.config=/app/eagle-map-server/conf/logback.xml","--server.max-http-header-size=524288"]