package com.server.apis.authentication

object AuthConstants {
    // token
    const val SECRET = "com.server.apis.ums"
    const val TOKEN_KEY = "token"

    // token timeout
    private const val MILLISECOND: Long = 1
    private const val SECOND: Long = 1000 * MILLISECOND
    private const val MINUTE: Long = 60 * SECOND
    private const val HOUR: Long = 60 * MINUTE
    private const val DAY: Long = 24 * HOUR
    private const val WEEK: Long = 7 * DAY
    const val TOKEN_TIMEOUT: Long = 2 + WEEK
}