package com.hokagelab.www.footballmatchdua.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.provider.CalendarContract
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.hokagelab.www.footballmatchdua.R
import com.hokagelab.www.footballmatchdua.model.Event
import com.hokagelab.www.footballmatchdua.model.LastEvent
import com.hokagelab.www.footballmatchdua.util.dateNewFormat
import kotlinx.android.synthetic.main.item_list.view.*
import org.jetbrains.anko.find
import org.jetbrains.anko.sdk27.coroutines.onClick
import java.text.SimpleDateFormat
import java.util.*

class EventAdapter(
    private val item: MutableList<Event>,
    private val listener: (Event) -> Unit
    ) : RecyclerView.Adapter<com.hokagelab.www.footballmatchdua.adapter.EventAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false))
    }

    override fun getItemCount(): Int = item.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(item[position], listener)
    }

    inner class ViewHolder(view: View) :RecyclerView.ViewHolder(view){
        private val tanggal: TextView = view.find(R.id.dateSchedule)
        private val waktu: TextView = view.find(R.id.dateTime)
        private val scoreSatu: TextView = view.find(R.id.scoresatu)
        private val scoreDua: TextView = view.find(R.id.scoredua)
        private val clubSatu: TextView = view.find(R.id.clubsatu)
        private val clubDua: TextView = view.find(R.id.clubdua)

        @SuppressLint("SetTextI18n")
        fun bindItem(item: Event, listener: (Event) -> Unit){
            tanggal.text = dateNewFormat(item.dateSchedule, false)

            if (item.strTime.isNullOrEmpty()){
                waktu.dateTime.text = "No Time"
            }else{
                waktu.dateTime.text = dateNewFormat(item.strTime, true)
            }
            if (item.clubSatu.isNullOrEmpty()){
                clubSatu.text = "no team name!"
            }else{
                clubSatu.text = item.clubSatu
            }
            if (item.clubDua.isNullOrEmpty()){
                clubDua.text = "no team name!"
            }else{
                clubDua.text = item.clubDua
            }
            if (item.scoreSatu.isNullOrEmpty()){
                scoreSatu.text = "no data!"
            }else{
                scoreSatu.text = item.scoreSatu
            }
            if (item.scoreDua.isNullOrEmpty()){
                scoreDua.text = "no data!"
            }else {
                scoreDua.text = item.scoreDua
            }

            itemView.setOnClickListener {
                listener(item)
            }

        }



    }




}