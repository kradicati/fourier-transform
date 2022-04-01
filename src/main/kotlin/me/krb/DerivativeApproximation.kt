package me.krb

import me.krb.transform.FourierTransform
import me.krb.util.times
import org.apache.commons.math3.complex.Complex
import org.apache.commons.math3.util.MathUtils

class DerivativeApproximation(private val transform: FourierTransform) {

    fun approximateDerivative(x: DoubleArray): Array<Complex> {
        val epsilon = 1.0 / MathUtils.TWO_PI

        val transformed: Array<Complex> = transform
            .compute(x)
            .map { complex -> Complex.I * epsilon * complex }
            .toTypedArray()
        return transform.computeInverse(transformed)
    }

}