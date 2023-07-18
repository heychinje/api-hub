package com.server.apis.authentication.interceptors

import com.auth0.jwt.exceptions.AlgorithmMismatchException
import com.auth0.jwt.exceptions.InvalidClaimException
import com.auth0.jwt.exceptions.SignatureVerificationException
import com.auth0.jwt.exceptions.TokenExpiredException
import com.fasterxml.jackson.databind.ObjectMapper
import com.server.apis.authentication.AuthConstants.TOKEN_KEY
import com.server.apis.authentication.AuthErrorCodes.CODE_EXPIRED_TOKEN
import com.server.apis.authentication.AuthErrorCodes.CODE_INVALID_CLAIM
import com.server.apis.authentication.AuthErrorCodes.CODE_MISMATCHED_ALGORITHM
import com.server.apis.authentication.AuthErrorCodes.CODE_MISMATCHED_SIGNATURE
import com.server.apis.authentication.AuthErrorCodes.CODE_UNKNOWN
import com.server.apis.authentication.entity.Status
import com.server.apis.authentication.usecase.VerifyTokenUseCase
import com.server.apis.ums.NullOrBlankTokenException
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor

@Component
class TokenInterceptor(
    private val verifyTokenUseCase: VerifyTokenUseCase,
    private val objectMapper: ObjectMapper = ObjectMapper()
) : HandlerInterceptor {
    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        val token: String? = request.getHeader(TOKEN_KEY)
        val result = verifyTokenUseCase(token)

        // if is ok
        if (result.isSuccess) return true

        // earlier process response and return this request-response
        when (result.exceptionOrNull()) {
            is AlgorithmMismatchException -> CODE_MISMATCHED_ALGORITHM to "The algorithm is different."
            is SignatureVerificationException -> CODE_MISMATCHED_SIGNATURE to "Failed to verify signature."
            is TokenExpiredException -> CODE_EXPIRED_TOKEN to "The token is expired."
            is InvalidClaimException -> CODE_INVALID_CLAIM to "The claim is invalid."
            is NullOrBlankTokenException -> NullOrBlankTokenException.let { it.errorCode to it.msg }
            else -> CODE_UNKNOWN to "Error occurred but we don't know why. More detail, ${result.exceptionOrNull()}"
        }.let {
            objectMapper.writeValueAsString(Status(it.first, it.second))
        }.let {
            response.contentType = "application/json;charset=UTF-8"
            response.writer.println(it)
        }
        return false
    }
}