package com.example.features.file

import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.utils.io.*
import kotlinx.io.readByteArray
import java.io.File
import java.time.OffsetDateTime
import java.util.*

class FileService(val repository: FileRepository) {
    fun getFile(id: UUID): FileMetadata? {
        return repository.findById(id)
    }

    suspend fun uploadFile(multiPartData: MultiPartData, dir: String): FileMetadata {

        var uploadedFile: FileMetadata? = null

        multiPartData.forEachPart { part ->
            when (part) {
                is PartData.FileItem -> {
                    val fileName = part.originalFileName ?: "unknown"
                    val fileType = part.contentType ?: ContentType.Application.OctetStream

                    val category = when {
                        fileType.match(ContentType.Image.Any) -> FileCategory.IMAGE
                        fileType.match(ContentType.Audio.Any) -> FileCategory.SOUND
                        fileType.match(ContentType.Video.Any) -> FileCategory.VIDEO
                        fileType.match(ContentType.Application.Any) -> FileCategory.DOCUMENT
                        fileType.match(ContentType.Text.Any) -> FileCategory.DOCUMENT
                        else -> FileCategory.OTHER
                    }

                    val storedName = UUID.randomUUID().toString()

                    val file = File(dir, storedName)
                    val bytes = part.provider().readRemaining().readByteArray()
                    file.writeBytes(bytes)

                    uploadedFile = FileMetadata(
                        id = UUID.randomUUID(),
                        originalName = fileName,
                        storedName = storedName,
                        category = category,
                        type = fileType.toString(),
                        sizeBytes = bytes.size.toLong(),
                        uploadedAt = OffsetDateTime.now(),
                    )

                    repository.save(uploadedFile)
                }

                else -> Unit
            }
            part.release()
        }

        return uploadedFile ?: throw IllegalStateException("Unable to upload file")
    }
}