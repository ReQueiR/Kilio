package com.example.plugins

import com.example.config.HelloService
import com.example.features.file.FileRepository
import com.example.features.file.FileService
import io.ktor.server.application.*
import org.koin.dsl.module
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger

fun Application.configureKoin() {
    install(Koin) {
        modules(module {
            single { FileRepository() }
            single { FileService(get()) }
        })
    }
}
