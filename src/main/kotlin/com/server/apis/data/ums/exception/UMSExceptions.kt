package com.server.apis.data.ums.exception


sealed class UMSException(msg: String, t: Throwable?) :
    Throwable("An Exception occurred while executing user management operations. The further information: $msg", t)

object InvalidUserNameException : UMSException("The user name is invalid", null)

object ExistedUserNameException : UMSException("The user name already exists", null)

object NotFoundUserNameException : UMSException("The user name does not exist", null)

object NotFoundUserProfileException : UMSException("The user profile does not exist", null)

object NotFoundUserException : UMSException("Cannot find any user according specified condition", null)

object InvalidPasswordException : UMSException("The password is invalid", null)

object InsecurityPasswordException : UMSException("The password does not match security rules", null)

object IncorrectPasswordException : UMSException("The password is incorrect", null)

