package com.laznas.mylmi.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.laznas.mylmi.R

class LoginFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_login, container, false)

        // Handle button click to navigate to another fragment
        view.findViewById<TextView>(R.id.buat_akun_baru).setOnClickListener {
            // Navigating to another fragment (Replace action ID with your actual action ID)
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }

        return view
    }
}
