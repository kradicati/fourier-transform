package me.krb.transform

import org.apache.commons.math3.complex.Complex

interface FourierTransform {

    fun compute(x: Array<Complex>): Array<Complex>

    @JvmDefault
    fun compute(x: DoubleArray): Array<Complex> {
        return compute(x.map { d -> Complex(d) }.toTypedArray())
    }

    fun computeInverse(x: Array<Complex>): Array<Complex>

    @JvmDefault
    fun computeInverse(x: DoubleArray): Array<Complex> {
        return compute(x.map { d -> Complex(d) }.toTypedArray())
    }

}