FROM gradle:jre15 as builder
COPY ./ /target/
WORKDIR /target/
RUN gradle bootJar
RUN cp -f build/libs/*.jar /target.jar

FROM openjdk:15-slim
RUN cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime
RUN echo 'Asia/Shanghai' >/etc/timezone
COPY --from=builder /target.jar /target.jar
ENTRYPOINT "java" "-jar" "/target.jar"