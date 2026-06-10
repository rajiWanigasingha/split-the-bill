package com.system.di

import eu.anifantakis.lib.ksafe.KSafe
import org.koin.android.ext.koin.androidApplication
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module

actual val platformModule: Module = module {
    // Fast, plain writes — for everyday preferences
    single(named("prefs")) {
        KSafe(
            context = androidApplication(),
            fileName = "prefs"
        )
    }

    // Encrypted writes — for secrets (tokens, passwords, PII)
    single(named("vault")) {
        KSafe(
            context = androidApplication(),
            fileName = "vault"
        )
    }
}