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

## On `partial` function application 
An example would explain this..
```
(def only-strings (partial filter string?)) 
(only-strings ["a" 1 2 4])
```
and when you compare to the function literals like this
```
(#(filter string? %) ["a" 1 2 5]) ;; 1
(#(filter % ["a" 1 2 5]) string?) ;; 2
```
which does exactly the same thing. The original 
annotation does seem to work pretty well but if i had to choose
then the form in (1) would appeal to me as i like having my data come last...

In the book, the authors explained 
<pre>
Be aware that, compared to a regular funciton call with 
explicit arguments, higher-order functions like 'apply' and 'partial'
do carry a performance penalty, albeit a small one and only on large arities.
'comp' up to three arguments and 'partial' up to four use specialization implementations
which create plain closures w/o requiring packing and unpacking arguments in a varargs sequence.

The performance penalty associated with larger arities of apply and partial 
is because such functions need to unpack sequence of provided arguments in order
to call the arity of the function that corresponds to the total number of arguments provided.
This can never be as fast as regular clojure function calls, which is simply reuse the JVM's method invocation
machinery. On the bright side, because of that underlying efficient 
machinery, calling functions with apply and the functions returned by partial 
remain leaps and bounds faster in Clojure than for example, direct, explicit method
calls in Python or Ruby.
</pre>

I'm in. Where do i sign?


