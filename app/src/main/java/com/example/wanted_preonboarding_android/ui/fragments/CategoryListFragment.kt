package com.example.wanted_preonboarding_android.ui.fragments

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.wanted_preonboarding_android.R
import com.example.wanted_preonboarding_android.communicator.Communicator

class CategoryListFragment : Fragment(R.layout.fragment_category_list){

    val TAG = "CategoryListFragment"
    lateinit var btnBusiness: Button
    lateinit var btnEntertainment: Button
    lateinit var btnGeneral: Button
    lateinit var btnHealth: Button
    lateinit var btnScience: Button
    lateinit var btnSports: Button
    lateinit var btnTechnology: Button
    private lateinit var comm: Communicator

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnBusiness = view.findViewById(R.id.btnBusiness)
        btnEntertainment = view.findViewById(R.id.btnEntertainment)
        btnGeneral = view.findViewById(R.id.btnGeneral)
        btnHealth = view.findViewById(R.id.btnHealth)
        btnScience = view.findViewById(R.id.btnScience)
        btnSports = view.findViewById(R.id.btnSports)
        btnTechnology = view.findViewById(R.id.btnTechnology)
        comm = requireActivity() as Communicator

        btnBusiness.setOnClickListener {
            comm.passDataCom(btnBusiness.text.toString())
        }

        btnEntertainment.setOnClickListener {
            comm.passDataCom(btnEntertainment.text.toString())
        }

        btnGeneral.setOnClickListener {
            comm.passDataCom(btnGeneral.text.toString())
        }

        btnHealth.setOnClickListener {
            comm.passDataCom(btnHealth.text.toString())
        }

        btnScience.setOnClickListener {
            comm.passDataCom(btnScience.text.toString())
        }

        btnSports.setOnClickListener {
            comm.passDataCom(btnSports.text.toString())
        }

        btnTechnology.setOnClickListener {
            comm.passDataCom(btnTechnology.text.toString())
        }
    }
}