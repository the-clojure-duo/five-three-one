(ns five-three-one.about.views
  (:require [re-frame.core :refer [subscribe]]))

(defn page []
  [:div [:h2 "About five-three-one"]
   [:div [:a {:href "#/"} "go to the home page"]]])
