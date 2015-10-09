(ns five-three-one.core
  (:require [reagent.core :as reagent]
            [five-three-one.views :as views]
            [five-three-one.routes :as routes]
            [five-three-one.db :as db]
            five-three-one.subs
            five-three-one.handlers
            five-three-one.utils.ajax))

;; -------------------------
;; Initialize app
(defn mount-root []
  (reagent/render [views/current-page] (.getElementById js/document "app")))

(defn init! []
  (routes/app-routes)
  (db/init)
  (mount-root))
