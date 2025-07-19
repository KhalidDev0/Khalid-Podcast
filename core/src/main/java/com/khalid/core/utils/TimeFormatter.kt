package com.khalid.core.utils

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import java.util.TimeZone

object TimeFormatter {
    private const val SECONDS_IN_MIN = 60L
    private const val MIN_IN_HOUR = 60L

    // I cant use this as it requires 26 API
    // private val isoFormatter = DateTimeFormatterBuilder()
    //     .appendInstant()
    //     .toFormatter()
    private val isoSdfWithMillis = SimpleDateFormat(
        "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",
        Locale.US
    ).apply { timeZone = TimeZone.getTimeZone("UTC") }
    private val isoSdfNoMillis = SimpleDateFormat(
        "yyyy-MM-dd'T'HH:mm:ss'Z'",
        Locale.US
    ).apply { timeZone = TimeZone.getTimeZone("UTC") }


    /**
     * @param seconds  duration in **seconds** (e.g. `382_514`).
     * @return         nicely formatted string, examples:
     *
     * * `382_514`  →  `"106h 15min"`
     * * `4_200`    →  `"1h 10min"`
     * * `590`      →  `"9 min"`
     * * `60`       →  `"1 min"`
     * * `0`        →  `"0 min"`
     */
    fun formatHrsMins(seconds: Long): String {
        if (seconds < 0) return ""

        val totalMinutes = seconds / SECONDS_IN_MIN
        val hours = totalMinutes / MIN_IN_HOUR
        val remainingMinutes = totalMinutes % MIN_IN_HOUR

        return buildString {
            if (hours > 0) {
                append(hours).append("h")
                if (remainingMinutes > 0) append(' ')
            }
            if (remainingMinutes > 0) append(remainingMinutes).append("m")
        }
    }

    /**
     * Turns an **ISO-8601 UTC** timestamp into a relative day label:
     *
     * * Same calendar day as the device → **“Today”**
     * * One day before                    → **“Yesterday”**
     * * More than one day                 → **“(n) days ago”**
     *
     * The calculation is purely date-based – it strips the time-of-day component
     * so `23:59` yesterday and `00:01` yesterday both become “Yesterday”.
     *
     * @param isoString backend timestamp like `"2024-07-23T20:00:00.000Z"`.
     * @return the relative label, or an empty string if parsing fails.
     */
    fun formatRelativeDay(isoString: String): String {
        val publishedUtc = runCatching { isoSdfWithMillis.parse(isoString) }
            .recoverCatching { isoSdfNoMillis.parse(isoString) }
            .getOrNull() ?: return ""
        val now = Calendar.getInstance()
        val published = Calendar.getInstance().apply { time = publishedUtc }

        fun Calendar.trimToDate() = apply {
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }

        val days = ((now.trimToDate().timeInMillis -
                published.trimToDate().timeInMillis) / (24 * 60 * 60 * 1000)).toInt()

        return when (days) {
            0 -> "Today"
            1 -> "Yesterday"
            else -> "$days days ago"
        }
    }
}