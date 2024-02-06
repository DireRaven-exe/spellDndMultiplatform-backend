package spelldnd.features.spells

import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureSpellsRouting() {
    routing {
        post("/spells-ru/add-spell") {
            val spellsController = SpellsController(call)
            spellsController.addSpell()
        }
        post("/spells-ru/add-spells") {
            val spellsController = SpellsController(call)
            spellsController.addSpells()
            spellsController.deleteSpell()
        }
        post("/spells-ru/delete-spell") {
            val spellsController = SpellsController(call)
            spellsController.deleteSpell()
        }
        post("/spells-ru/delete-spells") {
            val spellsController = SpellsController(call)
            spellsController.deleteSpells()
        }

    }
}