package me.krb.transform

import me.krb.util.*
import org.apache.commons.math3.complex.Complex
import org.apache.commons.math3.util.MathUtils

class FastFourierTransform : FourierTransform {
    override fun compute(x: Array<Complex>): Array<Complex> {
        var n = x.size.toDouble()

        if (n == 1.0) return x

        var data = x

        if (!isPowerOfTwo(n.toInt())) {
            val zero = Complex.ZERO

            data = pad(data) { zero }
        }

        n = data.size.toDouble()

        val even = Array(n.toInt() / 2) { k -> data[k * 2] }
        val evenFft = compute(even)

        val odd = even.mapIndexed { k, _ -> data[k * 2 + 1] }.toTypedArray()
        val oddFft = compute(odd)

        val transformed = arrayOfNulls<Complex>(n.toInt())
        for (k in 0 until n.toInt() / 2) {
            val exp = (Complex.I * -MathUtils.TWO_PI * k.toDouble() / n).exp()

            transformed[k] = evenFft[k] + exp * oddFft[k]
            transformed[k + n.toInt() / 2] = evenFft[k] - exp * oddFft[k]
        }

        return transformed
            .filterNotNull()
            .toTypedArray()
    }

    fun getFrequency(k: Double, n: Int, f: Double): Double {
         return k * f / n;
    }

    override fun computeInverse(x: Array<Complex>): Array<Complex> {
        TODO("Not yet implemented")
    }
}