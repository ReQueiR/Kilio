package com.example

import com.example.config.configureDatabase
import com.example.features.file.FileRepository
import com.example.features.file.FileService
import com.example.features.file.configureRouting
import io.ktor.server.application.Application
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty

fun main(args: Array<String>) {
    embeddedServer(Netty, port = 8080, module = Application::module).start(wait = true)
}

fun Application.module() {
    println("Configuring database...")
    configureDatabase()
    println("Configuring routes...")
    configureRouting()
    println("Done...")
}