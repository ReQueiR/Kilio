package com.example

import com.example.model.FileMetadata
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.io.File

fun Application.configureRouting() {
    routing {
        get("/file{id}") {
            val file = FileMetadata(call.parameters["id"]!!)
            call.respondFile(File("/storage/$file.storedName"))
        }
    }
}