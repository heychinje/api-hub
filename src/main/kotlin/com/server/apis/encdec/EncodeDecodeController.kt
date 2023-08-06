@file:OptIn(ExperimentalEncodingApi::class)

package com.server.apis.encdec

import com.server.apis.Constants
import com.server.apis.Constants.DOMAIN_AES
import com.server.apis.encdec.usecase.AesDecodeUseCase
import com.server.apis.encdec.usecase.AesEncodeUseCase
import io.ktor.util.*
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import kotlin.io.encoding.ExperimentalEncodingApi

@RestController
@RequestMapping(Constants.DOMAIN_ENC_DEC)
class EncodeDecodeController(
    private val aesEncodeUseCase: AesEncodeUseCase,
    private val aesDecodeUseCase: AesDecodeUseCase,
) {

    @GetMapping("$DOMAIN_AES/encode")
    fun encode(
        @RequestParam(value = "source") source: String,
        @RequestParam(value = "password") password: String
    ):String {
        return aesEncodeUseCase(source,password).encodeBase64()
    }

    @GetMapping("$DOMAIN_AES/decode")
    fun decode(
        @RequestParam(value = "source") source: String,
        @RequestParam(value = "password") password: String
    ):String {
        return aesDecodeUseCase(source.decodeBase64String(),password)
    }
}