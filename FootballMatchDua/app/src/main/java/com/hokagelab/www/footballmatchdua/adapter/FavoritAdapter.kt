package com.hokagelab.www.footballmatchdua.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.hokagelab.www.footballmatchdua.R
import com.hokagelab.www.footballmatchdua.model.Favorite
import org.jetbrains.anko.find

class FavoritAdapter(
    private val items:MutableList<Favorite>,
    private val listener: (Favorite) -> Unit): RecyclerView.Adapter<FavoritAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false))
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(items[position])
    }

    override fun getItemCount(): Int = items.size

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view){
        val datee: TextView = view.find(R.id.dateSchedule)
        val clubSatu: TextView = view.find(R.id.clubsatu)
        val clubDua: TextView = view.find(R.id.clubdua)
        val scoreSatu: TextView = view.find(R.id.scoresatu)
        val scoreDua: TextView = view.find(R.id.scoredua)

        fun bindItem(items: Favorite){
//            val format = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
//            val date = format.parse(items.dateSchedule)
//            val dateText = SimpleDateFormat("EEEE, dd-MM-yyyy", Locale.getDefault())
//                .format(date).toString()

            setText(datee, items.dateSchedule)
            setText(clubSatu, items.namaSatu)
            setText(clubDua, items.namaDua)
            setText(scoreSatu, items.SatuScore)
            setText(scoreDua, items.DuaScore)


            itemView.setOnClickListener {
                listener(items)
            }
        }

        private fun setText(textView: TextView, value: String) {
            if (value == "null")
                textView.visibility = View.GONE
            else
                textView.text = value
        }

    }


}