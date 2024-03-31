package com.example.democompouseapp.di

import com.example.democompouseapp.data.model.CharacterResponse
import com.example.democompouseapp.domain.mapper.CharacterMapper
import com.example.democompouseapp.domain.mapper.base.Mapper
import com.example.democompouseapp.domain.model.Character
import com.example.democompouseapp.presentation.mapper.CharacterUIMapper
import com.example.democompouseapp.presentation.model.CharacterUI
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class MapperModule {

    @Binds
    abstract fun bindCharacterMapper(
        mapper: CharacterMapper
    ): Mapper<CharacterResponse, Character>

    @Binds
    abstract fun bindCharacterUIMapper(
        mapper: CharacterUIMapper
    ): Mapper<Character, CharacterUI>
}