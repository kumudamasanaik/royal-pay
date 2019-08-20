package com.example.royalpay.util

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.WindowManager
import androidx.multidex.MultiDexApplication
import com.crashlytics.android.Crashlytics
import com.example.royalpay.BuildConfig
import com.example.royalpay.apiPresenter.RestApi
import com.facebook.stetho.Stetho
import com.facebook.stetho.okhttp3.StethoInterceptor
import io.fabric.sdk.android.Fabric
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

class AppController : MultiDexApplication(), Application.ActivityLifecycleCallbacks {

    companion object {
        val TAG: String = "AppController"
        lateinit var mInstance: AppController
        lateinit var service: RestApi
        var mActivity: Activity? = null
        var mCActivity = ""
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = this
        mInstance = this
        createRetrofitObj()
        Fabric.with(this, Crashlytics())
        registerActivityLifecycleCallbacks(this)
    }

    private fun createRetrofitObj() {


    }

    override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {
        mActivity = activity!!
        activity!!.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        activity!!.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN)
    }

    override fun onActivityPaused(activity: Activity?) {
        mActivity = null
    }

    fun getRequestHeader(): OkHttpClient {
        val okHttpClient: OkHttpClient.Builder = OkHttpClient.Builder()
        okHttpClient.readTimeout(60, TimeUnit.SECONDS)
        okHttpClient.writeTimeout(60, TimeUnit.SECONDS)
        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this)
            val logging: HttpLoggingInterceptor = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
            okHttpClient.addNetworkInterceptor(StethoInterceptor())
            okHttpClient.addInterceptor(logging)
        }
        return okHttpClient.build()
    }

    override fun onActivityResumed(activity: Activity?) {
        mActivity = activity
        mCActivity = activity!!::class.java.simpleName
    }

    override fun onActivityStarted(activity: Activity?) {
        mActivity = activity
    }

    override fun onActivityDestroyed(p0: Activity) {
    }

    override fun onActivitySaveInstanceState(p0: Activity, p1: Bundle) {
    }

    override fun onActivityStopped(p0: Activity) {
    }
}