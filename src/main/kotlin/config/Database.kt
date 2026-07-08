package com.example.config

import com.example.features.file.FileService
import com.example.features.link.LinkService
import com.example.features.user.UserService
import io.ktor.server.application.*
import org.flywaydb.core.Flyway
import org.jetbrains.exposed.v1.core.dao.id.java.UUIDTable
import org.jetbrains.exposed.v1.datetime.CurrentTimestamp
import org.jetbrains.exposed.v1.datetime.timestampWithTimeZone
import org.jetbrains.exposed.v1.jdbc.*

suspend fun Application.configureDatabase() {
    val dbUrl = "jdbc:postgresql://localhost:5432/fileserver"
    val dbUsername = "user"
    val dbPassword = "password"
    val dbDriver = "org.postgresql.Driver"

    val flyway = Flyway.configure().dataSource(dbUrl, dbUsername, dbPassword)
        .load()

    flyway.migrate()

    val database = Database.connect(dbUrl, driver = dbDriver, user = dbUsername, password = dbPassword)

    val userService = UserService(database)
    val linkService = LinkService(database)
}

object FileMetadataTable : UUIDTable("file_metadata") {
    val originalName = varchar("original_name", length = 255)
    val storedName = varchar("stored_name", length = 255).uniqueIndex()
    val type = varchar("type", length = 55)
    val sizeBytes = long("size_bytes")
    val uploadedAt = timestampWithTimeZone("uploaded_at")
}

object UserTable : UUIDTable("user") {
    val name = varchar("user_name", length = 255).uniqueIndex()
    val email = varchar("email", length = 255).uniqueIndex()
    val registeredAt = timestampWithTimeZone("registered_at")
}