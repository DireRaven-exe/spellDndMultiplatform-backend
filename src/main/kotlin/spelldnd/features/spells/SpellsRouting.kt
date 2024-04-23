package spelldnd.features.spells

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.routing.*
import io.ktor.server.response.respond

fun Application.configureSpellsRouting() {
    routing {
        post("/spells/create") {
            SpellsController(call).createSpell()
        }
        post("/spells/add-spells") {
            SpellsController(call).addSpells()
        }
        post("/spells/delete") {
            SpellsController(call).deleteSpell()
        }
        post("/spells/delete-spells") {
            SpellsController(call).deleteSpells()
        }
        get("/spells/search") {
            SpellsController(call).performSearch()
        }

        get("/spells") {
            SpellsController(call).getSpells()
        }
        get("/spells/{slug}") {
            val slug = call.parameters["slug"]
            if (slug != null) {
                SpellsController(call).getSpell(slug)
            } else {
                call.respond(HttpStatusCode.BadRequest, "Slug parameter is missing")
            }
        }
    }
}