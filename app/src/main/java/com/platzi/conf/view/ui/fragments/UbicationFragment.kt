package com.platzi.conf.view.ui.fragments

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.platzi.conf.R
import com.platzi.conf.databinding.FragmentUbicationBinding
import com.platzi.conf.model.Ubication


class UbicationFragment : Fragment() , OnMapReadyCallback, GoogleMap.OnMarkerClickListener{

private lateinit var binding: FragmentUbicationBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentUbicationBinding.inflate(inflater,container,false)
        var view=binding.root
        return view
//        return inflater.inflate(R.layout.fragment_ubication, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var mapFrag=childFragmentManager.findFragmentById(R.id.mapUbication) as SupportMapFragment

        mapFrag.getMapAsync(this)
    }

    override fun onMapReady(map: GoogleMap) {
        val ubication =Ubication()
        val zoom =16f
        val centerMap=LatLng(ubication.latitud,ubication.longitud)
        map?.animateCamera(CameraUpdateFactory.newLatLngZoom(centerMap,zoom))
        map.uiSettings.isZoomControlsEnabled=true

        val centerMark=LatLng(ubication.latitud,ubication.longitud)
        val markerOptions=MarkerOptions()
        markerOptions.position(centerMark)
        markerOptions.title(ubication.name)

        val bitmpIcon=context?.applicationContext?.let { ContextCompat.getDrawable(it,R.drawable.logo_platzi) }as BitmapDrawable
        val smallMarker= Bitmap.createScaledBitmap(bitmpIcon.bitmap,150,150,false)

        markerOptions.icon(BitmapDescriptorFactory.fromBitmap(smallMarker))

        map.addMarker(markerOptions)
        map.setOnMarkerClickListener ( this )
        map.setMapStyle(MapStyleOptions.loadRawResourceStyle(context,R.raw.custom_map))

    }

    override fun onMarkerClick(p0: Marker): Boolean {
//        val bundle= bundleOf("conference" to conference)
        findNavController().navigate(R.id.ubicationDetailsFragmentDialog)
        return true
    }
}