package com.gmail.williammingardi.forum.model

import org.springframework.security.core.GrantedAuthority
import javax.persistence.*

@Entity
class Role(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    private val name: String,
    @ManyToMany(mappedBy = "role")
    val user: List<User>
) : GrantedAuthority {
    override fun getAuthority() = name
}