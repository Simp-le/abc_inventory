package com.abc.inventory

import android.app.Application
import com.abc.inventory.data.AppContainer
import com.abc.inventory.data.AppDataContainer


class InventoryApplication : Application() {

    /**
     * AppContainer instance used by the rest of classes to obtain dependencies
     */
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}