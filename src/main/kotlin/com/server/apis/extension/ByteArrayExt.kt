package com.server.apis.extension

val ByteArray.hex: String
    get() = buildString {
        this@hex.forEach {
            val h = (it.toInt() and (0xFF)).toString(16)
            if (h.length == 1) append("0$h") else append(h)
        }
    }
