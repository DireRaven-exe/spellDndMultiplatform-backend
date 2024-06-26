package com.spelldnd.features.login

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import com.spelldnd.cashe.InMemoryCache
import com.spelldnd.cashe.TokenCache
import com.spelldnd.features.register.RegisterReceiveRemote
import com.spelldnd.plugins.Test
import java.util.UUID

fun Application.configureLoginRouting() {
    routing {
        post("/login") {
            val loginController = LoginController(call)
            loginController.performLogin()
        }
    }
}
