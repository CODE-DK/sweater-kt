package ru.homelab.controller

import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
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
    fun home(): String {
        return "home"
    }

    @GetMapping("/main")
    fun main(model: Model, @RequestParam(required = false, defaultValue = "") filter: String?): String {
        val messages = if (filter != null && filter.isNotEmpty()) {
            messageRepo.findByTag(filter)
        } else {
            messageRepo.findAll()
        }

        model.addAttribute("messages", messages)
        model.addAttribute("filter", filter)
        return "main"
    }

    @PostMapping("/add")
    fun add(
        model: Model,
        @RequestParam text: String,
        @RequestParam tag: String,
        @AuthenticationPrincipal user: User
    ): String {
        val message = Message(text = text, tag = tag, author = user)
        messageRepo.save(message)
        val messages = messageRepo.findAll()
        model.addAttribute("messages", messages)
        return "main"
    }
}