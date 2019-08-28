package com.example.royalpay.ui

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.royalpay.R
import com.example.royalpay.apiPresenter.NetworkStatus
import com.example.royalpay.model.DemoRes
import com.example.royalpay.ui.paymentDemonstration.PaymentActivity
import com.example.royalpay.util.CommonUtils
import com.example.royalpay.util.showToastMsg
import kotlinx.android.synthetic.main.activity_sign_in.*
import java.security.NoSuchAlgorithmException
import java.security.SecureRandom


class LogInActivity : AppCompatActivity(), LoginContract.View, View.OnClickListener {

    private lateinit var context: Context
    private val TAG = "SignInActivity"
    private lateinit var presenter: LoginPresenter
    private lateinit var unixTime: String
    private lateinit var rand: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        context = this
        init()
    }

    override fun init() {
        presenter = LoginPresenter(this)
        btn_sign_in.setOnClickListener (this)
        generateNonce()
        generateUnixTimeStrap()
    }

    private fun generateUnixTimeStrap() {
        unixTime = (System.currentTimeMillis() / 1000L).toString()
        CommonUtils.saveUnixTime(unixTime)
        ed_time_stamp.setText(unixTime)
    }

    private fun generateNonce() {
        val nonce = ByteArray(16)
        try {
            rand = SecureRandom.getInstance("SHA1PRNG").toString()
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        }
        val oauth_nonce = rand
        Log.d("nonce value", oauth_nonce)
        CommonUtils.saveNonce(oauth_nonce)
        ed_nonce_string.setText(oauth_nonce)
    }

    override fun onClick(view: View?) {
        when (view!!.id) {
            R.id.btn_sign_in -> doLogin()
        }
    }

    override fun doLogin() {
        if (presenter.validateLogin(
                ed_partner_code.text.toString(),
                ed_credential_code.text.toString(),
                ed_nonce_string.text.toString(),
                ed_time_stamp.text.toString())
        ) {
            if (NetworkStatus.isOnline2(this)) {
                showLoader()
                /* presenter.doLogin(
                     ed_partner_code.text.toString(),
                     ed_credential_code.text.toString(),
                     ed_nonce_string.text.toString(),
                     ed_time_stamp.text.toString()
                 )*/
                navigateToPaymentScreen()

            } else {
                showToastMsg(getString(R.string.error_no_internet))
            }
        }
    }

    private fun navigateToPaymentScreen() {
       /* val intent = Intent()
        intent.action = Intent.ACTION_VIEW
        intent.addCategory(Intent.CATEGORY_BROWSABLE)
        intent.data = Uri.parse("https://example.royalpay.com.au/api/payment/order")
        startActivity(intent)
*/
              val intent = Intent(context, PaymentActivity::class.java)
                       startActivity(intent)
                       finish()
    }

    override fun setLoginResp(res: DemoRes) {

    }

    override fun setForgotPsswrdResp(res: DemoRes) {

    }

    override fun showSignInValidateErrorMsg(msg: String) {
        when (msg) {
            "1" -> {
                first_tx_error_requires.visibility = View.VISIBLE
            }
            "2" -> {
                first_tx_error_requires.visibility = View.GONE
                second_tx_error_requires.visibility = View.VISIBLE
            }
            "3" -> {
                second_tx_error_requires.visibility = View.GONE
                third_tx_error_requires.visibility = View.VISIBLE
            }
            "4" -> {
                third_tx_error_requires.visibility = View.GONE
                fourth_tx_error_requires.visibility = View.VISIBLE
            }
        }
    }

    override fun showMsg(msg: String?) {
        showToastMsg(msg!!)
    }

    override fun showViewState(state: Int) {

    }

    override fun showLoader() {
        CommonUtils.showLoading(this, true)
    }

    override fun hideLoader() {
        CommonUtils.hideLoading()
    }
}