package com.example.communitysampleapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.communitysampleapp.databinding.ActivityBoardListBinding

class BoardListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBoardListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_board_list)
        setContentView(binding.root)

        binding.btnListWrite.setOnClickListener {
            val intent = Intent(this, BoardWriteActivity::class.java)
            startActivity(intent)
        }
    }
}