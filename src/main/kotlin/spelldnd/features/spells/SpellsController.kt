package spelldnd.features.spells

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import org.jetbrains.exposed.exceptions.ExposedSQLException
import spelldnd.database.spells.SpellDTO
import spelldnd.database.spells.Spells

class SpellsController(private val call: ApplicationCall) {
    suspend fun createSpell() {
        val receive = call.receive<SpellsRequest>()

        if (receive.slug.isEmpty()) {
            call.respond(HttpStatusCode.BadRequest, "slug is empty")
        }
        val spellsDTO = Spells.fetchSpell(receive.slug)

        if (spellsDTO != null) {
            call.respond(HttpStatusCode.Conflict, "User already exists")
        } else {

            try {
                Spells.insert(
                    SpellDTO(
                        slug = receive.slug,
                        name = receive.name,
                        desc = receive.desc,
                        higher_level = receive.higher_level,
                        range = receive.range,
                        components = receive.components,
                        material = receive.material,
                        ritual = receive.ritual,
                        duration = receive.duration,
                        concentration = receive.concentration,
                        casting_time = receive.casting_time,
                        level = receive.level,
                        level_int = receive.level_int,
                        school = receive.school,
                        dnd_class = receive.dnd_class,
                        archetype = receive.archetype
                    )
                )
            } catch (e: ExposedSQLException) {
                call.respond(HttpStatusCode.Conflict, "Spell already exists")
            }

            call.respond("Spell added")
        }
    }


    suspend fun addSpells() {
        val spellsList = call.receive<List<SpellsRequest>>()

        if (spellsList.isEmpty()) {
            call.respond(HttpStatusCode.BadRequest, "Empty list of spells")
            return
        }

        for (spell in spellsList) {
            if (spell.slug.isEmpty()) {
                call.respond(HttpStatusCode.BadRequest, "Slug is empty")
                return
            }
            val spellsDTO = Spells.fetchSpell(spell.slug)
            if (spellsDTO != null) {
                call.respond(HttpStatusCode.Conflict, "Spell with slug ${spell.slug} already exists")
                return
            } else {
                try {
                    Spells.insert(
                        SpellDTO(
                            slug = spell.slug,
                            name = spell.name,
                            desc = spell.desc,
                            higher_level = spell.higher_level,
                            range = spell.range,
                            components = spell.components,
                            material = spell.material,
                            ritual = spell.ritual,
                            duration = spell.duration,
                            concentration = spell.concentration,
                            casting_time = spell.casting_time,
                            level = spell.level,
                            level_int = spell.level_int,
                            school = spell.school,
                            dnd_class = spell.dnd_class,
                            archetype = spell.archetype
                        )
                    )
                } catch (e: ExposedSQLException) {
                    call.respond(HttpStatusCode.Conflict, "Spell with slug ${spell.slug} already exists")
                    return
                }
            }
        }

        call.respond("Spells added")
    }

    suspend fun deleteSpell() {
        val receive = call.receive<SpellsRequest>()

        val spell = Spells.fetchSpell(receive.slug)
        if (spell == null) {
            call.respond(HttpStatusCode.NotFound, "Spell not found")
            return
        }

        Spells.delete(receive.slug)
        call.respond("Spell deleted")
    }

    suspend fun deleteSpells() {
        val receive = call.receive<List<SpellsRequest>>()

        if (receive.isEmpty()) {
            call.respond(HttpStatusCode.BadRequest, "Empty list of spells")
            return
        }

        for (spell in receive) {
            if (spell.slug.isEmpty()) {
                call.respond(HttpStatusCode.BadRequest, "Slug is empty")
                return
            }

            try {
                Spells.delete(slug = spell.slug)
            } catch (e: ExposedSQLException) {
                call.respond(HttpStatusCode.Conflict, "Spell with slug ${spell.slug} already exists")
                return
            }

        }

        call.respond("Spells deleted")
    }

    suspend fun performSearch() {
        val searchQuery = call.parameters["searchQuery"] ?: ""
        val searchProperties = call.parameters.getAll("searchProperty")?.toSet() ?: emptySet()

        if (searchQuery.isBlank()) {
            call.respond(Spells.fetchAll())
        } else {
            val filteredSpells = Spells.fetchAll().filter { spell ->
                if (searchProperties.isNotEmpty()) {
                    searchProperties.any { property ->
                        when (property) {
                            "name" -> spell.name.contains(searchQuery, ignoreCase = true)
                            "desc" -> spell.desc.contains(searchQuery, ignoreCase = true)
                            "higher_level" -> spell.higher_level.contains(searchQuery, ignoreCase = true)
                            "range" -> spell.range.contains(searchQuery, ignoreCase = true)
                            "components" -> spell.components.contains(searchQuery, ignoreCase = true)
                            "material" -> spell.material.contains(searchQuery, ignoreCase = true)
                            "ritual" -> spell.ritual.contains(searchQuery, ignoreCase = true)
                            "duration" -> spell.duration.contains(searchQuery, ignoreCase = true)
                            "concentration" -> spell.concentration.contains(searchQuery, ignoreCase = true)
                            "casting_time" -> spell.casting_time.contains(searchQuery, ignoreCase = true)
                            "level" -> spell.level.contains(searchQuery, ignoreCase = true)
                            "school" -> spell.school.contains(searchQuery, ignoreCase = true)
                            "dnd_class" -> spell.dnd_class.contains(searchQuery, ignoreCase = true)
                            "archetype" -> spell.archetype.contains(searchQuery, ignoreCase = true)
                            else -> false
                        }
                    }
                } else {
                    // Если свойство не выбрано, ищем по всем свойствам
                    spell.name.contains(searchQuery, ignoreCase = true) ||
                            spell.desc.contains(searchQuery, ignoreCase = true) ||
                            spell.higher_level.contains(searchQuery, ignoreCase = true) ||
                            spell.range.contains(searchQuery, ignoreCase = true) ||
                            spell.components.contains(searchQuery, ignoreCase = true) ||
                            spell.material.contains(searchQuery, ignoreCase = true) ||
                            spell.ritual.contains(searchQuery, ignoreCase = true) ||
                            spell.duration.contains(searchQuery, ignoreCase = true) ||
                            spell.concentration.contains(searchQuery, ignoreCase = true) ||
                            spell.casting_time.contains(searchQuery, ignoreCase = true) ||
                            spell.level.contains(searchQuery, ignoreCase = true) ||
                            spell.school.contains(searchQuery, ignoreCase = true) ||
                            spell.dnd_class.contains(searchQuery, ignoreCase = true) ||
                            spell.archetype.contains(searchQuery, ignoreCase = true)
                }
            }
            call.respond(filteredSpells)
        }
    }

    suspend fun getSpells() {
        call.respond(Spells.fetchAll().toList())
    }

    suspend fun getSpell(slug: String) {
        if (slug.isBlank()) {
            call.respond(HttpStatusCode.BadRequest, "Slug parameter is missing")
            return
        }

        val spell = Spells.fetchSpell(slug)
        if (spell == null) {
            call.respond(HttpStatusCode.NotFound, "Spell not found")
        } else {
            call.respond(spell)
        }
    }
}