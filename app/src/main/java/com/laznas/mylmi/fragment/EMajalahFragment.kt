package com.laznas.mylmi.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.laznas.mylmi.adapter.MagazinesAdapter
import com.laznas.mylmi.databinding.FragmentEMajalahBinding
import com.laznas.mylmi.model.ResponseMagazinesItem
import com.laznas.mylmi.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

private var _binding: FragmentEMajalahBinding? = null
private val binding get() = _binding!!

class EMajalahFragment : Fragment() {
    private lateinit var adapter: MagazinesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEMajalahBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = MagazinesAdapter(requireContext(), arrayListOf())

        // Set layout manager untuk recyclerview menjadi horizontal
        val layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvMajalah.layoutManager = layoutManager

        binding.rvMajalah.adapter = adapter
        binding.rvMajalah.setHasFixedSize(true)

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

    private fun filterMagazinesByDate(data: ArrayList<ResponseMagazinesItem>): ArrayList<ResponseMagazinesItem> {
        val filteredList = ArrayList<ResponseMagazinesItem>()
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val calendarStart = Calendar.getInstance()
        calendarStart.time = sdf.parse("2024-01-01") ?: Date()
        val calendarEnd = Calendar.getInstance()
        calendarEnd.time = sdf.parse("2024-12-01") ?: Date()

        for (item in data) {
            val calendarItem = Calendar.getInstance()
            calendarItem.time = item.release?.let { sdf.parse(it) } ?: Date()
            if (calendarItem in calendarStart..calendarEnd) {
                filteredList.add(item)
            }
        }

        return filteredList
    }

    private fun setDataToAdapter(data: ArrayList<ResponseMagazinesItem>) {
        adapter.setData(data)
    }
}
