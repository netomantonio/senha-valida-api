package br.com.senhavalida.api

data class Regra(
    val rule: String? = null,
    val value: Int? = null
) {
    fun ehValida(password: String): Boolean {
        return when (this.rule) {
            "minSize" -> password.length >= this.value!!
            "minSpecialChars" -> password.contains("(?:[^`!@#\$%^&*\\-_=+'/.,]*[`!@#\$%^&*\\-_=+'/.,]){2}".toRegex())
            "noRepeted" -> if (value != 0) return password.contains("^(?!.*(\\d)\\1{$value,}).+\$".toRegex()) else true
            "minDigit" -> validarQuantidadeNumeros(password)
            else -> false
        }
    }

    private fun validarQuantidadeNumeros(password: String): Boolean {
        val regex = Regex("[0-9]")
        val matches = regex.findAll(password)
        val numeros = matches.map { it.groupValues[0] }.joinToString()
        return numeros.split(",").size >= value!!
    }
}