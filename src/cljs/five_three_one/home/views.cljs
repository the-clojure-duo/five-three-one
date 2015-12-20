(ns five-three-one.home.views
  (:require [reagent.core :as reagent :refer [atom]]
            [re-frame.core :refer [subscribe
                                   dispatch]]
            five-three-one.home.handlers
            five-three-one.home.subs
            [clojure.string :as str]
            [five-three-one.components.counter :as counter]))

(defn heading
  []
  "this is a heading")

(defn page []
  (fn []
    [:div [:h2 (heading)]
     [:div [:a {:href "#/about"} "go to about page"]]
     [counter/count [:home :counts :first]]
     [:h2 "OTHER COUNTER"]
     [counter/count [:home :counts :second]]
     [counter/count [:home :counts :third]]
     [:a {:href "/signout"} "SIGN OUT"]]))
