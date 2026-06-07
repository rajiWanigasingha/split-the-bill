package com.system.router.registration.di

import com.system.router.registration.repos.RegistrationRepository
import com.system.router.registration.repos.RegistrationRepositoryTestImpl
import com.system.router.registration.service.RegistrationService
import com.system.router.registration.service.RegistrationServiceImpl
import org.koin.dsl.bind
import org.koin.dsl.module
import org.koin.plugin.module.dsl.single

val registrationModule = module {
    single<RegistrationServiceImpl>() bind RegistrationService::class
    single<RegistrationRepositoryTestImpl>() bind RegistrationRepository::class
}