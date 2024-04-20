package spelldnd.features.spells

import kotlinx.serialization.Serializable

@Serializable
data class SpellsRequest(
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
data class SpellsResponse(
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
data class FetchSpellsRequest(
    val searchQuery: String
)

data class FetchSpellRequest(
    val slug: String
)