@file:Suppress("unused")
package com.dakusuno.dakusunogua

import android.app.Application
import android.content.Context
import com.cioccarellia.ksprefs.KsPrefs
import com.cioccarellia.ksprefs.config.EncryptionType
import com.cioccarellia.ksprefs.config.model.KeySize
import com.dakusuno.dakusunogua.di.networkModule
import com.dakusuno.dakusunogua.di.persistenceModule
import com.dakusuno.dakusunogua.di.repositoryModule
import com.dakusuno.dakusunogua.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class MainApplication :Application(){

    companion object {
        lateinit var appContext: Context

        private val aes = EncryptionType.AesEcb("GUAGithubUserApp", KeySize.SIZE_128)
        private val keyStore = EncryptionType.KeyStore("alias0")

        val prefs by lazy {
            KsPrefs(appContext) {
                encryptionType = aes
            }
        }
    }

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MainApplication)
            modules(networkModule)
            modules(repositoryModule)
            modules(viewModelModule)
            modules(persistenceModule)
        }
        appContext = this
        if(BuildConfig.DEBUG){
            Timber.plant(Timber.DebugTree())
        }
    }
}