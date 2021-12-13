#
#FROM openjdk:8-jre
#VOLUME ["/app"]
#EXPOSE 8080
#COPY  start.sh start.sh
#RUN sh -c 'touch /app.jar'
#ENTRYPOINT ["sh", "./start.sh"]

FROM openjdk:11
#VOLUME ["/app"]
EXPOSE 8080
ARG DEPENDENCY=target
ADD ${DEPENDENCY}/*.jar microServiceData.jar
ENTRYPOINT ["java","-jar","microServiceData.jar"]