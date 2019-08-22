package com.example.royalpay.apiPresenter

import com.example.royalpay.constants.Constants
import com.example.royalpay.util.CommonUtils
import com.google.gson.JsonObject

object ApiRequestParam {
    var respParamObj = JsonObject()
    fun getCommonParameter(jsonObject: JsonObject) {
        jsonObject.addProperty(Constants.CUSTOMER_ID, CommonUtils.getCustomerID())
    }

    fun getSession(jsonObject: JsonObject) {
        jsonObject.addProperty(Constants._SESION, CommonUtils.getSession())
    }

    /*LOGIN SCREEN */
    fun login(email: String, password: String): JsonObject {
        respParamObj = JsonObject()
        respParamObj.apply {
           /* addProperty(Constants.email, email)
            addProperty(Constants.password, password)*/
        }
        return respParamObj
    }

}