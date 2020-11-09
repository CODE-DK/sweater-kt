package ru.homelab

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class ServingWebContentApplication {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            runApplication<ServingWebContentApplication>(*args);
        }
    }
}