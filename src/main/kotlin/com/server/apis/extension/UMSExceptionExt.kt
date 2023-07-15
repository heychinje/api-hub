package com.server.apis.extension

import com.server.apis.authentication.AuthErrorCodes.CODE_UNKNOWN
import com.server.apis.ums.UMSException

val UMSException?.errorCode: Int
    get() = this?.errorCode ?: CODE_UNKNOWN