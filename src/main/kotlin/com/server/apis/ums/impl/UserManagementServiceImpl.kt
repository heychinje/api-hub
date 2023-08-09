package com.server.apis.ums.impl

import com.server.apis.authentication.AuthErrorCodes.CODE_OK
import com.server.apis.authentication.entity.Status
import com.server.apis.authentication.usecase.CreateTokenUseCase
import com.server.apis.ums.*
import com.server.apis.ums.dto.*
import com.server.apis.ums.usecase.ChangePasswordUseCase
import com.server.apis.ums.usecase.LoginUserUseCase
import com.server.apis.ums.usecase.RegisterUseCase
import com.server.apis.ums.usecase.ResetPasswordUseCase
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.config.ConfigurableBeanFactory
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Service


@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Service
class UserManagementServiceImpl(
    private val log: Logger = LoggerFactory.getLogger("UMS"),
    private val registerUseCase: RegisterUseCase,
    private val loginUserUseCase: LoginUserUseCase,
    private val resetPasswordUseCase: ResetPasswordUseCase,
    private val changePasswordUseCase: ChangePasswordUseCase,
    private val createTokenUseCase: CreateTokenUseCase,
) : UserManagementService {
    override fun register(userName: String, password: String): RegisterDTO =
        registerUseCase(userName, password).let { r ->
            // parse user
            val user = r.getOrNull()?.toDto()

            // parse status
            val status = r.exceptionOrNull()?.let {
                (it as? UMSException) ?: UnknownException
            }?.let {
                Status(it.errorCode, it.msg)
            } ?: Status(CODE_OK, "ok")

            // build dto
            RegisterDTO(
                status = status,
                data = user?.let { RegisterDTO.Data(it) }
            )
        }

    override fun login(userName: String, password: String): LoginDTO =
        loginUserUseCase(userName, password).let { r ->
            val user = r.getOrNull()?.toDto()

            val status = r.exceptionOrNull()?.let {
                (it as? UMSException) ?: UnknownException
            }?.let {
                Status(it.errorCode, it.msg)
            } ?: Status(CODE_OK, "ok")

            val token = user?.let {
                createTokenUseCase {
                    withClaim("userName", it.userName)
                    withClaim("userId", it.userId)
                }
            }

            LoginDTO(
                status = status,
                data = token?.let {
                    LoginDTO.Data(
                        user = user,
                        token = it
                    )
                }
            )
        }

    override fun resetPassword(userName: String, password: String): ResetPasswordDTO =
        resetPasswordUseCase(userName, password).let { r ->
            val user = r.getOrNull()?.toDto()

            val status = r.exceptionOrNull()?.let {
                (it as? UMSException) ?: UnknownException
            }?.let {
                Status(it.errorCode, it.msg)
            } ?: Status(CODE_OK, "ok")

            ResetPasswordDTO(
                status = status,
                data = user?.let {
                    ResetPasswordDTO.Data(user = it)
                }
            )
        }

    override fun changePassword(userName: String, oldPassword: String, newPassword: String): ChangePasswordDTO =
        changePasswordUseCase(userName, oldPassword, newPassword).let { r ->
            val user = r.getOrNull()?.toDto()

            val status = r.exceptionOrNull()?.let {
                (it as? UMSException) ?: UnknownException
            }?.let {
                Status(it.errorCode, it.msg)
            } ?: Status(CODE_OK, "ok")

            ChangePasswordDTO(
                status = status,
                data = user?.let {
                    ChangePasswordDTO.Data(user = it)
                }
            )
        }

    override fun subscribeService(
        userId: String,
        serviceName: String,
        serviceDescription: String?,
        serviceAccessCredential: String
    ): AvailableServiceDTO {
        TODO("Not yet implemented")
    }

    override fun fetchAvailableServices(userId: String): AvailableServiceDTO {
        TODO("Not yet implemented")
    }
}