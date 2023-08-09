package com.server.apis.ums

import com.server.apis.ums.dto.UserDTO as DtoUser
import com.server.apis.ums.entity.User as RawUser


fun RawUser.toDto(): DtoUser = DtoUser(userId, userName, createTime)