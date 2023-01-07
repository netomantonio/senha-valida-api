package br.com.senhavalida.api

import com.expediagroup.graphql.server.operations.Query
import org.apache.commons.logging.Log
import org.slf4j.event.LoggingEvent
import org.springframework.stereotype.Component
import java.util.logging.Logger

@Component
class VerificarResolverImpl: Query {
    fun verify(password: String, rules: List<Regra>): CheckedResponse {

        System.Logger.Level.INFO.severity

        val regrasInfringidas: MutableList<String> = mutableListOf<String>().toMutableList()

        for (regra in rules){
            if (!regra.ehValida(password)){
                regrasInfringidas += regra.rule!!
            }
        }

        return CheckedResponse(
            verify = regrasInfringidas.isEmpty(),
            noMatch = regrasInfringidas
        )
    }
}