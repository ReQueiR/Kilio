package com.example

import io.ktor.http.HttpStatusCode
import io.ktor.server.application.*
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.delete
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.put
import io.ktor.server.routing.routing
import org.jetbrains.exposed.v1.jdbc.*
import org.flywaydb.core.Flyway
import org.jetbrains.exposed.v1.core.dao.id.java.UUIDTable
import org.jetbrains.exposed.v1.datetime.timestampWithTimeZone

suspend fun Application.configureExposed() {
    val dbUrl = "jdbc:postgresql://localhost:5432/postgres"
    val dbUsername = "user"
    val dbPassword = "password"
    val dbDriver = "org.postgresql.Driver"

    val flyway = Flyway.configure().dataSource(dbUrl, dbUsername, dbPassword)
        .load()

    flyway.migrate()

    val database = Database.connect(dbUrl, driver = dbDriver, user = dbUsername, password = dbPassword)

    val userService = ExposedUserService(database)

    routing {
        // Create user
        post("/users") {
            val user = call.receive<ExposedUser>()
            val id = userService.create(user)
            call.respond(HttpStatusCode.Created, id)
        }

        // Read user
        get("/users/{id}") {
            val id = call.parameters["id"]?.toUInt() ?: throw IllegalArgumentException("Invalid ID")
            val user = userService.read(id)
            if (user != null) {
                call.respond(HttpStatusCode.OK, user)
            } else {
                call.respond(HttpStatusCode.NotFound)
            }
        }

        // Update user
        put("/users/{id}") {
            val id = call.parameters["id"]?.toUInt() ?: throw IllegalArgumentException("Invalid ID")
            val user = call.receive<ExposedUser>()
            userService.update(id, user)
            call.respond(HttpStatusCode.NoContent)
        }

        // Delete user
        delete("/users/{id}") {
            val id = call.parameters["id"]?.toUInt() ?: throw IllegalArgumentException("Invalid ID")
            userService.delete(id)
            call.respond(HttpStatusCode.NoContent)
        }
    }
}

object FileMetadataTable : UUIDTable("file_metadata") {
    val originalName = varchar("original_name", length = 255)
    val storedName = varchar("stored_name", length = 255).uniqueIndex()
    val type = varchar("type", length = 55)
    val sizeBytes = long("size_bytes")
    val uploadedAt = timestampWithTimeZone("uploaded_at")
}