package com.spelldnd.features.register

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import com.spelldnd.cashe.InMemoryCache
import com.spelldnd.cashe.TokenCache
import com.spelldnd.utils.isValidEmail
import java.util.*


fun Application.configureRegisterRouting() {
    routing {
        post("/register") {
            val registerController = RegisterController(call)
            registerController.registerNewUser()



        }
    }
}

