package com.example.democompouseapp.domain.mapper.base

abstract class Mapper<From, To> {
    abstract fun map(from: From): To
}