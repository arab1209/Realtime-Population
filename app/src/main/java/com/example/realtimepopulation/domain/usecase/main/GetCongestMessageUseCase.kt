package com.example.realtimepopulation.domain.usecase.main

import javax.inject.Inject

class GetCongestMessageUseCase @Inject constructor(){
    operator fun invoke(query: String): List<String> {
        return query.split(". ")
    }
}