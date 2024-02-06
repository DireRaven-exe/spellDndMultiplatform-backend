package spelldnd.database.spells

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
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

    fun insert(spellsDTO: SpellsDTO) {
        transaction {
            Spells.insert {
                it[slug] = spellsDTO.slug
                it[name] = spellsDTO.name
                it[desc] = spellsDTO.desc
                it[higher_level] = spellsDTO.higher_level
                it[range] = spellsDTO.range
                it[components] = spellsDTO.components
                it[material] = spellsDTO.material
                it[ritual] = spellsDTO.ritual
                it[duration] = spellsDTO.duration
                it[concentration] = spellsDTO.concentration
                it[casting_time] = spellsDTO.casting_time
                it[level] = spellsDTO.level
                it[level_int] = spellsDTO.level_int
                it[school] = spellsDTO.school
                it[dnd_class] = spellsDTO.dnd_class
                it[archetype] = spellsDTO.archetype
            }
        }
    }

    fun delete(slug: String) {
        transaction {
            Spells.deleteWhere { Spells.slug eq slug }
        }
    }


    fun fetchSpell(slug: String): SpellsDTO? {
        return try{
            transaction {
                val spellsModel = Spells.select { Spells.slug.eq(slug) }.single()
                SpellsDTO(
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
}