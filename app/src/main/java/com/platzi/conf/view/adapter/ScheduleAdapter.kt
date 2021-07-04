package com.platzi.conf.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.platzi.conf.R
import com.platzi.conf.model.Conference
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

/**
 * @project: PlatziConf
 * @AUTOR: OSCAR_DIAZ
 * @FECHA: 3/07/2021
 */
class ScheduleAdapter(val schedulelistener: ScheduleListener) : RecyclerView.Adapter<ScheduleAdapter.ViewHolder>() {

    val listConferences=ArrayList<Conference>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_scheduler,parent,false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val objConference=listConferences.get(position) as Conference
        holder.tvConferenceName.text=objConference.title
        holder.tvConferenceTema.text=objConference.tag
        holder.tvConferenceSpeaker.text=objConference.speaker

        val simpleDatef= SimpleDateFormat("HH:mm")
        val simpleDatefampm= SimpleDateFormat("a")

//        val cal=Calendar.getInstance()
//        cal.time=objConference.datetime
        val hourFormat=simpleDatef.format(objConference.datetime)

        holder.tvConferenceHour.text=hourFormat
        holder.tvConferenceampm.text= simpleDatefampm.format(objConference.datetime)
            .uppercase(Locale.getDefault())

        holder.itemView.setOnClickListener{
            schedulelistener.onConferenceClicked( objConference ,position)
        }



    }

    fun updateData(dataList:List<Conference>){
        listConferences.clear()
        listConferences.addAll(dataList)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = listConferences.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvConferenceName= itemView.findViewById<TextView>(R.id.item_tvSchedulerTitle)
        val tvConferenceSpeaker= itemView.findViewById<TextView>(R.id.item_tvSchedulerSpeaker)
        val tvConferenceTema= itemView.findViewById<TextView>(R.id.item_tvSchedulerTema)
        val tvConferenceHour= itemView.findViewById<TextView>(R.id.item_txtSchedulerHour)
        val tvConferenceampm= itemView.findViewById<TextView>(R.id.item_txtSchedulerAMPM)
    }
}