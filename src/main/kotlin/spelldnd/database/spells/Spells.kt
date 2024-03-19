package spelldnd.database.spells

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

object Spells: Table("spells_ru"){
    private val slug = Spells.varchar("slug", 255)
    private val name = Spells.varchar("name", 255)
    private val desc = Spells.text("desc")
    private val higher_level = Spells.text("higher_level")
    private val range = Spells.text("range")
    private val components = Spells.text("components")
    private val material = Spells.text("material")
    private val ritual = Spells.varchar("ritual", 25)
    private val duration = Spells.text("duration")
    private val concentration = Spells.varchar("concentration", 25)
    private val casting_time = Spells.text("casting_time")
    private val level = Spells.varchar("level", 25)
    private val level_int = Spells.integer("level_int")
    private val school = Spells.varchar("school", 255)
    private val dnd_class = Spells.text("dnd_class")
    private val archetype = Spells.text("archetype")

    fun insert(spellDTO: SpellDTO) {
        transaction {
            Spells.insert {
                it[slug] = spellDTO.slug
                it[name] = spellDTO.name
                it[desc] = spellDTO.desc
                it[higher_level] = spellDTO.higher_level
                it[range] = spellDTO.range
                it[components] = spellDTO.components
                it[material] = spellDTO.material
                it[ritual] = spellDTO.ritual
                it[duration] = spellDTO.duration
                it[concentration] = spellDTO.concentration
                it[casting_time] = spellDTO.casting_time
                it[level] = spellDTO.level
                it[level_int] = spellDTO.level_int
                it[school] = spellDTO.school
                it[dnd_class] = spellDTO.dnd_class
                it[archetype] = spellDTO.archetype
            }
        }
    }

    fun delete(slug: String) {
        transaction {
            Spells.deleteWhere { Spells.slug eq slug }
        }
    }


    fun fetchSpell(slug: String): SpellDTO? {
        return try{
            transaction {
                val spellsModel = Spells.select { Spells.slug.eq(slug) }.single()
                SpellDTO(
                    slug = spellsModel[Spells.slug],
                    name = spellsModel[Spells.name],
                    desc = spellsModel[Spells.desc],
                    higher_level = spellsModel[Spells.higher_level],
                    range = spellsModel[Spells.range],
                    components = spellsModel[Spells.components],
                    material = spellsModel[Spells.material],
                    ritual = spellsModel[Spells.ritual],
                    duration = spellsModel[Spells.duration],
                    concentration = spellsModel[Spells.concentration],
                    casting_time = spellsModel[Spells.casting_time],
                    level = spellsModel[Spells.level],
                    level_int = spellsModel[Spells.level_int],
                    school = spellsModel[Spells.school],
                    dnd_class = spellsModel[Spells.dnd_class],
                    archetype = spellsModel[Spells.archetype]
                )
            }
        } catch (e: Exception) {
            null
        }
    }

    fun fetchAll(): List<SpellDTO> {
        return try {
            transaction {
                val spellsList = Spells.selectAll().map {
                    SpellDTO(
                        slug = it[slug],
                        name = it[name],
                        desc = it[desc],
                        higher_level = it[higher_level],
                        range = it[range],
                        components = it[components],
                        material = it[material],
                        ritual = it[ritual],
                        duration = it[Spells.duration],
                        concentration = it[concentration],
                        casting_time = it[casting_time],
                        level = it[level],
                        level_int = it[level_int],
                        school = it[school],
                        dnd_class = it[dnd_class],
                        archetype = it[archetype]
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