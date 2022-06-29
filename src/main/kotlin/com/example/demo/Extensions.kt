package com.example.demo

import java.time.LocalDateTime
import java.time.format.DateTimeFormatterBuilder
import java.time.temporal.ChronoField
import java.util.*

private const val DAY_START = 1
private const val DAY_END = 31
private const val DECIMAL = 10
private const val ELEVEN_TH = 11
private const val THIRTEEN_TH = 13
private const val FIR_ST = 1
private const val SECO_ND = 2
private const val THI_RD = 3

fun LocalDateTime.format(): String = this.format(englishDateFormatter)

private val daysLookup = (DAY_START..DAY_END).associate { it.toLong() to getOrdinal(it) }

private val englishDateFormatter = DateTimeFormatterBuilder()
    .appendPattern("yyyy-MM-dd")
    .appendLiteral(" ")
    .appendText(ChronoField.DAY_OF_MONTH, daysLookup)
    .appendLiteral(" ")
    .appendPattern("yyyy")
    .toFormatter(Locale.ENGLISH)

private fun getOrdinal(n: Int) = when {
    n in ELEVEN_TH..THIRTEEN_TH -> "${n}th"
    n % DECIMAL == FIR_ST -> "${n}st"
    n % DECIMAL == SECO_ND -> "${n}nd"
    n % DECIMAL == THI_RD -> "${n}rd"
    else -> "${n}th"
}

fun String.toSlug() = lowercase(Locale.getDefault())
    .replace("\n", " ")
    .replace("[^a-z\\d\\s]".toRegex(), " ")
    .split(" ")
    .joinToString("-")
    .replace("-+".toRegex(), "-")
