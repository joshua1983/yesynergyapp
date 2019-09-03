package com.yesynergy.lib.servicios

import android.app.Application
import android.content.Context
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyLog
import com.android.volley.VolleyLog.TAG
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.yesynergy.lib.red.BackendVolley
import com.yesynergy.lib.red.ServiceInterface
import org.json.JSONObject



class Autenticacion : ServiceInterface {

    var URL:String = "http://admin.yesynergy.com/index.php/mobile/autenticarEstudianteMobile"

    override fun auth(usuario:String, clave:String, completionHandler: (response: JSONObject?) -> Unit){
        if (usuario.isNotEmpty() && clave.isNotEmpty()) {

            var postDataUsuario = JSONObject()
            postDataUsuario.put("usuario", usuario)
            postDataUsuario.put("password", clave)

            val jsonObjectRequest = JsonObjectRequest(Request.Method.POST, URL, postDataUsuario,
                Response.Listener<JSONObject> { response ->
                    completionHandler(response)
                },
                Response.ErrorListener { error ->
                    VolleyLog.e(TAG, "/post request fail! Error: ${error.message}")
                    completionHandler(null)
                })

            BackendVolley.instance?.addToRequestQueue(jsonObjectRequest, TAG)
        }
    }
}