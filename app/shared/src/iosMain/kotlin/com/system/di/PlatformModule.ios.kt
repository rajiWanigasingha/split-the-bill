package com.system.di

import eu.anifantakis.lib.ksafe.KSafe
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module

actual val platformModule: Module = module {
    single(named("prefs")) {
        KSafe(fileName = "prefs")
    }

    single(named("vault")) {
        KSafe(fileName = "vault")
    }
}