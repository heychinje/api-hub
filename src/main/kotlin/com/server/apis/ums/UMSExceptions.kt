package com.server.apis.ums

import com.server.apis.authentication.AuthErrorCodes.CODE_EXISTED_USERNAME
import com.server.apis.authentication.AuthErrorCodes.CODE_ILLEGAL_USERNAME
import com.server.apis.authentication.AuthErrorCodes.CODE_INCORRECT_PASSWORD
import com.server.apis.authentication.AuthErrorCodes.CODE_INSECURITY_PASSWORD
import com.server.apis.authentication.AuthErrorCodes.CODE_INVALID_PASSWORD
import com.server.apis.authentication.AuthErrorCodes.CODE_INVALID_USERNAME
import com.server.apis.authentication.AuthErrorCodes.CODE_NOT_FOUND_USER
import com.server.apis.authentication.AuthErrorCodes.CODE_NOT_FOUND_USERNAME
import com.server.apis.authentication.AuthErrorCodes.CODE_NOT_FOUND_USER_CREDENTIAL
import com.server.apis.authentication.AuthErrorCodes.CODE_NULL_OR_BLANK_TOKEN
import com.server.apis.authentication.AuthErrorCodes.CODE_UNKNOWN


sealed class UMSException(val msg: String, val errorCode: Int, val t: Throwable? = null) :
    Throwable("An Exception occurred while executing user management operations. The further information: $msg", t)

object InvalidUserNameException : UMSException("The user name is invalid", CODE_INVALID_USERNAME)

object IllegalUserNameException : UMSException("The user name does not match legal rules", CODE_ILLEGAL_USERNAME)

object ExistedUserNameException : UMSException("The user name already exists", CODE_EXISTED_USERNAME)

object NotFoundUserNameException : UMSException("The user name does not exist", CODE_NOT_FOUND_USERNAME)

object NotFoundUserCredentialException :
    UMSException("The user credential does not exist", CODE_NOT_FOUND_USER_CREDENTIAL)

object NotFoundUserException : UMSException("Cannot find any user according specified condition", CODE_NOT_FOUND_USER)

object InvalidPasswordException : UMSException("The password is invalid", CODE_INVALID_PASSWORD)

object InsecurityPasswordException :
    UMSException("The password does not match security rules", CODE_INSECURITY_PASSWORD)

object IncorrectPasswordException : UMSException("The password is incorrect", CODE_INCORRECT_PASSWORD)

object NullOrBlankTokenException: UMSException("The token passed is null or empty", CODE_NULL_OR_BLANK_TOKEN)

object UnknownException : UMSException("Unknown exception occurred", CODE_UNKNOWN)

