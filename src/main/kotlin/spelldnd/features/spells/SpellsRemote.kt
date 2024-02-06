package spelldnd.features.spells

import kotlinx.serialization.Serializable

@Serializable
data class SpellsReceiveRemote(
    val slug: String,
    val name: String,
    val desc: String,
    val higher_level: String,
    val range: String,
    val components: String,
    val material: String,
    val ritual: String,
    val duration: String,
    val concentration: String,
    val casting_time: String,
    val level: String,
    val level_int: Int,
    val school: String,
    val dnd_class: String,
    val archetype: String,
)

@Serializable
data class SpellsResponseRemote(
    val slug: String,
    val name: String,
    val desc: String,
    val higher_level: String,
    val range: String,
    val components: String,
    val material: String,
    val ritual: String,
    val duration: String,
    val concentration: String,
    val casting_time: String,
    val level: String,
    val level_int: Int,
    val school: String,
    val dnd_class: String,
    val archetype: String,
)