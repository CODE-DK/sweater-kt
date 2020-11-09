package ru.homelab.entity

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "messages")
data class Message(
    @Id
    @GeneratedValue
    var id: Long? = null,
    var text: String? = null,
    var tag: String? = null
)