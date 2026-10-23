package com.example

import com.example.config.configureDatabase
import com.example.features.file.configureRouting
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main() {
    embeddedServer(Netty, port = 8080, module = Application::module).start(wait = true)
}

fun Application.module() {
    println("Configuring database...")
    configureDatabase()
    println("Configuring routes...")
    configureRouting()
    println("Done...")
}