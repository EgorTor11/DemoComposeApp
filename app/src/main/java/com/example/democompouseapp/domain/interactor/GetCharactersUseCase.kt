package com.example.democompouseapp.domain.interactor

import androidx.paging.PagingData
import androidx.paging.map
import com.example.democompouseapp.data.model.CharacterResponse
import com.example.democompouseapp.domain.mapper.base.Mapper
import com.example.democompouseapp.domain.model.Character
import com.example.democompouseapp.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetCharactersUseCase @Inject constructor(
    private val repository: CharacterRepository,
    private val mapper: Mapper<CharacterResponse, Character>
) {

    fun invoke(): Flow<PagingData<Character>> =
        repository.getCharacters().map { pagingData ->
            pagingData.map { characterResponse ->
                mapper.map(characterResponse)
            }
        }
}