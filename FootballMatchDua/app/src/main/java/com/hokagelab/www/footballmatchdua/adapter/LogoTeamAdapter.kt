package com.hokagelab.www.footballmatchdua.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.hokagelab.www.footballmatchdua.R
import com.hokagelab.www.footballmatchdua.model.TeamLogo
import kotlinx.android.synthetic.main.item_logo.view.*

class LogoTeamAdapter(
    private val item: MutableList<TeamLogo>,
    private val listener: (TeamLogo) -> Unit
    ) : RecyclerView.Adapter<LogoTeamAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_logo, parent, false))
    }

    override fun getItemCount(): Int = item.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(item[position], listener)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindItem(
            item: TeamLogo,
            listener: (TeamLogo) -> Unit) {
            itemView.tv_logo.text = item.strTeam
            Glide.with(itemView.context).load(item.teambadge).into(itemView.imgLogo)
            itemView.setOnClickListener {
                listener(item)
            }

        }

    }

}