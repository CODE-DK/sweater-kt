package ru.homelab.entity

import javax.persistence.*

@Entity
@Table(name = "messages")
data class Message(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long = 0,
    var text: String = "",
    var tag: String = "",

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    var author: User? = null
) {
    fun getAuthorName(): String {
        return author?.username ?: "<none>"
    }
}