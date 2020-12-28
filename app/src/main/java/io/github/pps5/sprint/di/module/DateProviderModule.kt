package io.github.pps5.sprint.di.module

import io.github.pps5.sprint.util.DateProvider
import io.github.pps5.sprint.util.ThreeTenABPDateProvider
import org.koin.dsl.module

val dateProviderModule = module {
    single<DateProvider> { ThreeTenABPDateProvider() }
}