package com.example.model

data class FileMetadata(
    val id: Int,
    val originalName: String,
    val storedName: String,
    val type: String,
    val uploadedAt: String,
    val uploadedBy: String
)
