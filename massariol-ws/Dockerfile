FROM alpine:edge
RUN apk add --no-cache openjdk11
COPY massariol-distribution/build/libs/massariol-distribution.jar /webapp/massariol-distribution.jar
WORKDIR /webapp
ENTRYPOINT ["/usr/bin/java"]
CMD ["-jar", "/webapp/massariol-distribution.jar"]
EXPOSE 8080
