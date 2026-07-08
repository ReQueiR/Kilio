package com.example.features.user

import java.util.UUID
import kotlin.time.Instant

data class User(
    val id: UUID,
    val username: String,
    val email: String,
    val registeredAt: Instant
) {
}