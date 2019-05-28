package com.hokagelab.www.footballmatchdua.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.bumptech.glide.Glide
import com.hokagelab.www.footballmatchdua.R
import com.hokagelab.www.footballmatchdua.model.LastEvent
import com.hokagelab.www.footballmatchdua.model.TeamDetail
import kotlinx.android.synthetic.main.item_logo.view.*
import kotlinx.android.synthetic.main.item_player.view.*
import org.jetbrains.anko.find
import java.text.SimpleDateFormat
import java.util.*

class PlayerAdapter(
    private val item: MutableList<TeamDetail>,
    private val listener: (TeamDetail) -> Unit
    ) : RecyclerView.Adapter<com.hokagelab.www.footballmatchdua.adapter.PlayerAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_player, parent, false))
    }

    override fun getItemCount(): Int = item.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(item[position], listener)
    }

    inner class ViewHolder(view: View) :RecyclerView.ViewHolder(view){
        private val namaPlayer: TextView = view.find(R.id.player_nama)
        private val posisiPlayer: TextView = view.find(R.id.player_posisi)

        fun bindItem(item: TeamDetail, listener: (TeamDetail) -> Unit){
            namaPlayer.text = item.strPlayer
            posisiPlayer.text = item.strPosition
            Glide.with(itemView.context).load(item?.playerfoto).into(itemView.player_image)


            itemView.setOnClickListener {
                listener(item)
            }

        }
    }

}