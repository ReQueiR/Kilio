package com.example.features.file

import java.time.OffsetDateTime
import java.util.*

data class FileMetadata(
    val id: UUID,
    val originalName: String,
    val storedName: String,
    val category: FileCategory,
    val type: String,
    val sizeBytes: Long,
    val uploadedAt: OffsetDateTime,
)