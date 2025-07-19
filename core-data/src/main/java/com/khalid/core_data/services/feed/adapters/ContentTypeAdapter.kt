package com.khalid.core_data.services.feed.adapters

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonPrimitive
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonSerializer
import com.khalid.core_data.services.feed.dto.ContentTypeDto
import com.khalid.core_data.services.feed.dto.ContentTypeDto.UNKNOWN
import com.khalid.core_data.services.feed.dto.ContentTypeDto.entries
import com.khalid.core_data.utils.serialisedName
import java.lang.reflect.Type


internal class ContentTypeAdapter : JsonDeserializer<ContentTypeDto>,
    JsonSerializer<ContentTypeDto> {
    override fun deserialize(json: JsonElement, t: Type, ctx: JsonDeserializationContext) =
        entries.find { it.name.equals(json.asString.replace('-', '_'), true) } ?: UNKNOWN

    override fun serialize(src: ContentTypeDto, t: Type, ctx: JsonSerializationContext) =
        JsonPrimitive(src.serialisedName())
}