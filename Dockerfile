# Dockerfile for NRC coin daemon

#
# Daemon executable builder
#
FROM ubuntu:16.04 as builder

COPY .builder/checkpoints.patch /tmp/

RUN apt-get -y update \
 && apt-get -y install \
        build-essential \
        git \
        libboost-filesystem-dev \
        libboost-program-options-dev \
        libboost-system-dev \
        libboost-thread-dev \
        libssl-dev \
        libdb++-dev \
        libminiupnpc-dev \
        libqrencode-dev

RUN git clone https://github.com/NRCCOIN/NRCCOIN /tmp/nrc \
 && cd /tmp/nrc/src \
 && mkdir obj \
 && chmod +x leveldb/build_detect_platform \
 && patch checkpoints.cpp < /tmp/checkpoints.patch \
 && make -f makefile.unix


#
# Final Docker image
#
FROM ubuntu:16.04
LABEL description="NRC Daemon"

COPY --from=builder /tmp/nrc/src/Noorcoind /usr/local/bin/nrcd
COPY .docker /

RUN apt-get -y update \
 && apt-get -y install \
        gettext \
        libboost-filesystem-dev \
        libboost-program-options-dev \
        libboost-system-dev \
        libboost-thread-dev \
        libssl-dev \
        libdb++-dev \
        libminiupnpc-dev \
        libqrencode-dev \
        netcat \
        pwgen \
 && groupadd --gid 1000 nrc \
 && useradd --uid 1000 --gid nrc --shell /bin/false --create-home nrc \
 && chmod +x /usr/local/bin/notifier \
 && chmod +x /usr/local/bin/entrypoint

USER nrc
WORKDIR /home/nrc

RUN mkdir -p /home/nrc/.POLI
RUN mkdir -p /home/nrc/.Noorcoin

ENTRYPOINT [ "/usr/local/bin/entrypoint" ]
CMD [ "nrcd", "-printtoconsole" ]
