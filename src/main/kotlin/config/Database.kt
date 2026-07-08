package com.example.config

import com.example.features.file.FileRepository
import com.example.features.link.LinkService
import com.example.features.user.UserService
import io.ktor.server.application.*
import io.ktor.server.routing.*
import org.flywaydb.core.Flyway
import org.jetbrains.exposed.v1.core.dao.id.java.UUIDTable
import org.jetbrains.exposed.v1.datetime.timestampWithTimeZone
import org.jetbrains.exposed.v1.jdbc.*

suspend fun Application.configureExposed() {
    val dbUrl = "jdbc:postgresql://localhost:5432/postgres"
    val dbUsername = "user"
    val dbPassword = "password"
    val dbDriver = "org.postgresql.Driver"

    val flyway = Flyway.configure().dataSource(dbUrl, dbUsername, dbPassword)
        .load()

    flyway.migrate()

    val database = Database.connect(dbUrl, driver = dbDriver, user = dbUsername, password = dbPassword)

    val fileService = FileRepository(database)
    val userService = UserService(database)
    val linkService = LinkService(database)

    routing {

    }
}

object FileMetadataTable : UUIDTable("file_metadata") {
    val originalName = varchar("original_name", length = 255)
    val storedName = varchar("stored_name", length = 255).uniqueIndex()
    val type = varchar("type", length = 55)
    val sizeBytes = long("size_bytes")
    val uploadedAt = timestampWithTimeZone("uploaded_at")
}