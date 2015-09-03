(ns five-three-one.controllers.google-auth
  (:require [compojure.core :refer [defroutes GET]]
            [ring.middleware.params :refer [wrap-params]]
            [ring.util.response :refer [redirect response content-type]]
            [environ.core :refer [env]] 
            [clj-http.client :as client]
            [clojure.pprint :refer [pprint]]
            ring.util.codec
            [clojure.data.json :as json]
            [five-three-one.model.user :as model]))

(def CLIENT_ID (:client-id env))

(def REDIRECT_URI (:redirect-uri env))

(def CLIENT_SECRET (:client-secret env))

(def red (str "https://accounts.google.com/o/oauth2/auth?"
              "scope=email%20profile&"
              "redirect_uri=" (ring.util.codec/url-encode REDIRECT_URI) "&"
              "response_type=code&"
              "client_id=" (ring.util.codec/url-encode CLIENT_ID) "&"
              "approval_prompt=force&"
              #_"access_type=offline"))

;; user clicks sign in button
;; button goes to /google_login
;; /google_login route redirects to https://accounts.google.com/...
;; user enters credentials and clicks "log in"
;; user gets redirected to REDIRECT_URI with auth code inside query params
;; I hold onto auth code and redirect user from REDIRECT_URI to wherever I want
;; I use the given auth code to POST to google as form data
;; google returns a JSON containing the access token
;; use access token to get user data
;; redirect user back to home page with user data

(defn get-user-data [access-token]
  (let [json-str (:body (client/get "https://www.googleapis.com/oauth2/v1/userinfo"
                                    {:accept :json
                                     :query-params {"access_token" access-token}}))
        result (json/read-str json-str)]
    result))

(defroutes routes
  (GET "/google_login" [] (redirect red)))

(defn success-handler [{{access-token :access-token} :oauth2 :as request}]
  (let [{:strs [given_name family_name email]} (get-user-data access-token)
        ;; get user from db using email,
        ;; create new one if doesn't exist
        user (if-let [retrieved-user (model/get-user-by-email email)]
               retrieved-user
               (model/create-user! {:first-name given_name
                                    :last-name family_name
                                    :email email}))
        session (assoc-in (:session request) [:user] (:uuid user))
        response (assoc (content-type (response "") "text/html") :session session)]
    ;; add user data to session
    (println "from auth")
    (pprint response)
    response))

(def oauth-config {:client-id (:client-id env)
                   :client-secret (:client-secret env)
                   :redirect-uri (:redirect-uri env)
                   :token-endpoint "https://www.googleapis.com/oauth2/v3/token"
                   :success-handler success-handler})

(def signin-route
  (GET "/google_login" [] (redirect red)))
