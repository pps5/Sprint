package io.github.pps5.sprint

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen
import io.github.pps5.sprint.di.module.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        startKoin {
            androidContext(this@App)
            modules(viewModelModule)
            modules(dataStoreModule)
            modules(repositoryModules)
            modules(useCaseModules)
            modules(dateProviderModule)
        }
    }
}