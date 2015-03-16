(ns clojure-rest.domain
  (:use  [korma.core]
         [korma.db])
  (:require [compojure.handler :as handler]
            [compojure.route :as route]
            [clojure.java.jdbc :as sql]
            [cheshire.core :refer :all]))

(def dbspec (postgres {:db "axis"
                       :user "sugino"
                       :password "bigbird"}))

(defdb mydb dbspec)
(defentity factoid)

(defn create-tables
  "Create a factoid table"
  [db]
  (sql/db-do-commands 
    db 
    (sql/create-table-ddl
      :factoid
      [:id          "INT" "NOT NULL" "PRIMARY KEY"]
      [:created_by  "VARCHAR(255)"]
      [:fact        "VARCHAR(255)"]
      [:answer      "VARCHAR"])
    "CREATE INDEX FACTIDX ON factoid(fact)"))

(create-tables dbspec)
