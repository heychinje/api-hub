package com.server.apis.openai

import io.ktor.client.*
import io.ktor.client.engine.cio.*

internal val HttpClient = HttpClient(CIO) {

}