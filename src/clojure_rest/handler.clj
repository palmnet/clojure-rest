(ns clojure-rest.handler
  (:use [compojure.core]
        [korma.core]
        [korma.db]
        [clojure-rest.domain])
  (:require [compojure.handler :as handler]
            [compojure.route :as route]
            [cheshire.core :refer :all]))

(defn json [request]
  {:status 200
   :headers {"Content-Type" "text/json"}
   :body (generate-string {:first "Yasushi" :last "Sugino"})})

(defmacro uuid-keyword [rsc]
  `(keyword (str ~rsc "_uuid" )))

(defn make-table [name]
  (eval (symbol (str "clojure-rest.domain/" name))))

(defn res [ds]
    {:status 200
     :headers {"Content-Type" "text/json"}
     :body (generate-string ds )})

(defn find-db [rsc  condition ]
  (if (empty? condition)
    (res (select (make-table rsc)))
    (res (select (make-table rsc)
         (where condition)))))

(defn find-by-uuid [rsc pkey]
  (find-db
    rsc
    (hash-map 
      (uuid-keyword rsc) 
      (java.util.UUID/fromString pkey))))

(defn find-all [rsc]
  (find-db rsc {}))

(defn insert-into 
  [params]
  (let [
        ent (:rc params)
        val-params (dissoc params [:rc])]
  (insert ent (values val-params))))




(defroutes app-routes
  (GET "/" [] "Hello World")
  (GET "/json" [] json)
  (GET "/v1/apgw/:rc" [rc] (find-all rc))
  (POST "/v1/apgw/:rc" {rc :rc params :params}  (insert rc params ))
  (GET "/v1/apgw/:rc/:uuid-value" [rc uuid-value] (find-by-uuid rc uuid-value))
  ;(GET "/v1/apgw/:rc/:uuid-value" [rc uuid-value] (find-by-uuid rc uuid-value))
  (route/resources "/")
  (route/not-found "Not Found"))

(def app
  (handler/site app-routes))

(defn handler [request]
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body "Hello World2"})

(defn- search [rsc pkey]
  (select 
    (make-table rsc) 
    (where {(uuid-keyword rsc) (java.util.UUID/fromString pkey)})))

(find-db "iface" {:iface_uuid #uuid "e908b0da-e0a8-11e3-b627-000c29acdacb"})
(find-by-uuid "iface" "e908b0da-e0a8-11e3-b627-000c29acdacb")
(find-db "iface" {})
(insert-into {:location_name "CHINA" :is_japan false} )
 
