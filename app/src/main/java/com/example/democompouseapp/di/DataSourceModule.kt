package com.example.democompouseapp.di

import androidx.paging.PagingSource
import com.example.democompouseapp.data.datasource.CharacterPagingDataSource
import com.example.democompouseapp.data.model.CharacterResponse
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    abstract fun bindCharacterPagingDataSource(
        dataSource: CharacterPagingDataSource
    ): PagingSource<Int, CharacterResponse>
}