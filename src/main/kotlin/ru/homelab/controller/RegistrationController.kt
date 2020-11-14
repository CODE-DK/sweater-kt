package ru.homelab.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import ru.homelab.entity.Role.USER
import ru.homelab.entity.User
import ru.homelab.repo.UserRepo

@Controller
class RegistrationController(
    private val userRepo: UserRepo
) {

    @GetMapping("/registration")
    fun registration(): String {
        return "registration"
    }

    @PostMapping("/registration")
    fun add(user: User, model: Model): String {
        val userFromDb = userRepo.findByUsername(user.username)

        userFromDb?.let {
            model.addAttribute("message", "User exists!")
            return "registration"
        }

        user.active = true
        user.roles = mutableSetOf(USER)
        userRepo.save(user)

        return "redirect:/login"
    }
}