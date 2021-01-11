(ns mesh.web
  (:require
    [shadow.resource :as rc]
    ["path" :as path]
    ["process" :as process]
    ["express" :as express]
    ["cookie-parser" :as cookies]
    ["body-parser" :as body-parser]
    ;["morgan" :as morgan]
    ;["rotating-file-stream" :as rfs]
    ))

; fata errors should bail and notify the user
(defn bail [msg]
  (js/console.error msg)
  (js/console.error "WebRTC Signaling Mesh exit.")
  (js/process.exit 1))

(defn add-default-middleware [app]
  ; set up logging
  #_ (let [logs (str js/__dirname "/logs")
        access-log (rfs "access.log" #js {:interval "7d" :path logs})]
    (.use app (morgan "combined" #js {:stream access-log})))
  ; configure sane server defaults
  (.set app "trust proxy" "loopback")
  (.use app (cookies))
  ; json body parser
  (.use app (.json body-parser))
  app)

(defn add-default-routes [app]
  ; none yet here
  ;(.use app "/" (serve-static (path/join js/__dirname ".." "public")))
  app)

(defn reset-routes [app]
  (let [router app._router]
    (when router
      (print (str "Deleting " (aget app "_router" "stack" "length") " routes"))
      (set! app._router nil))
    (-> app
        (add-default-middleware)
        (add-default-routes))))

(defn create []
  (-> (express)
      (add-default-middleware)
      (add-default-routes)))

(defn serve [app]
  (let [host (or (aget js/process.env "BIND_ADDRESS") "127.0.0.1")
        port (or (aget js/process.env "PORT") "8000")
        srv (.bind (aget app "listen") app port host)]
    (srv (fn [] (js/console.log "Web server started: " (str "http://" host ":" port))))))
