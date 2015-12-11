FROM projectfrank/clojure-node
RUN mkdir -p /usr/src/app
WORKDIR /usr/src/app
COPY project.clj /usr/src/app/
RUN lein deps
COPY . /usr/src/app
RUN ./build.sh
EXPOSE 3000
CMD ./run-prod.sh