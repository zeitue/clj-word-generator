(ns clj-word-generator.core 
  (:gen-class ) 
  (:use [clojure.string :only (split)])
  (:use [clojure.java.io]))
(defn get-lines [fname]
  (with-open [r (reader fname)]
    (doall (line-seq r))))
(defn get-list [x lines]
  (first (remove nil?
  (for [num  (range (count lines)) ]
   (if (= (first(split (nth lines num) #"\s+" )) x)
     (rest(split (nth lines num) #"\s+" )))))))
(defn word-runner[depth args carry sounds]
  (let[entry (nth args depth nil)]
    (if-not (= entry nil)
       (doseq[x (get-list (str entry) sounds)]
         (if (= (nth args (+ depth 1) nil) nil)
           (println (str carry x))
           (word-runner (+ depth 1) args (str carry x) sounds))))))
(defn -main [& args]
  (if-not (nil? args)
    (let [sounds (get-lines (nth args 0 nil))]
 (word-runner 1 args "" sounds))
 (println "Usage: clj-word-generator ListFile List1 List2 List3")))