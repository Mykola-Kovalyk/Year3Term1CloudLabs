FROM alpine:3.18.4

ARG GCP_CREDENTIALS
ARG SRC

ENV GOOGLE_APPLICATION_CREDENTIALS=/keys/pubsub.json
ENV PROJECT=macro-parity-403112

RUN apk add --no-cache python3 py3-pip curl

COPY ${SRC} /run/

WORKDIR /run

COPY ${GCP_CREDENTIALS} ${GOOGLE_APPLICATION_CREDENTIALS}

#incstall gcloud cli
RUN curl -O https://dl.google.com/dl/cloudsdk/channels/rapid/downloads/google-cloud-cli-453.0.0-linux-x86_64.tar.gz
RUN tar -xvzf google-cloud-cli-453.0.0-linux-x86_64.tar.gz -C /
RUN rm -rf google-cloud-cli-453.0.0-linux-x86_64.tar.gz
RUN ln -s /google-cloud-sdk/bin/gcloud /usr/bin/gcloud
RUN gcloud auth activate-service-account --key-file=${GOOGLE_APPLICATION_CREDENTIALS}
RUN gcloud config set project ${PROJECT}

RUN pip install -r requirements.txt

ENTRYPOINT python3 sensor.py