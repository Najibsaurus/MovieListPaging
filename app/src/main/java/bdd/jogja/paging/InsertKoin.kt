package bdd.jogja.paging

import android.app.Application
import bdd.jogja.paging.di.MainModule
import org.koin.android.ext.android.startKoin

class InsertKoin : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(MainModule))
    }
}