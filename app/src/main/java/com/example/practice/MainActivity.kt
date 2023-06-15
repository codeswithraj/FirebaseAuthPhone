package com.example.practice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class MainActivity : AppCompatActivity() {
    lateinit var data: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        data= Firebase.auth
        var email=findViewById<EditText>(R.id.email)
        var pass=findViewById<EditText>(R.id.password)
        var button=findViewById<Button>(R.id.btn)
        button.setOnClickListener {
            data.createUserWithEmailAndPassword(email.text.toString(), pass.text.toString())
                .addOnSuccessListener {
                    Toast.makeText(this, "SignUp successfully", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this,LoginActivity::class.java))
                }
                .addOnFailureListener {
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                }

        }

    }
}