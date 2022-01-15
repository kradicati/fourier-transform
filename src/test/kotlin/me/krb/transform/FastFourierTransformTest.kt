package me.krb.transform

import org.apache.commons.math3.complex.Complex
import org.apache.commons.math3.transform.DftNormalization
import org.apache.commons.math3.transform.FastFourierTransformer
import org.apache.commons.math3.transform.TransformType
import org.apache.commons.math3.util.MathUtils
import org.junit.jupiter.api.Test
import org.knowm.xchart.QuickChart
import org.knowm.xchart.SwingWrapper
import org.knowm.xchart.XYChart
import java.io.File
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
            dataPoints.add(Complex(sin(MathUtils.TWO_PI * freq * t)))

            t += period
        }

        //File("sinwave.txt").writeText(dataPoints.map { cpx -> cpx.real }.toDoubleArray().contentToString())

        val fft = FastFourierTransform()
        //val fft = FastFourierTransformer(DftNormalization.STANDARD);

        val start = System.currentTimeMillis()
        val transformed = fft.compute(dataPoints.toTypedArray())
        //val transformed = fft.transform(dataPoints.toTypedArray(), TransformType.FORWARD)
        println("Transform took " + (System.currentTimeMillis() - start) + " ms")

        val xData: DoubleArray = transformed.mapIndexed { idx, _ -> idx.toDouble() }.toDoubleArray()
        val yData: DoubleArray = transformed.map { complex -> complex.real }.toDoubleArray()

        val chart: XYChart = QuickChart.getChart("Sample Chart", "Frequency", "Real part", "fhat(x)", xData, yData)
        SwingWrapper(chart).displayChart()

        Thread.sleep(10000000)
    }

}