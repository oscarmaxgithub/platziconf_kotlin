package com.platzi.conf.view.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.platzi.conf.R
import com.platzi.conf.databinding.FragmentSchedulerBinding
import com.platzi.conf.databinding.FragmentSpeakersBinding
import com.platzi.conf.model.Conference
import com.platzi.conf.model.Speaker
import com.platzi.conf.view.adapter.SpeakerAdapter
import com.platzi.conf.view.adapter.SpeakerListener
import com.platzi.conf.viewmodel.ScheduleViewModel
import com.platzi.conf.viewmodel.SpeakerViewModel

class SpeakersFragment : Fragment(), SpeakerListener {

    private lateinit var  speakerAdap: SpeakerAdapter
    private lateinit var  viewModel: SpeakerViewModel
    private lateinit var binding: FragmentSpeakersBinding
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       // return inflater.inflate(R.layout.fragment_speakers, container, false)
        binding = FragmentSpeakersBinding.inflate(inflater, container, false)
        val view= binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(SpeakerViewModel::class.java)
        viewModel.refresh()

        speakerAdap= SpeakerAdapter(this)
        binding.rvSpeakers.apply {
            layoutManager= GridLayoutManager(view.context,2)
            adapter= speakerAdap
        }
        observeViewModel()
//        R.id.rvScheduler.apply {
//            layoutMan
//        }
    }

    private fun observeViewModel() {
        viewModel.listSpeaker.observe(viewLifecycleOwner, Observer<List<Speaker>> { speaker->
            speakerAdap.updateData(speaker)
        })
        viewModel.isLoading.observe(viewLifecycleOwner, Observer<Boolean> {
            if(it!=null){
                binding.rlBaseSpeakers.visibility=View.INVISIBLE
            }
        })
    }

    override fun onSpeakerOnClicked(speaker: Speaker, pos: Int) {
        val bundle= bundleOf("speaker" to speaker)
        findNavController().navigate(R.id.speakerDetailsFragmentDialog,bundle)
    }

}