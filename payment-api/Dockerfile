FROM openjdk:8-jre-alpine
ADD /target/payment-api.jar.jar payment-api.jar
ENV JAVA_OPTS="-Xms128m -Xmx256m -Xdebug -Xrunjdwp:server=y,transport=dt_socket,address=8000,suspend=n"
ENTRYPOINT exec java $JAVA_OPTS -jar payment-api.jar