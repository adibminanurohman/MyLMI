package com.laznas.mylmi.screens

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.laznas.mylmi.adapter.MagazinesAdapter
import com.laznas.mylmi.databinding.FragmentEMajalahBinding
import com.laznas.mylmi.databinding.FragmentSemuaScreenBinding
import com.laznas.mylmi.model.ResponseMagazinesItem
import com.laznas.mylmi.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private var _binding: FragmentSemuaScreenBinding? = null
private val binding get() = _binding!!

class SemuaScreen : Fragment() {
    private lateinit var adapter: MagazinesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSemuaScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = MagazinesAdapter(requireContext(), arrayListOf())

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
                    data?.let { setDataToAdapter(it) }
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
}