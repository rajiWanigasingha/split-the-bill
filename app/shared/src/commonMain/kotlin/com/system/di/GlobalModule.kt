package com.system.di

import com.system.store.GlobalStoreViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val globalModule = module {
    viewModel { GlobalStoreViewModel(
        prefs = get(named("prefs")),
        vault = get(named("vault"))
    ) }
}