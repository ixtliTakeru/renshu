package g.takeru.renshu.kotlin

/**
 * ref: http://johnnyshieh.me/posts/kotlin-singleton-pattern/
 * ref: https://medium.com/@BladeCoder/kotlin-singletons-with-argument-194ef06edd9e
 */

class Singleton private constructor(){
    init {
        println("This ($this) is a singleton")
    }

    private object Holder {
        val INSTANCE = Singleton()
    }

    companion object {
        val instance: Singleton by lazy { Holder.INSTANCE }
    }
    }

    var text:String ?= null
}

// var first = Singleton.instance     // This (Singleton@xxxxxx) is a singleton
// first.b = "hello world"
// var second = Singleton.instance
// println(second.b)                  // hello world