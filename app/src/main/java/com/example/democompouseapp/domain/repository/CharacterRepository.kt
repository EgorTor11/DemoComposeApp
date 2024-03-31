package com.example.democompouseapp.domain.repository

import androidx.paging.PagingData
import com.example.democompouseapp.data.model.CharacterResponse
import com.example.democompouseapp.domain.model.Character
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {
    fun getCharacters(): Flow<PagingData<CharacterResponse>>
}