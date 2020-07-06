FROM alpine:edge
RUN apk add --no-cache openjdk11

RUN apk add --no-cache msttcorefonts-installer fontconfig
RUN update-ms-fonts

COPY massariol-distribution/build/libs/massariol-distribution.jar /webapp/massariol-distribution.jar
COPY massariol-application/src/main/resources/fonts/* /usr/share/fonts/
WORKDIR /webapp

#Habilitar para debugar remoto
#ENV JAVA_TOOL_OPTIONS -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005

ENTRYPOINT ["/usr/bin/java"]
CMD ["-jar", "/webapp/massariol-distribution.jar"]
EXPOSE 8080
