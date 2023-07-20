package com.server.apis.openai.usecase

import org.springframework.beans.factory.config.ConfigurableBeanFactory
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component


@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Component
class AcquireModelsUseCase {
    operator fun invoke():String{
        return ""
    }
}