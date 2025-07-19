package com.khalid.core_data.utils

import com.google.gson.annotations.SerializedName

internal fun Enum<*>.serialisedName() =
    javaClass.getField(name).getAnnotation(SerializedName::class.java)?.value ?: name.lowercase()
