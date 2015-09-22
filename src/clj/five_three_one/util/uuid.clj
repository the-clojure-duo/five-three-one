(ns five-three-one.util.uuid
  (:import java.util.UUID))

(defn from-string [s]
  (java.util.UUID/fromString s))

(defn random []
  (java.util.UUID/randomUUID))

