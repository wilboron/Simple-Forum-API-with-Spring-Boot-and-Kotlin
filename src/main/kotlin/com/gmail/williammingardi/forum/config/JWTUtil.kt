package com.gmail.williammingardi.forum.config

import com.gmail.williammingardi.forum.service.UserService
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.GrantedAuthority
import org.springframework.stereotype.Component
import java.util.*

@Component
class JWTUtil(
    private val userService: UserService
) {

    @Value("\${jwt.secret}")
    private lateinit var secret: String

    @Value("\${jwt.token-expiration-minutes}")
    private lateinit var minutesToExpire: String

    private val minutesInMilliseconds = 60000L
    private val expiration get() = minutesInMilliseconds * minutesToExpire.toLong()

    fun generateToken(
        username: String,
        authorities: MutableCollection<out GrantedAuthority>
    ): String? {
        return Jwts
            .builder()
            .setSubject(username)
//            .claim("roles", authorities)
            .setExpiration(Date(System.currentTimeMillis() + expiration))
            .signWith(SignatureAlgorithm.HS512, secret.toByteArray())
            .compact()
    }

    fun isValid(jwt: String?): Boolean {
        return try {
            Jwts.parser().setSigningKey(secret.toByteArray()).parseClaimsJws(jwt)
            true
        } catch (e: IllegalArgumentException) {
            false
        }
    }

    fun getAuthentication(jwt: String?): UsernamePasswordAuthenticationToken {
        val username = Jwts.parser()
            .setSigningKey(secret.toByteArray())
            .parseClaimsJws(jwt).body
            .subject

        val user = userService.loadUserByUsername(username)
        return UsernamePasswordAuthenticationToken(username, null, user?.authorities)
    }
}