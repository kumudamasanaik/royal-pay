package com.example.royalpay.apiPresenter

import com.example.royalpay.model.DemoRes
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    /* @POST(ApiConstants.SIGN_UP)
     fun doRegister(@Body registerInput: SignUpInput): Call<CustomerRes>
 */
    @POST(ApiConstants.LOGIN)
    fun doLogin(@Body json: JsonObject): Call<DemoRes>

}