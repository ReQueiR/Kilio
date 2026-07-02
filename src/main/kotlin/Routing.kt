package com.example

import io.ktor.http.HttpStatusCode
import io.ktor.server.application.*
import io.ktor.server.request.receive
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    var txt: String = ""

    routing {
        post {
            try {
                txt = call.receive<String>()
                call.respond(HttpStatusCode.OK, "Good job!")
            } catch (_: IllegalStateException) {
                call.respond(HttpStatusCode.BadRequest, "Failed!")
            }
        }
        get { call.respondText(txt) }
    }
}