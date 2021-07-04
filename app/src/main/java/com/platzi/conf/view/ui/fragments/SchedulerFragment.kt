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
import androidx.recyclerview.widget.LinearLayoutManager
import com.platzi.conf.R
import com.platzi.conf.databinding.FragmentSchedulerBinding
import com.platzi.conf.model.Conference

import com.platzi.conf.view.adapter.ScheduleAdapter
import com.platzi.conf.view.adapter.ScheduleListener
import com.platzi.conf.viewmodel.ScheduleViewModel


class SchedulerFragment : Fragment() , ScheduleListener{

    private lateinit var  scheduleAdap:ScheduleAdapter
    private lateinit var  viewModel:ScheduleViewModel
    private lateinit var binding: FragmentSchedulerBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_scheduler, container, false)}
        binding = FragmentSchedulerBinding.inflate(inflater, container, false)
        val view= binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(ScheduleViewModel::class.java)
        viewModel.refresh()

        scheduleAdap= ScheduleAdapter(this)
        binding.rvScheduler.apply {
            layoutManager=LinearLayoutManager(view.context,LinearLayoutManager.VERTICAL,false)
            adapter= scheduleAdap
        }
        observeViewModel()
//        R.id.rvScheduler.apply {
//            layoutMan
//        }
    }

    private fun observeViewModel() {
        viewModel.listSchedule.observe(viewLifecycleOwner, Observer<List<Conference>> {schedule->
            scheduleAdap.updateData(schedule)
        })
        viewModel.isLoading.observe(viewLifecycleOwner, Observer<Boolean> {
            if(it!=null){
                binding.rlBaseSchedule.visibility=View.INVISIBLE
            }
        })
    }

    override fun onConferenceClicked(conference: Conference, pos: Int) {
        val bundle= bundleOf("conference" to conference)
        findNavController().navigate(R.id.schedulerDetailsFragmentDialog,bundle)
    }
//    fun observeViewModel(){
//        viewModel.listSchedule.observe(viewLifecycleOwner, Observer<List<Conference>>{ schedule ->
//            scheduleAdap.updateData(schedule)
//        })
//    }
}