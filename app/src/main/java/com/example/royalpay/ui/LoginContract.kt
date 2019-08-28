package com.example.royalpay.ui

import com.example.royalpay.model.DemoRes
import com.example.royalpay.util.BasePresenter
import com.example.royalpay.util.BaseView

interface LoginContract {
    interface View : BaseView {
        fun doLogin()
        fun showSignInValidateErrorMsg(msg: String)
        fun setForgotPsswrdResp(res: DemoRes)
        fun setLoginResp(res: DemoRes)
    }

    interface Presenter : BasePresenter<View> {
        fun validateLogin(partnerCode: String?, credentialCode: String?,nonceString:String?,timeStamp:String?): Boolean
        fun validateForgotPassword(name: String?): Boolean
        fun doLogin(partnerCode: String, credentialCode: String,nonceString:String,timeStamp:String)
        fun forgotPassowrd(name: String)
    }
}