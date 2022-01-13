package me.krb.transform

import org.apache.commons.math3.complex.Complex
import org.junit.jupiter.api.Test
import org.knowm.xchart.QuickChart
import org.knowm.xchart.SwingWrapper
import org.knowm.xchart.XYChart
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.sin

class DiscreteFourierTransformTest {

    @Test
    fun computeTransform() {
        val dataPoints = ArrayList<Complex>()

        var i = 0.0
        while (i < 10.0) {
            dataPoints.add(Complex(sin(i)))
            i += 0.1
        }

        val dft = DiscreteFourierTransform()

        val normal = dft.compute(dataPoints.toTypedArray())

        println(dataPoints.toTypedArray().contentToString())
        println(normal.contentToString())
    }

    @Test
    fun computeInverse() {
        TODO()
    }
}