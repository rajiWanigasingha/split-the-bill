package com.system.router.registration

import org.koin.dsl.bind
import org.koin.dsl.module
import org.koin.plugin.module.dsl.single

val registrationModule = module {
    single<RegistrationServiceImpl>() bind RegistrationService::class
    single<RegistrationRepositoryTestImpl>() bind RegistrationRepository::class
}