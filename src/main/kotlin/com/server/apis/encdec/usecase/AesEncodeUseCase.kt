package com.server.apis.encdec.usecase

import io.ktor.util.*
import org.springframework.beans.factory.config.ConfigurableBeanFactory
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component
import java.io.UnsupportedEncodingException
import java.security.InvalidKeyException
import java.security.NoSuchAlgorithmException
import java.security.SecureRandom
import javax.crypto.*
import javax.crypto.spec.SecretKeySpec

@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Component
class AesEncodeUseCase {
    operator fun invoke(source: String, password: String, useBase64Encode: Boolean = true): String = try {
        // generate secret key
        val originalKey = KeyGenerator.getInstance("AES").run {
            init(
                128,
                SecureRandom.getInstance("SHA1PRNG").apply { setSeed(password.toByteArray(Charsets.UTF_8)) }
            )
            generateKey().encoded
        }.let {
            SecretKeySpec(it, "AES")
        }

        // do encrypt
        val encryptedSourceByteArray = Cipher.getInstance("AES").run {
            init(Cipher.ENCRYPT_MODE, originalKey)
            doFinal(
                source.toByteArray(Charsets.UTF_8)
            )
        }

        // convert to string
        if (useBase64Encode) encryptedSourceByteArray.encodeBase64()
        else String(encryptedSourceByteArray, Charsets.UTF_8)
    } catch (e: Exception) {
        throw when (e) {
            is NoSuchAlgorithmException -> e
            is NoSuchPaddingException -> e
            is InvalidKeyException -> e
            is IllegalBlockSizeException -> e
            is BadPaddingException -> e
            is UnsupportedEncodingException -> e
            else -> Exception("Unknown exception")
        }
    }
}