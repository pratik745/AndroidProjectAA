package com.example.myproject.utils


import com.example.myproject.model.RickMorty


object Constants {
    const val BASE_URL:String ="https://rickandmortyapi.com/api/"
    const val END_POINT:String ="character"
    var pages=1
    val charactersList:MutableList<RickMorty> = mutableListOf()
    val choices = arrayOf("Sort<None>","Sort<Name>","Sort<No.of.Episodes>")

}