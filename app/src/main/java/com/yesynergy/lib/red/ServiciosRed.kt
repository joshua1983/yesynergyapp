package com.yesynergy.lib.red

import android.app.Application
import android.text.TextUtils
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley
import org.json.JSONObject

interface ServiceInterface {
    fun auth(usuario: String, clave: String, completionHandler: (response: JSONObject?) -> Unit)
}

class BackendVolley : Application(){

    val requestQueue: RequestQueue? = null
    get() {
        if (field == null){
            return Volley.newRequestQueue(applicationContext)
        }
        return field
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    fun <T> addToRequestQueue(request:Request<T>, tag:String){
        request.tag = if (TextUtils.isEmpty(tag)) TAG else tag
        requestQueue?.add(request)
    }

    fun <T> addToRequestQueue(request: Request<T>){
        request.tag = TAG
        requestQueue?.add(request)
    }

    fun cancelarRequestPendiente(tag: Any){
        if (requestQueue != null){
            requestQueue!!.cancelAll(tag)
        }
    }

    companion object{
        private val TAG = BackendVolley::class.java.simpleName
        @get:Synchronized var instance: BackendVolley? = null
        private set
    }

}