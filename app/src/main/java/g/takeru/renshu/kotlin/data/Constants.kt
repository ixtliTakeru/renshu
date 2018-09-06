package g.takeru.renshu.kotlin.data

class Constants {
}

enum class Day {
    Morning, Afternoon, Night;

    override fun toString(): String {
        return super.toString().toUpperCase()
    }
}

enum class Months(var shorthand: String) {
    January("JAN"){
        override fun printSomething() {
            println("First month of the year.")
        }
    },

    February("FEB"){
        override fun printSomething() {
            println("Second month of the year.")
        }
    },

    March("MAR"){
        override fun printSomething() {
            println("Third month of the year.")
        }
    };

    var hello: String = "Say Hello to Kotlin Enums"
    abstract fun printSomething()
}