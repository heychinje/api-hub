package com.server.apis.extension

import java.security.MessageDigest

private val MD5: MessageDigest get() = MessageDigest.getInstance("MD5")

private val SHA1: MessageDigest get() = MessageDigest.getInstance("SHA-1")

private val SHA256: MessageDigest get() = MessageDigest.getInstance("SHA-256")


val String.md5: String get() = MD5.digest(toByteArray()).hex

fun String.md5(salt: String) = "{'salt':$salt,'value':$this}".md5

val String.sha1: String get() = SHA1.digest(toByteArray()).hex

fun String.sha1(salt: String) = "{'salt':$salt,'value':$this}".sha1

val String.sha256: String get() = SHA256.digest(toByteArray()).hex

fun String.sha256(salt: String) = "{'salt':$salt,'value':$this}".sha256




