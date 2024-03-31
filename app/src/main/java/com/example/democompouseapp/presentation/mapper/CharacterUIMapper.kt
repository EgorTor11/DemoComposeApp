package com.example.democompouseapp.presentation.mapper

import com.example.democompouseapp.domain.mapper.base.Mapper
import com.example.democompouseapp.domain.model.Character
import com.example.democompouseapp.presentation.model.CharacterUI
import com.example.democompouseapp.presentation.model.LocationUI
import com.example.democompouseapp.presentation.model.OriginUI
import com.example.democompouseapp.presentation.model.convertToUI
import javax.inject.Inject

class CharacterUIMapper @Inject constructor(): Mapper<Character, CharacterUI>() {
    override fun map(from: Character) = from.run {
        CharacterUI(
            id = id,
            name = name,
            status = status.convertToUI(),
            species = species,
            type = type,
            gender = gender,
            origin = OriginUI(
                origin.name,
                origin.url
            ),
            image = image,
            url = url,
            location = LocationUI(
                name = name,
                url = url
            ),
            episode = episode
        )
    }
}