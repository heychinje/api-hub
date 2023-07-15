package com.server.apis

import com.server.apis.authentication.interceptors.TokenInterceptor
import org.springframework.stereotype.Component
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer


@Component
class Configurer(
    private val tokenInterceptor: TokenInterceptor
) : WebMvcConfigurer {
    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addTokenInterceptors(tokenInterceptor)
    }

    private fun InterceptorRegistry.addTokenInterceptors(tokenInterceptor: TokenInterceptor) = run {
        addInterceptor(tokenInterceptor).apply {
            addPathPatterns("/apis")
            excludePathPatterns("/ums")
        }
    }
}