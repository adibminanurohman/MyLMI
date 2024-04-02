package com.laznas.mylmi.screens

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.laznas.mylmi.adapter.MagazinesAdapter
import com.laznas.mylmi.adapter.MagazinesTerbaruAdapter
import com.laznas.mylmi.databinding.FragmentEMajalahBinding
import com.laznas.mylmi.databinding.FragmentSemuaScreenBinding
import com.laznas.mylmi.databinding.FragmentTerbaruScreenBinding
import com.laznas.mylmi.model.ResponseMagazinesItem
import com.laznas.mylmi.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private var _binding: FragmentTerbaruScreenBinding? = null
private val binding get() = _binding!!

class TerbaruScreen : Fragment() {
    private lateinit var adapter: MagazinesTerbaruAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTerbaruScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = MagazinesTerbaruAdapter(requireContext(), arrayListOf())

        binding.rvMajalahTerbaru.adapter = adapter
        binding.rvMajalahTerbaru.setHasFixedSize(true)

        remoteGetMagazines()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun remoteGetMagazines() {
        ApiClient.apiService.getMagazines().enqueue(object : Callback<ArrayList<ResponseMagazinesItem>> {
            override fun onResponse(
                call: Call<ArrayList<ResponseMagazinesItem>>,
                response: Response<ArrayList<ResponseMagazinesItem>>
            ) {
                if (response.isSuccessful) {
                    val data = response.body()
                    if (data != null) {
                        val filteredData = filterMagazinesByDate(data)
                        setDataToAdapter(filteredData)
                    }
                }
            }

            override fun onFailure(call: Call<ArrayList<ResponseMagazinesItem>>, t: Throwable) {
                Log.d("error", t.stackTraceToString())
            }
        })
    }


    private fun setDataToAdapter(data: ArrayList<ResponseMagazinesItem>) {
        adapter.setData(data)
    }

    private fun filterMagazinesByDate(data: ArrayList<ResponseMagazinesItem>): ArrayList<ResponseMagazinesItem> {
        val filteredList = ArrayList<ResponseMagazinesItem>()

        // Batas awal dan akhir rentang tanggal yang ingin ditampilkan
        val startDate = "2023-01-01"
        val endDate = "2024-12-01"

        // Loop melalui setiap item majalah
        for (item in data) {
            val releaseDate = item.release ?: ""
            if (releaseDate in startDate..endDate) {
                filteredList.add(item)
            }
        }

        return filteredList
    }

}