package com.server.apis.openai

import com.server.apis.Constants.DOMAIN_MODELS
import com.server.apis.Constants.DOMAIN_OPEN_AI
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.utils.io.core.*
import kotlinx.coroutines.runBlocking
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import kotlin.io.use

@RestController
@RequestMapping(DOMAIN_OPEN_AI)
class OpenAIController {

    @GetMapping(DOMAIN_MODELS)
    fun getModels(): String {
        val content: String = runBlocking {
            HttpClient.get("http://www.x.com/").body()
        }
        println("????: $content")
        return content
    }
}