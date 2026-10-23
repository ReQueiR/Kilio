package com.example.plugins

import com.example.features.file.FileRepository
import com.example.features.file.FileService
import io.ktor.server.application.*
import org.koin.dsl.module
import org.koin.ktor.plugin.Koin

fun Application.configureKoin() {
    install(Koin) {
        modules(module {
            single { FileRepository() }
            single { FileService(get()) }
        })
    }
}
