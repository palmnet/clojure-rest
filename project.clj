(defproject clojure-rest "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :main ^:skip-aot clojure-rest.handler
  :url "http://example.com/FIXME"
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [cheshire "5.3.1"]
                 [korma "0.3.1"]
                 [log4j "1.2.15" :exclusions [javax.mail/mail
                                              javax.jms/jms
                                              com.sun.jdmk/jmxtools
                                              com.sun.jmx/jmxri]]
                 [clj-http "0.9.1"]
                 [postgresql/postgresql "8.4-702.jdbc4"]
                 [compojure "1.1.6"]]
  :plugins [[lein-ring "0.8.10"]]
  :ring {:handler clojure-rest.handler/app :auto-reload? true :auto-refresh? true}
  :profiles
  {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                        [korma "0.3.1"]
                        [ring-mock "0.1.5"]]}})
