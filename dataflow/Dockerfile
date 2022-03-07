FROM openjdk:11 AS java

FROM python:3.7 AS py


COPY --from=java /usr/local/openjdk-11 /usr/local/openjdk-11


ADD . /usr/src/rv1448/

RUN apt-get -y update && \
        apt-get -y install cmake pkg-config vim less ipython3 docker.io python3-protobuf 

RUN python3 -m pip install --upgrade pip && \
    python3 -m pip install -r /usr/src/rv1448/requirements.txt && \
        python3 -m pip install ipython 

ENV JAVA_HOME /usr/local/openjdk-11
RUN ln -s /usr/local/openjdk-11/bin/java /usr/bin/java

RUN cd /usr/src/rv1448 \
    && ./mvnw clean package -Pdocker 