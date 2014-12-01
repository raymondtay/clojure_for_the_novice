## Here's a couple of examples why Clojure code reads better than Java

Here's an example of how you would define 
functions and you would do well to notice the close similarity
to the mathematical equivalent.

As the book rightly pointed out, it is going to be difficult
to write even this trivial function in Java. Ironically,
the majority language on the JVM does not provide for funcitons
as first class values. In Java, code may only exists within methods,
which must be associated with a class, and methods cannot be referenced as
objects short of resorting to Java's reflection API.

```
(defn twice [f x] (f (f x) x))
(def f (fn [x] (* 2 x)))
(twice f 4)
=> 16
```

The ability to map functions against a data structure is very helpful
and here's an example:
```
(map clojure.string/lower-case ["Scala" "code" "can" "be" "noisy"])
=> ("scala" "code" "can" "be" "noisy")
```
Another cool thing you can do with Clojure is the ability to apply `zip`
and `map` abstracted by the Clojure's map function
```
(user=> (map * (range 10) (range 10))
(0 1 4 9 16 25 36 49 64 81)
```

