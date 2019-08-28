package com.example.royalpay.ui

import com.example.royalpay.MyApplication
import com.example.royalpay.apiPresenter.ApiRequestParam
import com.example.royalpay.apiPresenter.ApiResponsePresenter
import com.example.royalpay.apiPresenter.ApiType
import com.example.royalpay.apiPresenter.IResponseInterface
import com.example.royalpay.model.DemoRes
import retrofit2.Call
import retrofit2.Response

class LoginPresenter(view: LoginContract.View) : LoginContract.Presenter, IResponseInterface {
    private var iResponseInterface: ApiResponsePresenter = ApiResponsePresenter(this)
    var view: LoginContract.View? = view

    override fun validateLogin(
        partnerCode: String?,
        credentialCode: String?,
        nonceString: String?,
        timeStamp: String?
    ): Boolean {
        /*if (partnerCode.isNullOrEmpty()) {
            return false
        }
        if (credentialCode.isNullOrEmpty()) {
            return false
        }

        if (nonceString.isNullOrEmpty()) {
            return false
        }

        if (timeStamp.isNullOrEmpty()) {
            return false
        }
        return true*/
        if (partnerCode.isNullOrEmpty()) {
            view!!.showSignInValidateErrorMsg("1")
            return false
        }
        if (credentialCode.isNullOrEmpty()) {
            view!!.showSignInValidateErrorMsg("2")
            return false
        }
        if (nonceString.isNullOrEmpty()) {
            view!!.showSignInValidateErrorMsg("3")
            return false
        }
        if (timeStamp.isNullOrEmpty()) {
            view!!.showSignInValidateErrorMsg("4")
            return false
        }
        return true
    }

    override fun validateForgotPassword(name: String?): Boolean {
        if (name.isNullOrEmpty()) {
            return false
        }
        return true
}

    override fun doLogin(partnerCode: String, credentialCode: String,nonceString:String,timeStamp:String) {
        iResponseInterface.callApi(MyApplication.service.doLogin(ApiRequestParam.login(partnerCode, credentialCode, nonceString, timeStamp)),ApiType.LOGIN)
    }

    override fun forgotPassowrd(name: String) {

    }

    override fun <T> onResponseSuccess(call: Call<T>, response: Response<T>, reqType: String) {
        view?.hideLoader()
        if (response.isSuccessful) {
            when (reqType) {
                ApiType.LOGIN ->
                    view?.setLoginResp(response.body() as DemoRes)

                ApiType.FORGOTPASSWORD ->
                    view?.setForgotPsswrdResp(response.body() as DemoRes)
            }
        }
    }

    override fun <T> onResponseFailure(call: Call<T>, responseError: Throwable, reqType: String) {
        view?.hideLoader()
        when (reqType) {
            ApiType.LOGIN ->
                view?.showMsg(responseError.message)
            ApiType.FORGOTPASSWORD ->
                view?.showMsg(responseError.message)

        }
    }
}