package com.example.communitysampleapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.communitysampleapp.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        auth = Firebase.auth
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setContentView(binding.root)

        // 회원 가입 버튼
        binding.btnMainSignUp.setOnClickListener {
            val email = binding.etMainEmail.text.toString()
            val password = binding.etMainPassword.text.toString()

            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, getText(R.string.signup), Toast.LENGTH_LONG).show()

                        val intent = Intent(this, BoardListActivity::class.java)
                        startActivity(intent)
                    } else {

                    }
                }
        }

        // 로그아웃 버튼
        binding.btnMainSignOut.setOnClickListener {
            auth.signOut()
            Toast.makeText(this, getText(R.string.signout), Toast.LENGTH_LONG).show()
        }

        // 로그인 버튼
        binding.btnMainSignIn.setOnClickListener {
            val email = binding.etMainEmail.text.toString()
            val password = binding.etMainPassword.text.toString()

            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, getText(R.string.signin), Toast.LENGTH_LONG).show()

                        val intent = Intent(this, BoardListActivity::class.java)
                        startActivity(intent)
                    } else {

                    }
                }
        }
    }
}