webrtc-signaling-mesh: build.js
	echo "#!/usr/bin/env node" > $@
	npx browserify --node build.js | npx uglifyjs >> $@
	chmod 755 $@

build.js: src/**/*.cljs shadow-cljs.edn
	npx shadow-cljs release artifact
