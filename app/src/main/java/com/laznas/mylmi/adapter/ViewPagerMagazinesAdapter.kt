package com.laznas.mylmi.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.laznas.mylmi.screens.SemuaScreen
import com.laznas.mylmi.screens.TerbaruScreen

class ViewPagerMagazinesAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int = 2 // Jumlah tab

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> TerbaruScreen() // Fragment untuk tab "Terbaru"
            1 -> SemuaScreen() // Fragment untuk tab "Semua"
            else -> throw IllegalArgumentException("Invalid position: $position")
        }
    }
}