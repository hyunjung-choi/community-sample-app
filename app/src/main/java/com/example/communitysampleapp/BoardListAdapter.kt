package com.example.communitysampleapp

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.communitysampleapp.databinding.ItemBoardListBinding

class BoardListAdapter(val List: MutableList<Model>) : BaseAdapter() {
    override fun getCount(): Int = List.count()

    override fun getItem(position: Int): Any = List[position]

    override fun getItemId(position: Int): Long = position.toLong()

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val binding =
            ItemBoardListBinding.inflate(LayoutInflater.from(parent?.context), parent, false)

        binding.tvItemBoardList.text = List[position].title

        return binding.root
    }
}