FROM openjdk:17

ENV JAVA_OPTS="-Xmx2048m -Dfile.encoding=UTF-8 -Dsun.jnu.encoding=UTF-8 -Djava.security.egd=file:/dev/./urandom"

ENV PARAMS=""

ENV LANG=zh_CN.UTF-8 LC_ALL=zh_CN.UTF-8 LANGUAGE=zh_CN:zh TZ=Asia/Shanghai RUNNING_IN_DOCKER=true

ADD target/*.jar /app.jar

EXPOSE 8888

ENTRYPOINT ["sh","-c","java $JAVA_OPTS -jar /app.jar $PARAMS"]
