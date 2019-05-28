package com.hokagelab.www.footballmatchdua.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.provider.CalendarContract
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import com.hokagelab.www.footballmatchdua.R
import com.hokagelab.www.footballmatchdua.model.LastEvent
import com.hokagelab.www.footballmatchdua.model.NextEvent
import com.hokagelab.www.footballmatchdua.util.dateNewFormat
import kotlinx.android.synthetic.main.item_list.view.*
import org.jetbrains.anko.find
import java.text.SimpleDateFormat
import java.util.*

class NextAdapter(
    private val item: MutableList<NextEvent>,
    private val listener: (NextEvent) -> Unit
    ) : RecyclerView.Adapter<NextAdapter.ViewHolder>(){

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

        private var isAdded: Boolean = false


        fun bindItem(item: NextEvent, listener: (NextEvent) -> Unit){
            tanggal.dateSchedule.text = dateNewFormat(item.dateSchedule, false)
            waktu.dateTime.text = dateNewFormat(item.strTime, true)
            scoreSatu.text = item.scoresatu
            scoreDua.text = item.scoredua
            clubSatu.text = item.clubsatu
            clubDua.text = item.clubdua

            itemView.setOnClickListener {
                listener(item)
            }

            notif()

            itemView.findViewById<CheckBox>(R.id.addCalendar).setOnClickListener {
                Toast.makeText(itemView.context, "Apakah bisa = " + item.eventId, Toast.LENGTH_SHORT).show()
                if (!isAdded) {
                    addToCalender(item)
                    isAdded = !isAdded
                } else {
                    isAdded = !isAdded
                }
                notif()
            }

        }

        private fun notif() {
            itemView.addCalendar.isChecked = isAdded
        }

        @SuppressLint("SimpleDateFormat")
        fun addToCalender(item: NextEvent?) {
            val time = item?.strTime?.split("+")?.get(0)
            val date = item?.dateSchedule
            val dateClock = "$date $time"
            val simpleDate = SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
            simpleDate.timeZone = TimeZone.getTimeZone("Asia/Jakarta")
            val dateFormat = simpleDate.parse(dateClock)
            val timeInMillis = dateFormat.time
            val end = timeInMillis + 5400000

            val intent = Intent(Intent.ACTION_INSERT)
                .setData(CalendarContract.Events.CONTENT_URI)
                .putExtra(CalendarContract.Events.TITLE, "${item?.clubsatu} VS ${item?.clubdua}")
                .putExtra(CalendarContract.Events.EVENT_LOCATION, "Streaming Dong")
                .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, timeInMillis)
                .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, end)
            itemView.context.startActivity(intent)
        }



    }

}