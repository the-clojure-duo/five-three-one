(ns five-three-one.prod
  (:require [five-three-one.core :as core]))

;;ignore println statements in prod
(set! *print-fn* (fn [& _]))

(core/init!)
