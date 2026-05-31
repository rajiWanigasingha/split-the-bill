package com.system

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform