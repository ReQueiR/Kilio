package com.example.config

import com.example.features.link.LinkService
import com.example.features.users.UserService
import io.ktor.server.application.*
import org.flywaydb.core.Flyway
import org.jetbrains.exposed.v1.core.dao.id.java.UUIDTable
import org.jetbrains.exposed.v1.datetime.timestampWithTimeZone
import org.jetbrains.exposed.v1.jdbc.*

fun Application.configureDatabase() {
    val dbUrl = "jdbc:postgresql://localhost:5432/fileserver"
    val dbUsername = "user"
    val dbPassword = "password"
    val dbDriver = "org.postgresql.Driver"

    val flyway = Flyway.configure()
        .dataSource(dbUrl, dbUsername, dbPassword)
        .load()

    flyway.migrate()

    val database = Database.connect(dbUrl, driver = dbDriver, user = dbUsername, password = dbPassword)
}