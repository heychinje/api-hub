package com.server.apis.authentication.usecase

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.interfaces.DecodedJWT
import com.server.apis.authentication.AuthConstants.SECRET
import com.server.apis.ums.NullOrBlankTokenException
import org.springframework.stereotype.Component

@Component
class VerifyTokenUseCase(
    private val algorithm: Algorithm = Algorithm.HMAC256(SECRET),
) {
    operator fun invoke(token: String?): Result<DecodedJWT> = runCatching {
        token?.let {
            JWT.require(algorithm).build().verify(it)
        } ?: throw NullOrBlankTokenException
    }
}