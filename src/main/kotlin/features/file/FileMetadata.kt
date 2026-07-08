package com.example.features.file

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.util.UUID
import kotlin.time.Instant

@Serializable
data class FileMetadata(
    @Serializable(UUIDSerializer::class)
    val id: UUID,
    val originalName: String,
    val storedName: String,
    val type: String,
    val sizeBytes: Long,
    val uploadedAt: Instant,
)


object UUIDSerializer : KSerializer<UUID> {
    override val descriptor = PrimitiveSerialDescriptor("UUID", PrimitiveKind.STRING)
    override fun deserialize(decoder: Decoder): UUID {
        return UUID.fromString(decoder.decodeString())
    }

    override fun serialize(encoder: Encoder, value: UUID) {
        return encoder.encodeString(value.toString())
    }
}