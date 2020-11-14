package ru.homelab.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import ru.homelab.entity.Role
import ru.homelab.entity.User
import ru.homelab.repo.UserRepo
import java.util.stream.Collectors
import java.util.stream.Stream

@Controller
@RequestMapping("/user")
class UserController(
        private val userRepo: UserRepo
) {
    @GetMapping
    fun userList(model: Model): String {
        model.addAttribute("users", userRepo.findAll());
        return "userList"
    }

    @GetMapping("/{user}")
    fun userEditForm(model: Model, @PathVariable user: User): String {
        model.addAttribute("user", user)
        model.addAttribute("roles", Role.values())
        return "userEdit"
    }

    @PostMapping
    fun userSave(
            @RequestParam username: String,
            @RequestParam form: MutableMap<String, String>,
            @RequestParam("userId") user: User
    ): String {

        user.username = username
        val roles = Stream.of(Role.values())
                .map { return@map Role::name }
                .collect(Collectors.toSet())

        user.roles.clear()

        for (key in form.keys) {
            if (roles.contains(key)) {
                user.roles.add(Role.valueOf(key))
            }
        }

        userRepo.save(user)

        return "redirect:/user"
    }
}