package spelldnd.features.login

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import spelldnd.cashe.InMemoryCache
import spelldnd.cashe.TokenCache
import spelldnd.features.register.RegisterReceiveRemote
import spelldnd.plugins.Test
import java.util.UUID

fun Application.configureLoginRouting() {
    routing {
        post("/login") {
            val loginController = LoginController(call)
            loginController.performLogin()
        }
    }
}
