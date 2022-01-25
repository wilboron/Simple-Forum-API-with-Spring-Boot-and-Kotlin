package com.gmail.williammingardi.forum.model

import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.data.repository.cdi.Eager
import javax.persistence.*

@Entity
@Table(name = "users")
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val name: String,
    val email: String,
    @Column(nullable = false)
    var password: String,

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "user_role",
        joinColumns = [JoinColumn(name = "user_id")],
        inverseJoinColumns = [JoinColumn(name = "role_id")]
    )
    val role: List<Role> = mutableListOf()
)