package com.khalid.core.utils

import android.content.Context
import android.widget.Toast
import kotlinx.coroutines.flow.MutableStateFlow

fun <T> T?.isNull(): Boolean = this == null
fun <T> T?.isNotNull(): Boolean = this != null
fun String?.isNotNullOrBlank(): Boolean = !this.isNullOrBlank()
fun <T> T?.orDefault(default: T): T = this ?: default

inline fun <T> T?.ifNotNull(block: (T) -> Unit) {
    this?.let(block)
}

inline fun <T> T?.ifNull(block: () -> Unit) {
    if (this == null) block()
}

inline fun <T> List<T>.ifNotEmpty(block: (List<T>) -> Unit) {
    if (this.isNotEmpty()) block(this)
}

inline fun <T> List<T>?.ifNotNullOrEmpty(block: (List<T>) -> Unit) {
    if (this?.isNotEmpty() == true) block(this)
}

inline fun <T> List<T>?.ifNullOrEmpty(block: () -> Unit) {
    if (this.isNullOrEmpty()) block()
}

inline fun <T> List<T>.ifEmpty(block: (List<T>) -> Unit) {
    if (this.isEmpty()) block(this)
}

inline fun <T> MutableStateFlow<T>.update(block: T.() -> T) {
    this.value = block(this.value)
}

inline fun <reified T> Any?.safeCast(default: T): T = this as? T ?: default

fun Context.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}