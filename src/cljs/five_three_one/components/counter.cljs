(ns five-three-one.components.counter
  (:require-macros [reagent.ratom :refer [reaction]])
  (:require [reagent.core :as reagent :refer [atom]]
            [re-frame.core :refer [subscribe
                                   dispatch
                                   register-sub
                                   register-handler]]))

;; sub
(register-sub
 :count
 (fn
   [db [_ path]]
   (reaction (get-in @db path))))

;; handler
(register-handler
 :count-inc
 (fn [db [_ path]]
   (update-in db path inc)))

;; view
(defn count [path]
  (let [count (subscribe [:count path])]
    (fn []
      [:div 
       [:button {:on-click #(dispatch [:count-inc path])}
        "Click here to increment the number!"]
       [:div (str @count)]])))
