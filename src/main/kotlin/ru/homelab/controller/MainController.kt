package ru.homelab.controller

import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import ru.homelab.entity.Message
import ru.homelab.entity.User
import ru.homelab.repo.MessageRepo

@Controller
class MainController(
    private val messageRepo: MessageRepo
) {

    @GetMapping("/")
    fun home(model: MutableMap<String, Any?>): String {
        return "home"
    }

    @GetMapping("/main")
    fun main(model: MutableMap<String, Any?>): String {
        val messages = messageRepo.findAll()
        model["messages"] = messages
        return "main"
    }

    @PostMapping("/filter")
    fun filter(model: MutableMap<String, Any?>, @RequestParam filter: String?): String {
        val messages = if (filter != null && filter.isNotEmpty()) {
            messageRepo.findByTag(filter)
        } else {
            messageRepo.findAll()
        }
        model["messages"] = messages
        return "main"
    }

    @PostMapping("/add")
    fun add(
        model: MutableMap<String, Any?>,
        @RequestParam text: String,
        @RequestParam tag: String,
        @AuthenticationPrincipal user: User
    ): String {
        val message = Message(text = text, tag = tag, author = user)
        messageRepo.save(message)
        val messages = messageRepo.findAll()
        model["messages"] = messages
        return "main"
    }
}