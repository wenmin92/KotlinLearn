package gc

/* ==== Summary =========================================================
 * 《Java 讲义》 6.10 对象于垃圾回收
 *  1. 对象在内存中的状态
 *  2. 强制垃圾回收
 *  3. finalize 方法
 *  4. 对象的软、弱和虚引用
 * ======================================================================*/


var test: FinalizeTest? = null

class FinalizeTest {
    fun info() = println("info")

    protected fun finalize() {
        test = this
        println("finalize")
    }
}

fun main() {
    FinalizeTest()
    System.gc() // 仅仅执行gc(),不会立即回收
    System.runFinalization() // 1.单独执行无效果; 2.跟在gc()后调用,info()方法才会执行,仅仅gc(),info()不会执行; 3.runFinalization()返回后,JVM已经尽可能执行了所有待回收对象的finalize方法
    test?.info()
}
