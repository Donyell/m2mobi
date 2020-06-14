package nl.donyell.m2mobi.presentation.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import nl.donyell.m2mobi.presentation.di.module.NetworkModule
import nl.donyell.m2mobi.presentation.di.module.RepositoryModule
import nl.donyell.m2mobi.presentation.di.module.UseCaseModule
import nl.donyell.m2mobi.presentation.fragment.DetailFragment
import nl.donyell.m2mobi.presentation.fragment.MainFragment
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        RepositoryModule::class,
        UseCaseModule::class,
        NetworkModule::class
    ]
)
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun inject(mainFragment: MainFragment)
    fun inject(detailFragment: DetailFragment)
}