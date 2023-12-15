FROM maven:3.8.5-openjdk-17

RUN microdnf install xmlstarlet

ARG GCP_CREDENTIALS
ARG SRC

ENV GOOGLE_APPLICATION_CREDENTIALS=/keys/deployed-instance.json
ENV DOCKER_SRC=/app
ENV JAR_DIR=/jar
ENV PROJECT=terraform-408207

COPY ${SRC} ${DOCKER_SRC}
COPY ${GCP_CREDENTIALS} ${GOOGLE_APPLICATION_CREDENTIALS}

#incstall gcloud cli
RUN curl -O https://dl.google.com/dl/cloudsdk/channels/rapid/downloads/google-cloud-cli-453.0.0-linux-x86_64.tar.gz
RUN tar -xvzf google-cloud-cli-453.0.0-linux-x86_64.tar.gz -C /
RUN rm -rf google-cloud-cli-453.0.0-linux-x86_64.tar.gz
RUN ln -s /google-cloud-sdk/bin/gcloud /usr/bin/gcloud
RUN gcloud auth activate-service-account --key-file=${GOOGLE_APPLICATION_CREDENTIALS}
RUN gcloud config set project ${PROJECT}


WORKDIR ${DOCKER_SRC}

RUN mvn clean package;

RUN printf '%s' "ARTIFACT_ID='$(xmlstarlet select --template --value-of "_:project/_:artifactId" pom.xml)' " >>  ~/.bashrc
RUN printf '%s' "VERSION='$(xmlstarlet select --template --value-of "/_:project/_:version" pom.xml)' " >> ~/.bashrc
RUN chmod +x ~/.bashrc


WORKDIR ${JAR_DIR}
RUN cp -a ${DOCKER_SRC}/target/. ${JAR_DIR}
RUN rm -rf ${DOCKER_SRC}

EXPOSE 8080

ENTRYPOINT \
    source ~/.bashrc && \
    MYSQL_USER=$(gcloud secrets versions access "latest" --secret "mysql-user") \
    MYSQL_PASSWORD=$(gcloud secrets versions access "latest" --secret "mysql-password") \
    java -jar $ARTIFACT_ID-$VERSION.jar