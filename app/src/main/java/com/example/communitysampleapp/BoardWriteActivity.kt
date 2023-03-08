package com.example.communitysampleapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
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

        // 글쓰기 버튼
        binding.btnBoardWrite.setOnClickListener {
            val database = Firebase.database
            val myRef = database.getReference("board")
            val text = binding.etBoardWrite.text.toString()

            if (checkWriteIsNull(text)) {
                return@setOnClickListener
            } else {
                myRef.push().setValue(
                    Model(text)
                )
                finish()
            }
        }
    }

    private fun checkWriteIsNull(text: String): Boolean {
        if (TextUtils.isEmpty(text)) {
            binding.etBoardWrite.setError("You did not write any text.")
            return true
        }
        return false
    }
}