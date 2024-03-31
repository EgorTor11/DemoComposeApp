package com.example.democompouseapp.presentation.model

import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import com.example.democompouseapp.R
import com.example.democompouseapp.domain.model.CharacterStatus

enum class CharacterStatusUI(
    @ColorRes
    val color: Int,
    @StringRes
    val naming: Int
) {
    ALIVE(
        R.color.green,
        R.string.alive
    ),
    DEAD(
        R.color.red,
        R.string.dead
    ),
    UNKNOWN(
        R.color.black,
        R.string.unknown
    )
}

fun CharacterStatus.convertToUI(): CharacterStatusUI {
    return when (this) {
        CharacterStatus.ALIVE -> CharacterStatusUI.ALIVE
        CharacterStatus.DEAD -> CharacterStatusUI.DEAD
        CharacterStatus.UNKNOWN -> CharacterStatusUI.UNKNOWN
    }
}