package me.krb.transform

import org.apache.commons.math3.complex.Complex
import org.junit.jupiter.api.Test
import kotlin.math.sin

class FastFourierTransformTest {

    @Test
    fun computeTransform() {
        val dataPoints = ArrayList<Complex>()

        var i = 0.0
        while (i < 12.7) {
            dataPoints.add(Complex(sin(i)))
            i += 0.1
        }

        val fft = FastFourierTransform()

        val transformed = fft.compute(dataPoints.toTypedArray())

        println(transformed.contentToString())
    }

}