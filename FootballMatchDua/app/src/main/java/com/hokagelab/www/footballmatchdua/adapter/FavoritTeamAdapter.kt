package com.hokagelab.www.footballmatchdua.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.hokagelab.www.footballmatchdua.R
import com.hokagelab.www.footballmatchdua.model.FavoriteTeam
import kotlinx.android.synthetic.main.item_logo.view.*
import kotlinx.android.synthetic.main.item_team.view.*

class FavoritTeamAdapter(
    private val items: MutableList<FavoriteTeam>,
    private val listener: (FavoriteTeam) -> Unit): RecyclerView.Adapter<FavoritTeamAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritTeamAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_team, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(items[position])
    }

    override fun getItemCount(): Int = items.size

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view){

        fun bindItem(items: FavoriteTeam){
            itemView.team_nama.text = items.NAMA_TEAM
            Glide.with(itemView.context).load(items.FOTO_TEAM).into(itemView.team_image)


            itemView.setOnClickListener {
                listener(items)
            }
        }

    }


}