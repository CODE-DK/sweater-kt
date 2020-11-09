package ru.homelab.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
class GreetingController {

    @GetMapping("/greeting")
    fun greeting(
        @RequestParam(name = "name", required = false, defaultValue = "World") name: String,
        model: MutableMap<String, Any>
    ): String {

        model["name"] = name
        return "greeting"
    }

    @GetMapping
    fun main (model: MutableMap<String, Any>): String {
        model["some"] = "hello, letsCode!"
        return "main"
    }

}