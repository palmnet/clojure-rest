(ns clojure-rest.test.handler
  (:use clojure.test
        ring.mock.request  
        clojure-rest.handler))

(deftest test-app
  (testing "main route"
    (let [response (app (request :get "/"))]
      (is (= (:status response) 200))
      (is (= (:body response) "Hello World"))))
  
  (testing "not-found route"
    (let [response (app (request :get "/invalid"))]
      (is (= (:status response) 404))))

  (testing "iface"
    (let [response (app (request :get "/v1/apgw/iface"))]
      (is (= (:status response) 200))
      (count (:body response))))
;
;  (testing "vrf"
;    (let [response (app (request :get "/v1/apgw/vrf"))]
;      (is (= (:status response) 200))
;      (first (:body response)))))

  (testing "iface-by-key"
    (let [response (app (request :get "/v1/apgw/iface/e908b0da-e0a8-11e3-b627-000c29acdacb"))]
      (is (= (:status response) 200))
      (count (:body response))))

  (testing "iface-post"
    (let [response (app (request :post "/v1/apgw/iface" {:foo "bar"}))]
      (is (= (:status response) 200))
      (print (:body response)))))
  


