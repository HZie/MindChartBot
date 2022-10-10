package hcil.hzie.mindchart

import android.app.Application

class MindChartApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        // initialize Amplify when application is starting
        Backend.initialize(applicationContext)
    }
}