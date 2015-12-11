(ns five-three-one.methods
  (:require [five-three-one.methods.user-info :refer [user-info]]
            [compojure.core :refer [GET POST]]
            [ring.util.response :refer [response content-type]]))

(def methods {:user-info user-info
              :test (fn [& args] "stuff")})

(defn dispatch [{x :body
                 :as request}]
  ;; take a map where the keys are methods and the values are params for those methods
  ;; reduce an array of the keys using an empty map as an accumulator
  ;; reduce fn will retrieve method from a map and retrieve params for the method from the map passed into here
  ;; assoc the result to the accumulator with method name as key
  ;; methods will also be given the request map in case there's something needed from there (e.g. cookies)
  (let [reduce-fn (fn [acc [method params]]
                    (println "method: " request)
                    (let [method-fn (method methods)]
                      (assoc acc method (method-fn params request))))
        result (reduce reduce-fn {} x)]
    (println (str "dispatch: " request))
    (-> result
        response
        (content-type "application/transit+json"))))

(def route (POST "/methods" request (dispatch request)))
