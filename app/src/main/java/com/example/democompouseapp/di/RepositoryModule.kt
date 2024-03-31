package com.example.democompouseapp.di

import com.example.democompouseapp.data.repository.CharacterRepositoryImpl
import com.example.democompouseapp.domain.repository.CharacterRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindCharacterRepository(
        repository: CharacterRepositoryImpl
    ): CharacterRepository
}