This is the place where i get to know Clojure
a little better.

## Clojure simplies concurrent programming

On page 9 of the book, it is written
```
Clojure's support for functional programming makes it easy to write thread
safe code. Since immutablke data strucutures cannot ever change, there is no
danger of data corruption based on another thread's activity. 

Howver, Clojure's support for concurrency goes beyond just functional programming.
When you need references to mutable data, Clojure protects them via STM.
STM is a higher-level approach to thread safety than the locking mechanisms that Java
provides. Rather than creating fragile, error-prone locking strategies, you can 
protected shared state with transactions. This is much more productive, because many
programmers have a good understanding of transactions based on experiences with databases.
```

## Reading the source code for Clojure programs

On page 20 of the book, it is written
```
Much of Clojure is written in Clojure, and it is instructuve to read the source
code. You can view the source of a Clojure function using the repl library.
i.e. (clojure.repl/source a-symbol)

(use 'clojure.repl)
(source identity)

```

