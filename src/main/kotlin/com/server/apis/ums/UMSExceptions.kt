package com.server.apis.ums


sealed class UMSException(msg: String, t: Throwable?) :
    Throwable("An Exception occurred while executing user management operations. The further information: $msg", t)

object InvalidUserNameException : UMSException("The user name is invalid", null)

object IllegalUserNameException : UMSException("The user name does not match legal rules", null)

object ExistedUserNameException : UMSException("The user name already exists", null)

object NotFoundUserNameException : UMSException("The user name does not exist", null)

object NotFoundUserCredentialException : UMSException("The user credential does not exist", null)

object NotFoundUserException : UMSException("Cannot find any user according specified condition", null)

object InvalidPasswordException : UMSException("The password is invalid", null)

object InsecurityPasswordException : UMSException("The password does not match security rules", null)

object IncorrectPasswordException : UMSException("The password is incorrect", null)

object UnknownException : UMSException("Unknown exception occurred", null)

