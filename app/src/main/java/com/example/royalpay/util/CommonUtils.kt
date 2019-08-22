package com.example.royalpay.util

import android.app.ProgressDialog
import com.example.royalpay.constants.Constants
import com.example.royalpay.listener.ISelectedDateListener
import java.text.SimpleDateFormat
import java.util.*

class CommonUtils {
    companion object {
        private var dateListener: ISelectedDateListener? = null
        private var TAG: String = "COMMON UTILS"
        private var myProgressDialog: ProgressDialog? = null

        fun getCustomerID(): String {
            return SharedPreferenceManager.getPrefVal(
                Constants.CUSTOMER_ID,
                "",
                SharedPreferenceManager.VALUE_TYPE.STRING
            ) as String
        }

        fun getSession(): String {
            val session = SharedPreferenceManager.getPrefVal(
                Constants._SESION,
                "",
                SharedPreferenceManager.VALUE_TYPE.STRING
            ) as String
            if (session.isEmpty()) {
                return generateSession()
            }
            return session
        }

        private fun generateSession(): String {
            return try {
                val chars = "abcdefghijklmnopqrstuvwxyz".toCharArray()
                val sb = StringBuilder()
                val random = Random()
                for (i in 0..15) {
                    val c = chars[random.nextInt(chars.size)]
                    sb.append(c)
                }
                val randomString = sb.toString() + "_" + SimpleDateFormat("ddMMyyyyhhmmssSSS").format(java.util.Date())
                SharedPreferenceManager.setPrefVal(
                    Constants._SESION,
                    randomString,
                    SharedPreferenceManager.VALUE_TYPE.STRING
                )
                randomString
            } catch (ex: Exception) {
                ex.printStackTrace()
                return ""
            }
        }


        fun saveOtpVerificationHashKey(otpStaticKey: String) {
            SharedPreferenceManager.setPrefVal(
                SharedPreferenceManager.OTP_VERIFICATION_KEY,
                otpStaticKey,
                SharedPreferenceManager.VALUE_TYPE.STRING
            )

        }
    }
}