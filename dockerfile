FROM gradle:alpine
WORKDIR /app
ADD build.gradle /app/
RUN gradle build -x test --parallel --continue > /dev/null 2>&1 || true
COPY . /app
CMD gradle clean build --no-daemon && java -jar build/libs/*.jar