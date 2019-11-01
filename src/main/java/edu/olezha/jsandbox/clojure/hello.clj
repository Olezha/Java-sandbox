(ns edu.olezha.jsandbox.clojure.hello)

(def hello (fn [] "Hello world"))
(println hello)
(defn hello [] "Goodnight Moon")
(println hello)
(defn hello [] (println "Dydh da an Nor")) ; Hello world
(hello)

(quote 1 2 3)
'(1 2 3)

(vector 1 2 3)
(vec '(1 2 3))
[1 2 3]

(nth '(1 2 3) 0)

(def foo {"k1" "v1" "k2" "v2", "k3" "v3"})
(println foo)
(println (foo "k1"))

(def bar {:k1 "v1" :k2 "v2"})
(println (bar :k1))
(println (:k2 bar))

(println :foo)

(def foobar [foo bar])
(map (fn [y] (:k1 y)) foobar)

#{"apple" "pair" "peach"}

(add 3 4)
(+ 3 4)
(+ 1 2 3)

(def list-int '(1 2 3 4))
(def vect-int (vec list-int))
(= vect-int list-int)
(identical? vect-int list-int)

(defn const-fun1 [y] 1)
(println const-fun1 "a")
(defn ident-fun [y] y)
(println ident-fun 10)
(defn list-maker-fun [x f]
  (map (fn [z] (let [w z]
                 (list w (f w))
                 )) x))
(println (list-maker-fun ["a"] const-fun1))
(println (list-maker-fun ["a" "b"] const-fun1))
(println (list-maker-fun [2 1 3] ident-fun))

(defn schwartz [x key-fn]
  (map (fn [y] (nth y 0))
       (sort-by (fn [t] (nth t 1))
                (map (fn [z] (let [w z]
                               (list w (key-fn w)))
                       ) x))))
(println (schwartz [2 3 1 5 4] ident-fun))
(println (apply schwartz [[2 3 1 5 4] ident-fun]))

(defn like-for [counter]
  (loop [ctr counter]
    (println ctr)
    (if (< ctr 10)
      (recur (inc ctr))
      ctr
      )))

(println "10.3.2")
