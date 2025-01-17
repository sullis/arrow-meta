package arrow.meta.plugins.helloWorld

import arrow.meta.Meta
import arrow.meta.Plugin
import arrow.meta.invoke
import arrow.meta.quotes.Transform
import arrow.meta.quotes.namedFunction

/**
 * The following example shows a Hello World Compiler Plugin.
 *
 * The Hello World plugin auto implements the `helloWorld` function by rewriting the Kotlin AST before the compiler proceeds.
 *
 * ```kotlin
 * val Meta.helloWorld: Plugin get() =
 *   "Hello World" {
 *     meta(
 *       namedFunction({ name == "helloWorld" }) { c ->  // <-- namedFunction(...) {...}
 *         Transform.replace(
 *           replacing = c,
 *           newDeclaration = """|fun helloWorld(): Unit =
 *                               |  println("Hello ΛRROW Meta!")
 *                               |""".function.synthetic
 *         )
 *       }
 *     )
 *   }
 * ```
 *
 * For any user code whose function name is `helloWorld` our compiler plugin will replace the matching function for a
 * function that returns Unit and prints our message.
 *
 * ```kotlin:diff
 * -fun helloWorld(): Unit = TODO()
 * +fun helloWorld(): Unit =
 * +  println("Hello ΛRROW Meta!")
 * ```
 *
 *  Take a look at [`arrow-meta-examples`](https://github.com/arrow-kt/arrow-meta-examples) repository for getting more details.
 */
val Meta.helloWorld: Plugin
  get() =
    "Hello World" {
      meta(
        namedFunction({ name == "helloWorld" }) { c ->
          Transform.replace(
            replacing = c,
            newDeclaration =
            """|fun helloWorld(): Unit = 
               |  println("Hello ΛRROW Meta!")
               |""".function.synthetic
          )
        }
      )
    }
