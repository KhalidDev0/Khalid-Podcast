package com.khalid.core_data.services.feed.adapters

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonPrimitive
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonSerializer
import com.khalid.core_data.services.feed.dto.SectionLayoutDto
import com.khalid.core_data.utils.serialisedName
import java.lang.reflect.Type

internal class SectionLayoutAdapter : JsonDeserializer<SectionLayoutDto>,
    JsonSerializer<SectionLayoutDto> {
    override fun deserialize(
        json: JsonElement,
        type: Type,
        ctx: JsonDeserializationContext
    ): SectionLayoutDto =
        SectionLayoutDto.entries
            .firstOrNull { it.serialisedName().equals(json.asString, ignoreCase = true) }
            ?: SectionLayoutDto.BIG_SQUARE

    override fun serialize(
        src: SectionLayoutDto,
        type: Type,
        ctx: JsonSerializationContext
    ): JsonElement = JsonPrimitive(src.serialisedName())
}