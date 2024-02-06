package spelldnd.features.register

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import spelldnd.cashe.InMemoryCache
import spelldnd.cashe.TokenCache
import spelldnd.utils.isValidEmail
import java.util.*


fun Application.configureRegisterRouting() {
    routing {
        post("/register") {
            val registerController = RegisterController(call)
            registerController.registerNewUser()



        }
    }
}

