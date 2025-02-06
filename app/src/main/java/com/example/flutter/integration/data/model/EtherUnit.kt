package com.example.flutter.integration.data.model

import java.math.BigDecimal
import java.math.MathContext

enum class EtherUnit(private val exponent: Int) {
    WEI(0),
    KWEI(3),
    MWEI(6),
    GWEI(9),
    SZABO(12),
    FINNEY(15),
    ETHER(18),
    KETHER(21),
    METHER(24),
    GETHER(27),
    TETHER(30);

    val weiFactor: BigDecimal
        get() = BigDecimal.TEN.pow(exponent)
}

fun String.convert(
    fromUnit: EtherUnit = EtherUnit.WEI,
    toUnit: EtherUnit = EtherUnit.ETHER
): BigDecimal {
    return try {
        val valueInWei = this.toBigDecimal().multiply(fromUnit.weiFactor)
        valueInWei.divide(toUnit.weiFactor, MathContext.DECIMAL128)
    } catch (e: Exception) {
        BigDecimal("0000000000")
    }
}