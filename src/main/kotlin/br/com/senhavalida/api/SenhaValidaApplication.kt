package br.com.senhavalida.api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SenhaValidaApplication

fun main(args: Array<String>) {
	runApplication<SenhaValidaApplication>(*args)
}
