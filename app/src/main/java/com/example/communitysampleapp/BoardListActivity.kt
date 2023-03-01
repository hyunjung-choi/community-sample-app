package com.example.communitysampleapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.example.communitysampleapp.databinding.ActivityBoardListBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class BoardListActivity : AppCompatActivity() {

    private val TAG = "BoardListActivity"

    private lateinit var binding: ActivityBoardListBinding
    private lateinit var adapter: BoardListAdapter

    val list = mutableListOf<Model>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_board_list)
        setContentView(binding.root)

        binding.btnListWrite.setOnClickListener {
            val intent = Intent(this, BoardWriteActivity::class.java)
            startActivity(intent)
        }

        getData()
        adapter = BoardListAdapter(list)
        binding.lvList.adapter = adapter

    }

    private fun getData() {
        val database = Firebase.database
        val myRef = database.getReference("board")

        val postListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                for (dataModel in snapshot.children) {
                    val item = dataModel.getValue(Model::class.java)
                    if (item != null) {
                        list.add(item)
                    }
                    Log.d(TAG, "onDataChange: ${item}")
                }

                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w(TAG, "onCancelled: ", error.toException())
            }
        }

        myRef.addValueEventListener(postListener)
    }
}