package me.krb

import me.krb.transform.FastFourierTransform
import me.krb.util.plus
import me.krb.util.times
import org.apache.commons.math3.complex.Complex
import org.apache.commons.math3.util.MathUtils
import org.junit.jupiter.api.Test
import org.knowm.xchart.QuickChart
import org.knowm.xchart.SwingWrapper
import org.knowm.xchart.XYChart
import kotlin.math.cos
import kotlin.math.pow
import kotlin.math.sqrt

class DerivativeApproximationTest {

    @Test
    fun testPolynomial() {
        var i = 0.0

        val piOverFour = Math.PI / 4
        val total = MathUtils.TWO_PI - piOverFour

        val data = ArrayList<Double>()

        while (i < total) {
            data.add(i)

            i += piOverFour
        }

        var f = Complex(3.0)

        data.forEachIndexed { j, t -> f += (Complex.I * t).exp() * j.toDouble() }

        println(f)
    }

    @Test
    fun testSineWaveApproximation() {
        val charts = ArrayList<XYChart>()

        // Creating and plotting base data
        val x = ArrayList<Double>()

        val time = 5
        val rate = 1.0
        val freq = 1.0

        val points = 2.0.pow(9)

        val period = time / (rate * points)

        val max = points * period

        var t = 0.0

        while (t < max) {
            val f1 = cos(MathUtils.TWO_PI * freq * t)

            x.add(f1)

            t += period
        }

        val xData: DoubleArray = List(x.size) { idx -> idx.toDouble() / 100 }.toDoubleArray()

        val chart: XYChart = QuickChart.getChart("Chart", "x", "y", "sin(x)", xData, x.toDoubleArray())

        // Creating and plotting transformed data
        val transformed = DerivativeApproximation(FastFourierTransform()).approximateDerivative(x.toDoubleArray())

        val xTransformedData: DoubleArray = transformed.mapIndexed { idx, _ -> idx.toDouble() }.toDoubleArray()
        val yTransformedData: DoubleArray =
            transformed.map { complex -> sqrt(complex.real.pow(2) + complex.imaginary.pow(2)) }.toDoubleArray()

        chart.addSeries("Transformed", xTransformedData, yTransformedData)

        // Finalize plot

        charts.add(chart)

        SwingWrapper(charts).displayChart()

        Thread.sleep(1000000)
    }

}