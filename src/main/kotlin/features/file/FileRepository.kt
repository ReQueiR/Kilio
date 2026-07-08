package com.example.features.file

import com.example.config.FileMetadataTable
import com.example.config.FileMetadataTable.id
import com.example.config.FileMetadataTable.originalName
import com.example.config.FileMetadataTable.sizeBytes
import com.example.config.FileMetadataTable.storedName
import com.example.config.FileMetadataTable.type
import com.example.config.FileMetadataTable.uploadedAt
import org.jetbrains.exposed.v1.core.eq
import org.jetbrains.exposed.v1.jdbc.insert
import org.jetbrains.exposed.v1.jdbc.selectAll
import org.jetbrains.exposed.v1.jdbc.transactions.transaction
import java.util.*

class FileRepository  {
    fun save(file: FileMetadata) {
        transaction {
            FileMetadataTable.insert {
                it[id] = UUID.randomUUID()
                it[originalName] = file.originalName
                it[storedName] = file.storedName
                it[type] = file.type.name
                it[sizeBytes] = file.sizeBytes
            }
        }
    }

    fun findAll(): List<FileMetadata> {
        return FileMetadataTable.selectAll()
            .map { row -> FileMetadata(
                id = row[id].value,
                originalName = row[originalName],
                storedName = row[storedName],
                type = FileCategory.valueOf(row[type]),
                sizeBytes = row[sizeBytes],
                uploadedAt = row[uploadedAt]
            )
        }
    }

    fun findById(id: UUID): FileMetadata? {
        return FileMetadataTable.selectAll()
            .where { FileMetadataTable.id eq id }
            .map { row -> FileMetadata(
                id = row[FileMetadataTable.id].value,
                originalName = row[originalName],
                storedName = row[storedName],
                type = FileCategory.valueOf(row[type]),
                sizeBytes = row[sizeBytes],
                uploadedAt = row[uploadedAt]
            )}
            .singleOrNull()
    }
}