package com.acemirr.projectwork.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.acemirr.projectwork.R
import com.acemirr.projectwork.data.model.Meet
import com.acemirr.projectwork.databinding.ItemMeetBinding
import com.acemirr.projectwork.utils.formatDate

class ListMeetAdapter:ListAdapter<Meet,ListMeetAdapter.MeetViewHolder>(diff) {

    private var listener:Listener?=null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MeetViewHolder {
        val v:ItemMeetBinding = ItemMeetBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MeetViewHolder(v)
    }

    override fun onBindViewHolder(holder: MeetViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    fun setListener(listener:Listener){
        this.listener = listener
    }
    inner class MeetViewHolder(private val binding:ItemMeetBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(data:  Meet){
            binding.tvMeetCreator.text = data.creator
            binding.tvMeetName.text = data.name
            binding.tvMeetStartDate.text = binding.root.context.getString(R.string.start_date, data.startDate.formatDate("yyyy-MM-dd HH:mm"))
            binding.tvMeetEndDate.text = binding.root.context.getString(R.string.end_date, data.endDate.formatDate("yyyy-MM-dd HH:mm"))
            binding.root.setOnClickListener {
                listener?.onItemClicked(data)
            }
        }
    }

    companion object {
        val diff = object : DiffUtil.ItemCallback<Meet>() {
            override fun areItemsTheSame(oldItem: Meet, newItem: Meet): Boolean {
                return oldItem==newItem
            }

            override fun areContentsTheSame(oldItem: Meet, newItem: Meet): Boolean {
                return oldItem.id==newItem.id
            }
        }
    }
    interface Listener{
        fun onItemClicked(data: Meet)
    }
}