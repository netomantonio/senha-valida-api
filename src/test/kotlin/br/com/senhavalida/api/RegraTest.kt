package br.com.senhavalida.api

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

internal class RegraTest(

){

    @Test
    fun `Nao deve validar regra noRepeted quando valor da regra for ZERO`(){
        assertTrue(Regra("noRepeted", 0).ehValida("1!2@3TestSenhaForte4444"))
    }

    @ParameterizedTest
    @MethodSource("senhasValidas")
    fun `Deve validar com sucesso uma senha com todas as regras validas`(password: String){
        val regras = listOf(
            Regra("minSize", 8),
            Regra("minSpecialChars", 2),
            Regra("noRepeted", 2),
            Regra("minDigit", 4)
        )
        val regrasInfringidas: MutableList<String> = mutableListOf<String>().toMutableList()

        for (regra in regras){
            if (!regra.ehValida(password)){
                regrasInfringidas += regra.rule!!
            }
        }

        assertTrue(regrasInfringidas.isEmpty())


    }

    @ParameterizedTest
    @MethodSource("senhasInvalidas")
    fun `Deve validar com insucesso uma senha com todas as regras invalidas`(password: String){
        val regras = listOf(
            Regra("minSize", 8),
            Regra("minSpecialChars", 2),
            Regra("noRepeted", 2),
            Regra("minDigit", 4)
        )
        val regrasInfringidas: MutableList<String> = mutableListOf<String>().toMutableList()

        for (regra in regras){
            if (!regra.ehValida(password)){
                regrasInfringidas += regra.rule!!
            }
        }

        assertFalse(regrasInfringidas.isEmpty())


    }


    companion object {
        @JvmStatic
        fun senhasValidas(): List<Arguments> {
           return listOf(
                Arguments.of("teste!1234@"),
                Arguments.of("#1t3st3%2"),
                Arguments.of("T3sT3#4@1"),
                Arguments.of("S3nh4Va!1d@5"),
                Arguments.of("p@55w0rd$29")
            )
        }

        @JvmStatic
        fun senhasInvalidas(): List<Arguments> {
            return listOf(
                Arguments.of("!@1234"),
                Arguments.of("#1234tes"),
                Arguments.of("T3sT333#4@1"),
                Arguments.of("S3nh4Va!1d@"),
                Arguments.of("p54w0r2"),
                Arguments.of("p544302"),
                Arguments.of("ppp0222")
            )
        }
    }
}