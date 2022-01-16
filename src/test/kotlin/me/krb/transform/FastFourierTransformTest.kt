package me.krb.transform

import org.apache.commons.math3.complex.Complex
import org.apache.commons.math3.util.MathUtils
import org.junit.jupiter.api.Test
import org.knowm.xchart.QuickChart
import org.knowm.xchart.SwingWrapper
import org.knowm.xchart.XYChart
import kotlin.math.*

class FastFourierTransformTest {

    @Test
    fun computeTransform() {
        val dataPoints = ArrayList<Complex>()

        val time = 1
        val rate = 1.0
        val freq = 440.0

        val points = 2.0.pow(14)

        val period = time / (rate * points)

        val max = points * period

        var t = 0.0

        while (t < max) {
            val f1 = cos(MathUtils.TWO_PI * freq * t)
            val f2 = 0.5 * cos(MathUtils.TWO_PI * 2 * t)
            val f3 = 3 * cos(MathUtils.TWO_PI * 110 * t)
            val f4 = cos(MathUtils.TWO_PI * 3000 * t)
            val f5 = 1.5 * cos(MathUtils.TWO_PI * 7000 * t)

            dataPoints.add(Complex(f1 + f2 + f3 + f4 + f5))

            t += period
        }

        val fft = FastFourierTransform()

        val start = System.currentTimeMillis()

        val transformed = fft.compute(dataPoints.toTypedArray())

        println("Transform took " + (System.currentTimeMillis() - start) + " ms")

        val xTransformData: DoubleArray = transformed.mapIndexed { idx, _ -> idx.toDouble() }.toDoubleArray()
        val yTransformData: DoubleArray = transformed.map { complex -> sqrt(complex.real.pow(2) + complex.imaginary.pow(2)) }.toDoubleArray()

        val charts = ArrayList<XYChart>()

        val transformChart: XYChart = QuickChart.getChart("Transformed", "Frequency", "Real part", "fhat(x)", xTransformData, yTransformData)

        charts.add(transformChart)

        SwingWrapper(charts).displayChart()

        Thread.sleep(10000000)
    }

}