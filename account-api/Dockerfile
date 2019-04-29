FROM openjdk:8-jre-alpine
ADD /target/account-api.jar.jar account-api.jar
ENV JAVA_OPTS="-Xms128m -Xmx256m -Xdebug -Xrunjdwp:server=y,transport=dt_socket,address=8000,suspend=n"
ENTRYPOINT exec java $JAVA_OPTS -jar account-api.jar