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
class AesDecodeUseCase {
    operator fun invoke(source: String, password: String, isBase64Text: Boolean = true): String = try {
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

        // do decrypt
        val decryptedSourceByteArray = Cipher.getInstance("AES").run {
            init(Cipher.DECRYPT_MODE, originalKey)
            doFinal(
                if (isBase64Text) source.decodeBase64Bytes() else source.toByteArray(Charsets.UTF_8)
            )
        }

        // convert to string
        String(decryptedSourceByteArray, Charsets.UTF_8)
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