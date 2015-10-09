(ns five-three-one.utils.ajax.cljs
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [cljs.core.async :refer [put! chan <!]]
            [goog.events :as events]
            [cognitect.transit :as t])
  (:import [goog.net XhrIo]
           [goog.net EventType]))

(defn read-transit [s]
  (let [reader (t/reader :json)]
    t/read reader s))

(defn write-transit [x]
  (let [writer (t/writer :json)]
    t/write writer x))

(defn do-ajax [url & [method content]]
  (let [xhr (XhrIo.)
        out (chan)
        method (if (= method :post)
                 "POST"
                 "GET")
        transit-string (when (= method :post)
                         (write-transit content))
        handler #(let [result (-> %
                                  .-target
                                  .getResponseText
                                  read-transit)]
                   (put! out result))]
    (events/listen xhr EventType.SUCCESS handler)
    (.send xhr url nil method transit-string)
    out))
