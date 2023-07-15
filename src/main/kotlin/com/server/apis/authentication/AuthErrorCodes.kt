package com.server.apis.authentication

object AuthErrorCodes {
    // top error code
    const val CODE_UNKNOWN = -1
    const val CODE_OK = 0

    // domain error code start value
    private const val CODE_AUTH = 1000
    private const val CODE_UMS = 2000

    // auth domain error code
    const val CODE_MISMATCHED_ALGORITHM = CODE_AUTH + 1
    const val CODE_MISMATCHED_SIGNATURE = CODE_AUTH + 2
    const val CODE_EXPIRED_TOKEN = CODE_AUTH + 3
    const val CODE_INVALID_CLAIM = CODE_AUTH + 4


    // user domain error code
    const val CODE_INVALID_USERNAME = CODE_UMS + 1
    const val CODE_ILLEGAL_USERNAME = CODE_UMS + 2
    const val CODE_EXISTED_USERNAME = CODE_UMS + 3
    const val CODE_NOT_FOUND_USERNAME = CODE_UMS + 4
    const val CODE_NOT_FOUND_USER_CREDENTIAL = CODE_UMS + 5
    const val CODE_NOT_FOUND_USER = CODE_UMS + 6
    const val CODE_INVALID_PASSWORD = CODE_UMS + 7
    const val CODE_INSECURITY_PASSWORD = CODE_UMS + 8
    const val CODE_INCORRECT_PASSWORD = CODE_UMS + 9
}