package ru.homelab.repo

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ru.homelab.entity.Message

@Repository
interface MessageRepo : JpaRepository<Message, Long> {

    fun findByTag(tag: String): List<Message>
}