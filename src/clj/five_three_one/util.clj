(ns five-three-one.util
  (:require [clojure.data.json :as json]
            [five-three-one.util.uuid :as uuid]))

(defn embed-json-in-dom [var-name data]
  (let [uuid (.toString (uuid/random))]
    (list [:meta {:content (json/write-str data)
                  :id uuid}]
          [:script (str "var "
                        var-name
                        " = JSON.parse(document.getElementById('"
                        uuid
                        "').content);")])))


