package com.laznas.mylmi.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.laznas.mylmi.R
import com.laznas.mylmi.adapter.SliderAdapter
import com.laznas.mylmi.databinding.FragmentBerandaBinding
import com.laznas.mylmi.model.ResponseFundraisingsItem
import com.laznas.mylmi.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private var _binding: FragmentBerandaBinding? = null
private val binding get() = _binding!!

class BerandaFragment : Fragment() {

    private lateinit var adapter: SliderAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBerandaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = SliderAdapter(requireContext(), arrayListOf())

        binding.rvSlider.adapter = adapter
        binding.rvSlider.setHasFixedSize(true)

        // Menambahkan LinearLayoutManager dengan orientasi horizontal ke RecyclerView
        val layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvSlider.layoutManager = layoutManager

        // Set OnClickListener untuk card "Infaq"
        binding.cvInfaq.setOnClickListener {
            // Dapatkan NavController
            val navController = Navigation.findNavController(view)
            // Navigasi ke InfaqFragment
            navController.navigate(R.id.action_berandaFragment_to_infaqFragment)
        }
        // Set OnClickListener untuk card "Infaq"
        binding.cvWakaf.setOnClickListener {
            // Dapatkan NavController
            val navController = Navigation.findNavController(view)
            // Navigasi ke InfaqFragment
            navController.navigate(R.id.action_berandaFragment_to_wakafFragment)
        }
        // Set OnClickListener untuk card "Infaq"
        binding.cvZakat.setOnClickListener {
            // Dapatkan NavController
            val navController = Navigation.findNavController(view)
            // Navigasi ke InfaqFragment
            navController.navigate(R.id.action_berandaFragment_to_zakatFragment)
        }
        // Set OnClickListener untuk card "Infaq"
        binding.cvQurban.setOnClickListener {
            // Dapatkan NavController
            val navController = Navigation.findNavController(view)
            // Navigasi ke InfaqFragment
            navController.navigate(R.id.action_berandaFragment_to_qurbanFragment)
        }

        remoteGetSlider()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun remoteGetSlider() {
        ApiClient.apiService.getFundraisings()
            .enqueue(object : Callback<ArrayList<ResponseFundraisingsItem>> {
                override fun onResponse(
                    call: Call<ArrayList<ResponseFundraisingsItem>>,
                    response: Response<ArrayList<ResponseFundraisingsItem>>
                ) {
                    if (response.isSuccessful) {
                        val data = response.body()
                        data?.let { setDataToAdapter(it) }
                    }
                }

                override fun onFailure(
                    call: Call<ArrayList<ResponseFundraisingsItem>>,
                    t: Throwable
                ) {
                    Log.d("error", t.stackTraceToString())
                }

            })

    }
    private fun setDataToAdapter(data: ArrayList<ResponseFundraisingsItem>){
        adapter.setData(data)
    }


}