package com.server.apis.authentication.usecase

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTCreator
import com.auth0.jwt.algorithms.Algorithm
import com.server.apis.authentication.AuthConstants.SECRET
import com.server.apis.authentication.AuthConstants.TOKEN_TIMEOUT
import org.springframework.beans.factory.config.ConfigurableBeanFactory
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component
import java.util.*


@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Component
class CreateTokenUseCase(
    private val algorithm: Algorithm = Algorithm.HMAC256(SECRET),
) {
    operator fun invoke(
        timeout: Long = TOKEN_TIMEOUT,
        builder: JWTCreator.Builder.() -> Unit
    ): String = JWT.create().apply {
        builder(this)
        val issuedAt = Date()
        val expiresAt = Date(issuedAt.time + timeout)
        withIssuedAt(issuedAt)
        withExpiresAt(expiresAt)
    }.sign(algorithm)
}