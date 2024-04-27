package com.spelldnd.database.spells

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

object Spells: Table("spells_ru"){
    private val slug = com.spelldnd.database.spells.Spells.varchar("slug", 255)
    private val name = com.spelldnd.database.spells.Spells.varchar("name", 255)
    private val desc = com.spelldnd.database.spells.Spells.text("desc")
    private val higher_level = com.spelldnd.database.spells.Spells.text("higher_level")
    private val range = com.spelldnd.database.spells.Spells.text("range")
    private val components = com.spelldnd.database.spells.Spells.text("components")
    private val material = com.spelldnd.database.spells.Spells.text("material")
    private val ritual = com.spelldnd.database.spells.Spells.varchar("ritual", 25)
    private val duration = com.spelldnd.database.spells.Spells.text("duration")
    private val concentration = com.spelldnd.database.spells.Spells.varchar("concentration", 25)
    private val casting_time = com.spelldnd.database.spells.Spells.text("casting_time")
    private val level = com.spelldnd.database.spells.Spells.varchar("level", 25)
    private val level_int = com.spelldnd.database.spells.Spells.integer("level_int")
    private val school = com.spelldnd.database.spells.Spells.varchar("school", 255)
    private val dnd_class = com.spelldnd.database.spells.Spells.text("dnd_class")
    private val archetype = com.spelldnd.database.spells.Spells.text("archetype")

    fun insert(spellDTO: com.spelldnd.database.spells.SpellDTO) {
        transaction {
            com.spelldnd.database.spells.Spells.insert {
                it[com.spelldnd.database.spells.Spells.slug] = spellDTO.slug
                it[com.spelldnd.database.spells.Spells.name] = spellDTO.name
                it[com.spelldnd.database.spells.Spells.desc] = spellDTO.desc
                it[com.spelldnd.database.spells.Spells.higher_level] = spellDTO.higher_level
                it[com.spelldnd.database.spells.Spells.range] = spellDTO.range
                it[com.spelldnd.database.spells.Spells.components] = spellDTO.components
                it[com.spelldnd.database.spells.Spells.material] = spellDTO.material
                it[com.spelldnd.database.spells.Spells.ritual] = spellDTO.ritual
                it[com.spelldnd.database.spells.Spells.duration] = spellDTO.duration
                it[com.spelldnd.database.spells.Spells.concentration] = spellDTO.concentration
                it[com.spelldnd.database.spells.Spells.casting_time] = spellDTO.casting_time
                it[com.spelldnd.database.spells.Spells.level] = spellDTO.level
                it[com.spelldnd.database.spells.Spells.level_int] = spellDTO.level_int
                it[com.spelldnd.database.spells.Spells.school] = spellDTO.school
                it[com.spelldnd.database.spells.Spells.dnd_class] = spellDTO.dnd_class
                it[com.spelldnd.database.spells.Spells.archetype] = spellDTO.archetype
            }
        }
    }

    fun delete(slug: String) {
        transaction {
            com.spelldnd.database.spells.Spells.deleteWhere { com.spelldnd.database.spells.Spells.slug eq slug }
        }
    }


    fun fetchSpell(slug: String): com.spelldnd.database.spells.SpellDTO? {
        return try{
            transaction {
                val spellsModel = com.spelldnd.database.spells.Spells.select { com.spelldnd.database.spells.Spells.slug.eq(slug) }.single()
                com.spelldnd.database.spells.SpellDTO(
                    slug = spellsModel[com.spelldnd.database.spells.Spells.slug],
                    name = spellsModel[com.spelldnd.database.spells.Spells.name],
                    desc = spellsModel[com.spelldnd.database.spells.Spells.desc],
                    higher_level = spellsModel[com.spelldnd.database.spells.Spells.higher_level],
                    range = spellsModel[com.spelldnd.database.spells.Spells.range],
                    components = spellsModel[com.spelldnd.database.spells.Spells.components],
                    material = spellsModel[com.spelldnd.database.spells.Spells.material],
                    ritual = spellsModel[com.spelldnd.database.spells.Spells.ritual],
                    duration = spellsModel[com.spelldnd.database.spells.Spells.duration],
                    concentration = spellsModel[com.spelldnd.database.spells.Spells.concentration],
                    casting_time = spellsModel[com.spelldnd.database.spells.Spells.casting_time],
                    level = spellsModel[com.spelldnd.database.spells.Spells.level],
                    level_int = spellsModel[com.spelldnd.database.spells.Spells.level_int],
                    school = spellsModel[com.spelldnd.database.spells.Spells.school],
                    dnd_class = spellsModel[com.spelldnd.database.spells.Spells.dnd_class],
                    archetype = spellsModel[com.spelldnd.database.spells.Spells.archetype]
                )
            }
        } catch (e: Exception) {
            null
        }
    }

    fun fetchAll(): List<com.spelldnd.database.spells.SpellDTO> {
        return try {
            transaction {
                val spellsList = com.spelldnd.database.spells.Spells.selectAll().map {
                    com.spelldnd.database.spells.SpellDTO(
                        slug = it[com.spelldnd.database.spells.Spells.slug],
                        name = it[com.spelldnd.database.spells.Spells.name],
                        desc = it[com.spelldnd.database.spells.Spells.desc],
                        higher_level = it[com.spelldnd.database.spells.Spells.higher_level],
                        range = it[com.spelldnd.database.spells.Spells.range],
                        components = it[com.spelldnd.database.spells.Spells.components],
                        material = it[com.spelldnd.database.spells.Spells.material],
                        ritual = it[com.spelldnd.database.spells.Spells.ritual],
                        duration = it[com.spelldnd.database.spells.Spells.duration],
                        concentration = it[com.spelldnd.database.spells.Spells.concentration],
                        casting_time = it[com.spelldnd.database.spells.Spells.casting_time],
                        level = it[com.spelldnd.database.spells.Spells.level],
                        level_int = it[com.spelldnd.database.spells.Spells.level_int],
                        school = it[com.spelldnd.database.spells.Spells.school],
                        dnd_class = it[com.spelldnd.database.spells.Spells.dnd_class],
                        archetype = it[com.spelldnd.database.spells.Spells.archetype]
                    )
                }
                println(spellsList::class.simpleName)
                spellsList
            }
        } catch (e: Exception) {
            emptyList()
        }
    }
}