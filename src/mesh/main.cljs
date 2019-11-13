(ns mesh.main
  (:require ["bittorrent-dht" :as DHT]
            ;["bittorrent-protocol" :as BT]
            ;["express" :as Express]
            ;["express-ws" :as WS]
            
            ))

(defn create-servers []
  (let [; express
        ; express-websocket
        ; dht
        ; bittorrent
        ]))

(defn main! []
  (js/console.log "Starting.")
  (let [dht (DHT.)]
    ; TODO: persist DHT to disk
    (.on dht "peer"
         (fn [peer infoHash from]
           (js/console.log "peer:" peer infoHash from)))
    (.announce dht "428803921137c6cce177ea3acadad873b5b4f8f5" (fn [] (print "Announced.")))
    (.listen dht)
    ;(js/console.log (.address dht))
    
    ))

(defn reload! []
  (js/console.log "Reload."))
