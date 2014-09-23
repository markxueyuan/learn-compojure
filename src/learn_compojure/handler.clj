(ns learn-compojure.handler
  (:require [compojure.core :refer :all]
            [compojure.handler :as handler]
            [compojure.route :as route]))

(defn inner-routes [user-id]
  (routes
    (GET "/" [] "What's up")
    (GET "/profile" [] (str "Your id is: " user-id))
    (GET "/post" [] (str "I don't know your post condition"))))

(defroutes app-routes
  (GET "/" [] "Hello Yuanyuan")
  (GET "/2" [] "<h1>黑粗翔</h1>")
  (GET "/hehe" request (str request))
  (GET "/params" {a :params} (str a))
  (GET "/cheat/you/:a/:b/:c/:d" [a b c d]
    (str "<h1>" "You idiot, see this " [a b c d] "</h1>"))
  (GET "/far/:from/:the/:reality" [from the reality :as {{user-agent "user-agent"} :headers}]
    (str from the reality ", " user-agent))
  (GET "/foo" request (get-in request [:headers "user-agent"]))
  (GET "/user/:id" {{id :id} :params}
    (str "welcome" id))
  (GET ["/test/:value", :value #"[0-9]+"] {{value :value} :params}
    (str "The number is " value))
  (GET "/:name" [name]
    (str "你好," name))
  (GET "/system/s" {{user-agent "user-agent"} :headers}
    (str "Your system is, " user-agent))
  (route/resources "/")
  (route/not-found "Not Found")
  (comment (context "/account" []
    (GET "/" []  "we need more test")
    (route/not-found "not found"))))

(def app
  (handler/site app-routes))


