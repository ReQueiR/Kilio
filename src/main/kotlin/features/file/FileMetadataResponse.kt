package com.example.features.file

import kotlinx.serialization.Serializable

@Serializable
data class FileMetadataResponse(
    val id: String,
    val originalName: String,
    val storedName: String,
    val type: String,
    val sizeBytes: Long,
    val uploadedAt: String,
) {
}

fun FileMetadata.toResponse() = FileMetadataResponse(
    id = id.toString(),
    originalName = originalName,
    storedName = storedName,
    type = type.name,
    sizeBytes = sizeBytes,
    uploadedAt = uploadedAt.toString()
)