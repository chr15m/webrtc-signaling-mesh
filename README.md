🚧 Under construction. Please check back later.

 * Problem: WebRTC signaling is centralized.
 * Solution: a decentralized mesh of WebRTC signaling servers.

# Quickstart: [run a node](#run-a-node)

Web browsers can send messages directly peer-to-peer using WebRTC. This gives us a way to build decentralized web applications where browsers (peers) can communicate directly without a trusted third-party server in between. Unfortunately the WebRTC connection phase requires centralized services for establishing the route between peers, NAT hole-punching, and negotiating the connection. These centralized must provide a signalling protocol, STUN, and sometimes TURN connectivity, which makes them a single point of failure in peer-to-peer web applications.

This server provides a solution to this problem. It uses the bittorrent DHT to find other server nodes and then forms a mesh that client browsers can use to coordinate WebRTC connectivity in a decentralized, trust-minimised way. It additionally provides a STUN and TURN service that clients can also use.

Server nodes are protected from becoming overloaded by a proof-of-work requirement on clients. As load increases on a server the proof-of-work they require from clients increases, and clients can use this signal to select for nodes which are under less load, thereby ensuring better service and distribution of traffic over the network.

# Run a node

...

<!--

It also includes other facilities useful for building decentralized web applications:

 * IP
 * DHT mutable get
 * STUN & TURN

# Quick start

If you just want to use the signaling mesh to make your own p2p WebRTC connections you can do so with the client library `sigmesh`.

# Design

### PoW

# Why PoW?

Good fences make good neighbours. PoW is now a well established mechanism in distributed security for 

The purpose of PoW in this system is twofold:

 * Protect nodes from abuse
 * Auction based mechanism

-->

# API

 * `/` - returns server's hash token and current PoW height requirement.
 * `/nodes` - returns a random selection of recently seen nodes `["https://...", "https://..." ...]`.
 * `/ip` - returns the requesting client's IP address as seen by this node.
 * `/dht-get` - make a BitTorrent DHT mutable get request.
 * `/dht-put` - make a BitTorrent DHT mutable put request.
 * `/token` - obtain a TURN server authentication token by sending sufficient PoW.
 * `/room/HASH` - server-sent-event signaling channel where all posts to a particular hash are broadcast to all nodes connected to that hash.

If `content-type` is set to `json` then JSON data will be returned, otherwise an HTML summary will be returned.

Post size is limited to 1k, and every post requires PoW.

PoW is proportional to `max(cpu, mem)`. Additional PoW is required for every message sent in the past minute to the `/room/HASH` endpoint.

A STUN & TURN server are also run. Authentication tokens for the STUN/TURN server can be obtained from the `/token` endpoint.

PoW tokens are generated against a time-stamped HMAC of the server secret which the server can use to establish recency.
