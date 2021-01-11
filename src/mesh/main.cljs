(ns mesh.main
  (:require
    [mesh.web :as web]
    ;[mesh.bittorrent :as bt]
    ;[mesh.util :as util]
    [cljs.core.async :refer (go <!) :as async]
    [cljs.core.async.interop :refer-macros [<p!]]))

(defonce app (web/create))

(defn setup-routes [app]
  (.use app "/" (fn [req res] (.json res true))))

(defn reload! []
  (web/reset-routes app)
  (setup-routes app)
  (println "Fresh routes loaded: " (aget app "_router" "stack" "length")))

(defn main! []
  (web/serve app)
  ;(bt/serve app)
  ;(stun/serve app)
  (reload!)
  (println "Servers started."))
