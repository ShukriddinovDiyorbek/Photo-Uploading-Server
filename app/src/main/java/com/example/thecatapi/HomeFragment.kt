package com.example.thecatapi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton

class HomeFragment : Fragment() {
    lateinit var root: View
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        root = inflater.inflate(R.layout.fragment_home, container, false)

//        val floatingActionButton = root.findViewById<FloatingActionButton>(R.id.floating_action_button)
//        floatingActionButton.setOnClickListener {
//            findNavController().navigate(R.id.uploadFragment)
//        }

        return root
    }
}