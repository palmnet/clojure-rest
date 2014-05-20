(ns clojure-rest.handler
  (:use compojure.core)
  (:require [compojure.handler :as handler]
            [compojure.route :as route]
            [cheshire.core :refer :all]))

(defn json [request]
  {:status 200
   :headers {"Content-Type" "text/json"}
   :body (generate-string {:first "Yasushi" :last "Sugino"})}) 

(defroutes app-routes
  (GET "/" [] "Hello World")
  (GET "/json" [] json)
  (route/resources "/")
  (route/not-found "Not Found"))

(def app
  (handler/site app-routes))


(defn handler [request]
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body "Hello World2"})
