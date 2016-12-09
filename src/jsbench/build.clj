(ns jsbench.build)

(defn js-escape [x]
  (-> (clojure.string/replace x #"\\" "\\\\\\\\")
      (clojure.string/replace #"\n" "\\\\n")
      (clojure.string/replace #"\r" "\\\\r")
      (clojure.string/replace #"\"" "\\\\\"")
      (clojure.string/replace #"'" "\\\\'")))

(defn wrap-bench [x]
  (format "
var start = new Date().getTime();
eval(\"var parse = new Date().getTime(); !function(){%s}()\")
var execTook = new Date().getTime() - parse;
var parseTook = parse - start;
alert(parseTook);
alert(execTook);
" x))

(defn write-bench
  ([in out]
   (let [main-o (slurp in)
         main-o (js-escape main-o)]
     (spit out (wrap-bench main-o))))
  ([in1 in2 out]
   (let [main1 (slurp in1)
         main1 (js-escape main1)
         main2 (slurp in2)
         main2 (js-escape main2)]
     (spit out (wrap-bench (str main1 " " main2))))))

(comment
  (write-bench "main-o.js" "target/cljs/main-o.js")
  (write-bench "jquery-3.1.1.min.js" "target/cljs/main-jq.js")
  (write-bench "react.min.js" "react-dom.min.js" "target/cljs/main-react.js")
  )

