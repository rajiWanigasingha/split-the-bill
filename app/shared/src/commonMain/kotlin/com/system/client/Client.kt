package com.system.client

import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.statement.HttpResponse
import io.ktor.serialization.kotlinx.json.json

class Client {
    private val httpClient = HttpClient() {
        install(ContentNegotiation) {
            json()
        }
        install(HttpTimeout) {
            requestTimeoutMillis = 15_000
        }
    }

    suspend fun get(url: String, block: HttpRequestBuilder.() -> Unit = {}): HttpResponse {
        return httpClient.get(urlBuilder(url), block)
    }

    suspend fun post(url: String, block: HttpRequestBuilder.() -> Unit = {}): HttpResponse {
        return httpClient.post(urlBuilder(url), block)
    }

    suspend fun put(url: String, block: HttpRequestBuilder.() -> Unit = {}): HttpResponse {
        return httpClient.put(urlBuilder(url), block)
    }

    suspend fun delete(url: String, block: HttpRequestBuilder.() -> Unit = {}): HttpResponse {
        return httpClient.delete(urlBuilder(url), block)
    }

    private fun urlBuilder(path: String): String {
        return "http://localhost:8080${path}"
    }
}