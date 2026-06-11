package com.system.di

import com.system.screen.login.state.LoginViewModel
import org.koin.dsl.module
import org.koin.core.module.dsl.viewModel

val loginScreenModule = module {
    viewModel { LoginViewModel(get()) }
}