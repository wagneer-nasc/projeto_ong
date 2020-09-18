package app.equipe41.projetoong.Fragment

import android.os.Bundle
import android.util.Log.d
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import app.equipe41.projetoong.Adapter.DonationAdapter
import app.equipe41.projetoong.Adapter.OngAdapter
import app.equipe41.projetoong.Models.Donation
import app.equipe41.projetoong.Models.Ong
import app.equipe41.projetoong.R
import app.equipe41.projetoong.Retrofit.RetrofitClient
import app.equipe41.projetoong.Service.DonationService
import app.equipe41.projetoong.Service.OngService
import kotlinx.android.synthetic.main.fragment_donation.*
import kotlinx.android.synthetic.main.fragment_ong.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class DonationFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }
    private lateinit var adapter : DonationAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_donation, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DonationFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
    //Um Metodo onActivityCreated sera de extrema importancia para escrever coigos em um fragment
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val id = "5f5ee50e79b1422878c08dfd"
        RetrofitClient.getInstance.create(DonationService::class.java)
            .getDonation(id).enqueue(object : Callback<ArrayList<Donation>> {
                override fun onFailure(call: Call<ArrayList<Donation>>, t: Throwable) {
                    d("error", "onFalied ${t.message}")
                }
                override fun onResponse(call: Call<ArrayList<Donation>>, response: Response<ArrayList<Donation>>) {
                    d("error", "onFalie ${response.code()}")
                    if (response.isSuccessful) {
                        showData(response.body()!!)
                    }
                }
            })
    }

    private fun showData(donations: ArrayList<Donation>) {
        adapter = DonationAdapter(donations)
        recyclerView_donation.layoutManager = LinearLayoutManager(this.context)
        recyclerView_donation.adapter = adapter

    }


}