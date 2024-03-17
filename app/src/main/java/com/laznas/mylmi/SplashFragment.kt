package com.laznas.mylmi

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.navigation.fragment.findNavController

class SplashFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        // Memuat tata letak untuk fragmen ini
        val view = inflater.inflate(R.layout.fragment_splash, container, false)

        // Handler untuk menunda navigasi ke fragmen selanjutnya (onboardingFragment)
        Handler(Looper.getMainLooper()).postDelayed({

            // Menentukan apakah onboarding sudah selesai atau belum
            if (onBoardingIsFinished()){
                // Jika onboarding sudah selesai, navigasikan ke homeFragment
                findNavController().navigate(R.id.navigate_splashFragment_to_homeFragment)
            } else {
                // Jika onboarding belum selesai, navigasikan ke onBoarding1Fragment
                findNavController().navigate(R.id.action_splashFragment_to_onBoarding1Fragment)
            }
        }, 3000)

        // Mengembalikan tata letak yang dimuat
        return view
    }

    // Fungsi untuk mengecek apakah onboarding sudah selesai atau belum
    private fun onBoardingIsFinished(): Boolean{
        val sharedPreferences = requireActivity().getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
        // Mengembalikan nilai boolean yang menunjukkan apakah onboarding sudah selesai atau belum
        return sharedPreferences.getBoolean("finshed", false)
    }
}
