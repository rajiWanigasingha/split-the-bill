package com.system.di

import com.system.screen.registration.state.RegistrationScreenViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val registrationScreenModule = module {
    viewModel { RegistrationScreenViewModel(get()) }
}