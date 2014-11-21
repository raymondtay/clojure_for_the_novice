// define a record named Person 
// with two fields: first name and last name
(defrecord Person [firstname lastname])

// here is how to use it
Person{:firstname "Raymond", :lastname "Tay")
// or
Person{"Raymond", "Tay"}

// note to self:
// Turns out that the defrecord is actually a macro in clojure
// and it has the following form
// (defrecord name [arg1 arg2 arg3])
// if you need different semantics, write your own macro. 
// If you want a variant of records with strong typing
// and configurable null-checking for all fields. 
// you can create your own defrecord macro to be used like this:
// (defrecord name [Type :arg1 Type :arg2 Type :arg3] :allow-nulls false)
//
// This ability to reprogram the language from within the langauge is the 
// unique advantage of Lisp. 
// + Lisp is homoiconic. That is, Lisp code is just Lisp data. This makes
//   it easy for programs to write other programs.
// + The whole language is therem, all the time. Paul Graham's essay "Revenge of the nerds"
//   explains why this concept is so powerful

