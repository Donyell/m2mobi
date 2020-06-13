package nl.donyell.m2mobi

import android.app.Application
import nl.donyell.m2mobi.presentation.di.AppComponent
import nl.donyell.m2mobi.presentation.di.DaggerAppComponent

class M2MobiApp: Application(){

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(applicationContext)
    }
}