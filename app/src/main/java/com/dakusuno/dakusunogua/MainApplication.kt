@file:Suppress("unused")
package com.dakusuno.dakusunogua

import android.app.Application
import com.dakusuno.dakusunogua.di.networkModule
import com.dakusuno.dakusunogua.di.repositoryModule
import com.dakusuno.dakusunogua.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class MainApplication :Application(){
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MainApplication)
            modules(networkModule)
            modules(repositoryModule)
            modules(viewModelModule)
        }
        if(BuildConfig.DEBUG){
            Timber.plant(Timber.DebugTree())
        }
    }
}