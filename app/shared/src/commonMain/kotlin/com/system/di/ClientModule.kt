package com.system.di

import com.system.client.Client
import org.koin.dsl.module

val clientModule = module {
    single<Client> { Client() }
}