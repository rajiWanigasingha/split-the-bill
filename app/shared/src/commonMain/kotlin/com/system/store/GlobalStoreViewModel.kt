package com.system.store

import androidx.lifecycle.ViewModel
import eu.anifantakis.lib.ksafe.KSafe
import eu.anifantakis.lib.ksafe.invoke
import kotlin.time.Clock
import kotlin.time.Instant

class GlobalStoreViewModel(
    private val prefs: KSafe,
    private val vault: KSafe
) : ViewModel() {

    private var authData by vault(AuthInformationDTO())

    fun setAuthInfo(auth: AuthInformationDTO) {
        authData = auth
    }

    fun getAccessToken(): String? {

        val expired = authData.accessTokenExpireDate ?: return null

        if (Instant.parse(expired) < Clock.System.now()) {
            return null
        }

        return authData.accessToken
    }
}