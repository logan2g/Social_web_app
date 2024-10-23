FROM openjdk:8
COPY ./build/libs/SocialNet-0.0.1.jar /tmp
COPY ./data.mv.db /tmp
WORKDIR /tmp
ENTRYPOINT ["java","-jar", "SocialNet-0.0.1.jar"]
EXPOSE 8080