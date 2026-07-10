package com.example.features.file

import org.jetbrains.exposed.v1.core.dao.id.java.UUIDTable
import org.jetbrains.exposed.v1.datetime.timestampWithTimeZone

object FileMetadataTable : UUIDTable("file_metadata") {
    val originalName = varchar("original_name", length = 255)
    val storedName = varchar("stored_name", length = 255).uniqueIndex()
    val category = varchar("category", length = 255)
    val type = varchar("type", length = 55)
    val sizeBytes = long("size_bytes")
    val uploadedAt = timestampWithTimeZone("uploaded_at")
}