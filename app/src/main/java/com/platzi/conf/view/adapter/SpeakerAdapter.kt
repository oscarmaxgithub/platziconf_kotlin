package com.platzi.conf.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.platzi.conf.R
import com.platzi.conf.model.Speaker
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

/**
 * @project: PlatziConf
 * @AUTOR: OSCAR_DIAZ
 * @FECHA: 3/07/2021
 */
class SpeakerAdapter(val speakerlistener: SpeakerListener) : RecyclerView.Adapter<SpeakerAdapter.ViewHolder>() {

    val listSpeakers=ArrayList<Speaker>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_speaker,parent,false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val objSpeaker=listSpeakers.get(position) as Speaker
        holder.tvSpeakerName.text=objSpeaker.name
        holder.tvSpeakerJob.text=objSpeaker.jobtitle

        Glide.with(holder.itemView.context)
            .load(objSpeaker.image)
            .apply(RequestOptions.circleCropTransform())
            .into(holder.imgSpeaker)

        holder.itemView.setOnClickListener{
            speakerlistener.onSpeakerOnClicked(objSpeaker ,position)
        }



    }

    fun updateData(dataList:List<Speaker>){
        listSpeakers.clear()
        listSpeakers.addAll(dataList)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = listSpeakers.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvSpeakerName= itemView.findViewById<TextView>(R.id.item_speakerName)
        val tvSpeakerJob= itemView.findViewById<TextView>(R.id.item_speakerJob)
        val imgSpeaker= itemView.findViewById<ImageView>(R.id.item_speakerimg)
    }
}