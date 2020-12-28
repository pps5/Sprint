package io.github.pps5.sprint.data.di

import androidx.room.Room
import io.github.pps5.sprint.data.internal.repository.GoalRepositoryImpl
import io.github.pps5.sprint.data.internal.store.db.AppDatabase
import io.github.pps5.sprint.data.repository.GoalRepository
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val dataModule = module {
    single<GoalRepository> { GoalRepositoryImpl(get()) }

    single {
        Room.databaseBuilder(
            androidApplication(),
            AppDatabase::class.java,
            "database"
        ).build()
    }
}