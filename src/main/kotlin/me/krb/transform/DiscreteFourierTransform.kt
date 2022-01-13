package me.krb.transform

import me.krb.util.complexSum
import me.krb.util.div
import me.krb.util.times
import org.apache.commons.math3.complex.Complex
import org.apache.commons.math3.util.MathUtils

class DiscreteFourierTransform : FourierTransform {

    override fun compute(x: Array<Complex>): Array<Complex> {
        return x
            .mapIndexed { k, _ -> computeIndex(x, k) }
            .toTypedArray()
    }

    private fun computeIndex(x: Array<Complex>, k: Int): Complex {
        val n = x.size.toDouble()

        val partialExp = Complex.I * -MathUtils.TWO_PI * k.toDouble() / n

        val summand = fun(j: Int): Complex {
            val w = (partialExp * j.toDouble()).exp()

            return w * x[j]
        }

        return complexSum(summand, 0, n.toInt() - 1)
    }

    override fun computeInverse(x: Array<Complex>): Array<Complex> {
        return x
            .mapIndexed { k, _ -> computeInverseIndex(x, k) }
            .toTypedArray()
    }

    private fun computeInverseIndex(x: Array<Complex>, k: Int): Complex {
        val n = x.size.toDouble()

        val exp = (Complex.I * MathUtils.TWO_PI * k.toDouble() / n).exp()

        val summand = fun(j: Int): Complex = exp * x[j]

        return complexSum(summand, 0, n.toInt() - 1) / n
    }
}