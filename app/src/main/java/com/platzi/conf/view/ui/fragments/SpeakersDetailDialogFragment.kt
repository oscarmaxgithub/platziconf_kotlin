package com.platzi.conf.view.ui.fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.platzi.conf.R
import com.platzi.conf.databinding.FragmentSpeakersDetailDialogBinding
import com.platzi.conf.model.Speaker
import java.text.SimpleDateFormat

class SpeakersDetailDialogFragment : DialogFragment() {

    private lateinit var binding: FragmentSpeakersDetailDialogBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_speakers_detail_dialog, container, false)
        binding = FragmentSpeakersDetailDialogBinding.inflate(inflater, container, false)
        var view = binding.root
        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.FullScreenDialogPlatziStyle)
    }

    @SuppressLint("SimpleDateFormat")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbarspeakerDetail.navigationIcon =
            ContextCompat.getDrawable(view.context, R.drawable.ic_close_24)
        binding.toolbarspeakerDetail.setTitleTextColor(Color.WHITE)
        binding.toolbarspeakerDetail.setNavigationOnClickListener() {
            dismiss()
        }
        val speaker = arguments?.getSerializable("speaker") as Speaker
        binding.toolbarspeakerDetail.title = speaker.name
        binding.txtspeakerDetailSpeaker.text = speaker.name
        binding.txtspeakerDetailWorkPlace.text = speaker.workplace
//        var pattern = "dd/MM/yyyy hh:mm a"
//        var sdf = SimpleDateFormat(pattern)
//        var dateformated: String = sdf.format(speaker.datetime)
        binding.txtspeakerDetailDescription.text = speaker.biography
        binding.txtspeakerJob.text = speaker.jobtitle
        Glide.with(this)
            .load(speaker.image)
            .apply(RequestOptions.circleCropTransform())
            .into(binding.ivFotoSpeaker)
        binding.ivTwitterIcon.setOnClickListener{
            val url = speaker.twitter
            val uri = Uri.parse("https://twitter.com/"+url)
            val launchBrowser = Intent(Intent.ACTION_VIEW, uri)
            startActivity(launchBrowser)
        }

    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT)
    }
}