package com.example.democompouseapp.domain.mapper

import com.example.democompouseapp.data.model.CharacterResponse
import com.example.democompouseapp.domain.mapper.base.Mapper
import com.example.democompouseapp.domain.model.Character
import com.example.democompouseapp.domain.model.Location
import com.example.democompouseapp.domain.model.Origin
import com.example.democompouseapp.domain.model.getCharacterStatusEnum
import javax.inject.Inject

class CharacterMapper @Inject constructor(): Mapper<CharacterResponse, Character>() {
    override fun map(from: CharacterResponse) = from.run {
        Character(
            id = id,
            name = name,
            status = getCharacterStatusEnum(status),
            species = species,
            type = type,
            gender = gender,
            origin = Origin(
                origin.name,
                origin.url
            ),
            image = image,
            url = url,
            location = Location(
                name = name,
                url = url
            ),
            episode = episode
        )
    }
}