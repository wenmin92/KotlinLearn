package gc

import java.lang.ref.WeakReference

/* ==== Summary =========================================================
 * 《Java 讲义》 6.10 对象于垃圾回收
 *  1. 对象在内存中的状态
 *  2. 强制垃圾回收
 *  3. finalize 方法
 *  4. 对象的软、弱和虚引用
 * ======================================================================*/

/**
 * 引用类型：强引用，软引用，弱引用，虚引用
 * TYPE: Strong, Soft, Weak, Phantom
 */

class ReferenceTest {
    fun info() = "hello"
}

fun main() {
    // 创建一个对象
    var obj: ReferenceTest? = ReferenceTest()

    // 创建弱引用，与 obj 指向同一个对象
    val wr = WeakReference(obj)

    // 切断 obj 引用
    obj = null

    // 取出弱引用所引用的对象
    println(wr.get()?.info())

    // 强制垃圾回收
    System.gc()
    System.runFinalization()

    // 再次取出弱引用所引用的对象
    println(wr.get()?.info())
}