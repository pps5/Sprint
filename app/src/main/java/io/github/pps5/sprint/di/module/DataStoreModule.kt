package io.github.pps5.sprint.di.module

import androidx.room.Room
import io.github.pps5.sprint.data.store.db.AppDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val dataStoreModule = module {
    single {
        Room.databaseBuilder(
            androidApplication(),
            AppDatabase::class.java,
            "database"
        ).build()
    }
}