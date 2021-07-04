package com.platzi.conf.view.ui.fragments

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import com.platzi.conf.R
import com.platzi.conf.databinding.FragmentSchedulerDetailDialogBinding
import com.platzi.conf.model.Conference
import java.text.SimpleDateFormat


class SchedulerDetailDialogFragment : DialogFragment() {

    private lateinit var binding: FragmentSchedulerDetailDialogBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL,R.style.FullScreenDialogPlatziStyle)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSchedulerDetailDialogBinding.inflate(inflater, container, false)
        var view = binding.root
        return view
        //return inflater.inflate(R.layout.fragment_scheduler_detail_dialog, container, false)
    }

    @SuppressLint("SimpleDateFormat")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbarConferenceDetail.navigationIcon =
            ContextCompat.getDrawable(view.context, R.drawable.ic_close_24)
        binding.toolbarConferenceDetail.setTitleTextColor(Color.WHITE)
        binding.toolbarConferenceDetail.setNavigationOnClickListener() {
            dismiss()
        }
        val conference = arguments?.getSerializable("conference") as Conference
        binding.toolbarConferenceDetail.title = conference.title
        binding.txtSchedulerTitleDetail.text = conference.title
        binding.txtConferenceDetailDescription.text = conference.description
        var pattern = "dd/MM/yyyy hh:mm a"
        var sdf = SimpleDateFormat(pattern)
        var dateformated: String = sdf.format(conference.datetime)
        binding.txtConferenceDetailHour.text = dateformated
        binding.txtConferenceDetailSpeaker.text = conference.speaker
        binding.txtConferenceDetailTagTema.text = conference.tag

    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT)
    }
}