package com.example.features.file

import kotlinx.serialization.Serializable

@Serializable
data class FileMetadataResponse(
    val id: String,
    val originalName: String,
    val storedName: String,
    val category: String,
    val type: String,
    val sizeBytes: Long,
    val uploadedAt: String,
) {
}

fun FileMetadata.toResponse() = FileMetadataResponse(
    id = id.toString(),
    originalName = originalName,
    storedName = storedName,
    category = category.name,
    type = type,
    sizeBytes = sizeBytes,
    uploadedAt = uploadedAt.toString()
)