package com.server.apis

object Constants {
    // root domain
    const val DOMAIN_ROOT = "/"

    // ums domain
    const val DOMAIN_UMS = "/ums"
    const val DOMAIN_REGISTER = "/register"
    const val DOMAIN_LOGIN = "/login"

    // apis domain
    private const val DOMAIN_APIS = "/apis"

    // ip domain
    const val DOMAIN_IP = "$DOMAIN_APIS/ip"
    const val DOMAIN_CURRENT = "/current"

    // open-ai domain
    const val DOMAIN_OPEN_AI = "$DOMAIN_APIS/openai"
    const val DOMAIN_MODELS = "/models"
}