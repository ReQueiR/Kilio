package com.example.features.file

import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.request.receiveMultipart
import io.ktor.server.response.respond
import io.ktor.server.response.respondText
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.routing
import org.koin.ktor.ext.inject

fun Application.configureRouting() {
    val fileService by inject<FileService>()

    routing {
        post("/upload") {
            val multipartData = call.receiveMultipart()
            val result = fileService.uploadFile(multipartData, "uploads")
            call.respond(HttpStatusCode.Created, result.toResponse())
        }

        get("/ping") {
            call.respondText("PONG!")
        }

        post("/upload2") {
            //
        }
    }
}
