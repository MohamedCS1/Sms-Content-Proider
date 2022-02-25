package com.example.smscontentprovider

import android.annotation.SuppressLint
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SmsAdapter : RecyclerView.Adapter<SmsAdapter.SmsViewHolder>() {

    var smsarray = arrayListOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SmsViewHolder {
        return SmsViewHolder(LinearLayout.inflate(parent.context ,R.layout.card_sms ,null))
    }

    override fun onBindViewHolder(holder: SmsViewHolder, position: Int) {
        holder.tvsms.text = smsarray[position]
    }

    override fun getItemCount(): Int {
        return smsarray.size
    }

    class SmsViewHolder(itemView : View):RecyclerView.ViewHolder(itemView)
    {
        val tvsms = itemView.findViewById<TextView>(R.id.sms_display)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(smsarray : ArrayList<String>)
    {
        this.smsarray = smsarray
        notifyDataSetChanged()
    }

}