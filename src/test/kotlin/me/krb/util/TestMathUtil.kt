package me.krb.util

import org.junit.jupiter.api.Test
import java.util.*
import kotlin.collections.ArrayList

class TestMathUtil {

    @Test
    fun testPad() {
        val x = ArrayList<Double>()

        val rand = Random()

        for (i in 0 until 260) {
            x.add(rand.nextDouble())
        }

        println(x.size)

        val padded = pad(x.toTypedArray()) {0}

        println("${padded.size} ${padded.contentToString()}")
    }

}