package com.server.apis.root

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class RootDomainController  {
    private val messages by lazy {
        javaClass.getResource("/html/index.html")?.readText() ?: "No message found"
    }

    @GetMapping("/")
    fun getRoot(): String {
        return messages
    }
}