package com.example.practice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {
    private lateinit var data: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        data= Firebase.auth
        val email=findViewById<EditText>(R.id.idemail)
        val pass=findViewById<EditText>(R.id.idpassword)
        val button=findViewById<Button>(R.id.idbtn)
        button.setOnClickListener {
            data.signInWithEmailAndPassword(email.text.toString(),pass.text.toString())
                .addOnSuccessListener {
                    Toast.makeText(this,"LogIn successfully", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener {
                    Toast.makeText(this,it.message, Toast.LENGTH_SHORT).show()
                }
        }
    }
}