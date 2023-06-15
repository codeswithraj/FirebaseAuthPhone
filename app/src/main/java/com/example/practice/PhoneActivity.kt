package com.example.practice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.hbb20.CountryCodePicker
import java.util.concurrent.TimeUnit

class PhoneActivity : AppCompatActivity() {
    lateinit var phoneNumber:String
    private lateinit var auth:FirebaseAuth
    private lateinit var vId:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_phone)

        auth=Firebase.auth
        var phone = findViewById<EditText>(R.id.contact)
        var ccp = findViewById<CountryCodePicker>(R.id.code)
        var button = findViewById<Button>(R.id.logInBtn)
        button.setOnClickListener {
            ccp.registerCarrierNumberEditText(phone)
            phoneNumber=ccp.fullNumberWithPlus.replace(" ","")
            initiateOtp()
        }
        // verify otp


        var enterOtp=findViewById<EditText>(R.id.verifyId)
        var  verifyButton=findViewById<Button>(R.id.verifyBtn)
        verifyButton.setOnClickListener {

            val credential = PhoneAuthProvider.getCredential(vId, enterOtp.text.toString())
            auth.signInWithCredential(credential)
                .addOnSuccessListener {
                    Toast.makeText(this, "Verified", Toast.LENGTH_SHORT).show()
                    Toast.makeText(this, "Registration successful", Toast.LENGTH_SHORT).show()

                }
                .addOnFailureListener {
                    Toast.makeText(this, "OTP Invalid", Toast.LENGTH_SHORT).show()
                }

        }
    }
    // send otp message
    private fun initiateOtp() {
        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(phoneNumber)
            .setTimeout(30L,TimeUnit.SECONDS)
            .setActivity(this)
            .setCallbacks(object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

                override fun onCodeSent(
                    verificationId: String,
                    forceResendingToken: PhoneAuthProvider.ForceResendingToken
                ) {
                    vId = verificationId
                    Toast.makeText(this@PhoneActivity, "send $verificationId", Toast.LENGTH_SHORT).show()

                }

                override fun onVerificationCompleted(phoneAuthCredential: PhoneAuthCredential) {

                }

                override fun onVerificationFailed(e: FirebaseException) {

                }
            })
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)

    }

}