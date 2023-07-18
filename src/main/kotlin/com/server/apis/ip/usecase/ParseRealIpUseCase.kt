package com.server.apis.ip.usecase

import jakarta.servlet.http.HttpServletRequest
import org.springframework.beans.factory.config.ConfigurableBeanFactory
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component

@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Component
class ParseRealIpUseCase {

    operator fun invoke(request: HttpServletRequest): String? = with(request) { realIp ?: forwardedIp }

    private val HttpServletRequest.realIp: String?
        get() = getHeader("X-Real-IP")?.takeIf { it.isValid }

    private val HttpServletRequest.forwardedIp: String?
        get() = getHeader("X-Forwarded-For")?.takeIf {
            it.isValid
        }?.split(",")?.takeIf {
            it.isNotEmpty()
        }?.get(0)

    private val String.isValid: Boolean
        get() = isNotBlank() && isNotEmpty() && contentEquals("unknown", true)
}