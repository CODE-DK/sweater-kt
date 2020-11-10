package ru.homelab.repo

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ru.homelab.entity.User

@Repository
interface UserRepo : JpaRepository<User, Long> {
    fun findByUsername(username: String): User?
}