package com.example.democompouseapp.data.model

data class PageResponse(
    val info: PageInfoResponse,
    val results: List<CharacterResponse>
)