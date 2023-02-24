package com.example.communitysampleapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.communitysampleapp.databinding.ActivityBoardWriteBinding
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class BoardWriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBoardWriteBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_board_write)
        setContentView(binding.root)

        binding.btnBoardWrite.setOnClickListener {
            val database = Firebase.database
            val myRef = database.getReference("message")
            val text = binding.etBoardWrite.text.toString()

            myRef.push().setValue(
                Model(text)
            )
        }
    }
}