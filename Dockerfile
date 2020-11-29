FROM openjdk:8-jre

ENV PROXYPORT "8089"
ENV PROXYSOURCE "/.*"
ENV PROXYTARGET "localhost/"

WORKDIR /home/genmock

COPY target/libs ./libs
COPY target/wiremock.jar .
COPY src/main/bash/startproxy.sh .
COPY src/main/wiremock/requestmappings.json .
RUN mkdir -p src/test/resources/mappings
RUN chmod +x startproxy.sh
EXPOSE $PORT

CMD ["./startproxy.sh"]