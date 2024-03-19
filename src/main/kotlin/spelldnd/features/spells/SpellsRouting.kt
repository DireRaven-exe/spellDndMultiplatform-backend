package spelldnd.features.spells

import io.ktor.server.application.*
import io.ktor.server.routing.*

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
        post("/spells/search") {
            SpellsController(call).performSearch()
        }

        get("/spells") {
            SpellsController(call).getSpells()
        }

    }
}