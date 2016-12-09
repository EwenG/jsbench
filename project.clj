(defproject jsbench/jsbench "0.0.1"
  :dependencies [[org.clojure/clojure "1.8.0"]]
  :profiles {:dev {:dependencies [[org.clojure/clojurescript "1.9.293"]]}}
  :source-paths ["src"]
  :plugins [[lein-cljsbuild "1.1.5"]]
  :cljsbuild {
    :builds [{
        ; The path to the top-level ClojureScript source directory:
        :source-paths ["src"]
        ; The standard ClojureScript compiler options:
        ; (See the ClojureScript compiler documentation for details.)
        :compiler {
          :output-to "target/cljs/main-o.js"  ; default: target/cljsbuild-main.js
          :optimizations :advanced
          :pretty-print false}}]})
