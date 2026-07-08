package com.example.features.file

import java.time.OffsetDateTime
import java.util.*

data class FileMetadata(
    val id: UUID,
    val originalName: String,
    val storedName: String,
    val type: FileCategory,
    val sizeBytes: Long,
    val uploadedAt: OffsetDateTime,
)