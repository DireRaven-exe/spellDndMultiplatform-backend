import io.ktor.server.application.*
import io.ktor.server.cio.*
import io.ktor.server.engine.*
import org.jetbrains.exposed.sql.Database
import com.spelldnd.features.login.configureLoginRouting
import com.spelldnd.features.register.configureRegisterRouting
import com.spelldnd.features.spells.configureSpellsRouting
import com.spelldnd.plugins.*
import java.io.File

@Throws(Exception::class)
fun main() {
    val file = File("example.txt")
    try {
        //throw Exception("Hi There!")

        Database.connect(
            url = "jdbc:postgresql://localhost:5432/spelldnd",
            driver = "org.postgresql.Driver",
            user = "postgres",
            password = "rfvsikjdrf1"
        )

        embeddedServer(CIO, port = 8080, host = "0.0.0.0", module = Application::module)
            .start(wait = true)
    } catch (e: Exception) {
        file.appendText(e.toString() + "\n")
    }
}

fun Application.module() {
    configureLoginRouting()
    configureRegisterRouting()
    configureSpellsRouting()
    configureSerialization()
}
