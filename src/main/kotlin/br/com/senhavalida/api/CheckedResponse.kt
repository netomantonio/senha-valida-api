package br.com.senhavalida.api

data class CheckedResponse(
    val verify: Boolean? = null,
    val noMatch: List<String>? = null
)