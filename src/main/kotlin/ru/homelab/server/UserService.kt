package ru.homelab.server

import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import ru.homelab.repo.UserRepo

@Service
class UserService(
    private val userRepo: UserRepo
) : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails {
        return userRepo.findByUsername(username)!!
    }
}