package com.yesynergy.lib.servicios

import com.yesynergy.lib.red.ServiceInterface
import org.json.JSONObject

class ApiController constructor(serviceInjector: ServiceInterface): ServiceInterface{
    private val service: ServiceInterface = serviceInjector
    override fun auth(usuario: String, clave: String, completionHandler: (response: JSONObject?) -> Unit) {
        service.auth(usuario,clave,completionHandler)
    }
}