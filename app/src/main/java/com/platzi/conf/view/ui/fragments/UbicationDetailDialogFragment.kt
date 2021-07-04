package com.platzi.conf.view.ui.fragments

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.platzi.conf.R
import com.platzi.conf.databinding.FragmentUbicationDetailDialogBinding
import com.platzi.conf.model.Ubication

class UbicationDetailDialogFragment : DialogFragment() {

    private lateinit var binding: FragmentUbicationDetailDialogBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL,R.style.FullScreenDialogPlatziStyle)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentUbicationDetailDialogBinding.inflate(inflater,container,false)
        var view=binding.root
        return view
        //return inflater.inflate(R.layout.fragment_ubication_detail_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbarUbicationDetail.navigationIcon =
            ContextCompat.getDrawable(view.context, R.drawable.ic_close_24)
        binding.toolbarUbicationDetail.setTitleTextColor(Color.WHITE)
        binding.toolbarUbicationDetail.setNavigationOnClickListener() {
            dismiss()
        }
        val ubication=Ubication()
        binding.toolbarUbicationDetail.title=ubication.name
        binding.txtNameUbication.text=ubication.name
        binding.txtPhoneDetailUbication.text=ubication.phone
        binding.txtUbicationDetailUbication.text=ubication.address
        binding.txtLinkDetailUbication.text=ubication.website
        Glide.with(this)
            .load(ubication.photo)
            .apply(RequestOptions.circleCropTransform())
            .into(binding.ivFotoUbicacion)
        binding.lltelefonoUbication.setOnClickListener{
            val intent=Intent(Intent.ACTION_DIAL).apply {
                data =Uri.parse("tel:${ubication.phone}")
            }
            startActivity(intent)
        }
        binding.llWebSiteUbication.setOnClickListener {
            val url = ubication.website
            val uri = Uri.parse(url)
            val launchBrowser = Intent(Intent.ACTION_VIEW, uri)
            startActivity(launchBrowser)
        }
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT)
    }

}