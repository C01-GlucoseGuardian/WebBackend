FROM eclipse-temurin:17-jdk-jammy as builder
COPY . .
RUN chmod +x mvnw
RUN ./mvnw clean package -DskipTests=true -Dmaven.javadoc.skip=true
RUN mkdir target/extracted
RUN java -Djarmode=layertools -jar target/*.jar extract --destination target/extracted

FROM eclipse-temurin:17-jre-jammy
VOLUME /tmp
COPY --from=builder target/extracted/dependencies/ ./
COPY --from=builder target/extracted/spring-boot-loader/ ./
COPY --from=builder target/extracted/snapshot-dependencies/ ./
COPY --from=builder target/extracted/application/ ./
COPY --from=builder src/main/resources/application.properties config/production.properties
EXPOSE 8080
ENTRYPOINT ["java","org.springframework.boot.loader.JarLauncher", "--spring.config.name=production"]