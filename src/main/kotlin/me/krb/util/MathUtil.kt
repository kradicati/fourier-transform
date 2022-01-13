package me.krb.util

import org.apache.commons.math3.complex.Complex
import kotlin.math.ceil
import kotlin.math.log
import kotlin.math.pow

fun complexSum(
    function: (Int) -> Complex,
    start: Int, end: Int
): Complex {
    var sum = Complex.ZERO

    for (i in start until start + end) {
        sum += function(i)
    }

    return sum
}

fun isPowerOfTwo(x: Int): Boolean {
    return (x != 0) && ((x and (x - 1)) == 0)
}

inline fun <reified T> pad(x: Array<T>, noinline padFunction: (Int) -> T): Array<T> {
    val size = getClosestPowerOf2(x.size.toDouble())

    val toReturn = Array(size, padFunction)

    x.copyInto(toReturn)

    return toReturn
}

fun getClosestPowerOf2(size: Double): Int {
    return 2.0.pow(ceil(log(size, 2.0) / log(2.0, 2.0))).toInt()
}

operator fun Complex.plus(addend: Complex): Complex = add(addend)

operator fun Complex.plus(addend: Double): Complex = add(addend)

operator fun Complex.times(addend: Complex): Complex = multiply(addend)

operator fun Complex.times(addend: Double): Complex = multiply(addend)

operator fun Complex.div(addend: Complex): Complex = divide(addend)

operator fun Complex.div(addend: Double): Complex = divide(addend)

operator fun Complex.minus(addend: Complex): Complex = subtract(addend)

operator fun Complex.minus(addend: Double): Complex = subtract(addend)