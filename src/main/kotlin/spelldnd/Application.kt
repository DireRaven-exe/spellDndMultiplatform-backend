package spelldnd

import io.ktor.server.application.*
import io.ktor.server.cio.*
import io.ktor.server.engine.*
import org.jetbrains.exposed.sql.Database
import spelldnd.features.login.configureLoginRouting
import spelldnd.features.register.configureRegisterRouting
import spelldnd.features.spells.configureSpellsRouting
import spelldnd.plugins.*

fun main() {
    Database.connect(
        url = "jdbc:postgresql://localhost:5432/spelldnd",
        driver = "org.postgresql.Driver",
        user = "postgres",
        password = "rfvsikjdrf1"
    )

    embeddedServer(CIO, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    configureRouting()
    configureLoginRouting()
    configureRegisterRouting()
    configureSpellsRouting()
    configureSerialization()
}
