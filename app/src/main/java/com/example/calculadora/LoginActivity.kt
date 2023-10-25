package com.example.calculadora

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val analytics_2 = FirebaseAnalytics.getInstance(this)
        analytics_2.setAnalyticsCollectionEnabled(true)

        lateinit var analytics: FirebaseAnalytics
        analytics = Firebase.analytics


        val botao_login: Button = findViewById(R.id.botao_login)
        val login: EditText = findViewById(R.id.campo_login)
        val senha: EditText = findViewById(R.id.campo_senha)
        val checkBox: CheckBox = findViewById(R.id.check_login)

        botao_login.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            if(login.text.isNotEmpty() && login.text.length > 5) {
                if(senha.text.isNotEmpty() && senha.text.length > 5) {
                    if(checkBox.isChecked) {
                        startActivity(intent)
                        finish()
                    }else{
                        Toast.makeText(this, "É necessário marcar a box.", Toast.LENGTH_LONG).show()
                    }
                }else{
                    Toast.makeText(this, "Campo senha mínimo 6 caractéres.", Toast.LENGTH_LONG).show()
                }
            }else{
                Toast.makeText(this, "Campo login mínimo 6 caractéres..", Toast.LENGTH_LONG).show()
            }
        }
    }
    override fun onStart() {
        super.onStart()
    }
    override fun onStop() {
        super.onStop()
        Log.v("StopLogin", "Stop Login executado.")

        val login : EditText = findViewById(R.id.campo_login)
        val senha : EditText = findViewById(R.id.campo_senha)
        val checkBox: CheckBox = findViewById(R.id.check_login)

        login.text = null
        senha.text = null
        checkBox.isChecked = false
    }
}