package me.krb.transform

import org.apache.commons.math3.complex.Complex

interface FourierTransform {

    fun compute(x: Array<Complex>): Array<Complex>

    fun computeInverse(x: Array<Complex>): Array<Complex>

}