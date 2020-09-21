package app.equipe41.projetoong.Fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import app.equipe41.projetoong.Adapter.VoluntaryAdapter
import app.equipe41.projetoong.Models.Voluntario
import app.equipe41.projetoong.R
import app.equipe41.projetoong.Retrofit.RetrofitClient
import app.equipe41.projetoong.Service.VoluntarioService
import app.equipe41.projetoong.Util.Constants
import kotlinx.android.synthetic.main.fragment_voluntary.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class VoluntaryFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }
    private lateinit var adapter: VoluntaryAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_voluntary, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            VoluntaryFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val id = activity!!.intent.getStringExtra(Constants.ID_ONG)
        if (id != null) {
            RetrofitClient.getInstance.create(VoluntarioService::class.java)
                .getVoluntary(id).enqueue(object : Callback<ArrayList<Voluntario>> {
                    override fun onFailure(call: Call<ArrayList<Voluntario>>, t: Throwable) {
                        Log.d("error", "onFalied ${t.message}")
                    }

                    override fun onResponse(call: Call<ArrayList<Voluntario>>, response: Response<ArrayList<Voluntario>>) {
                        Log.d("error", "onFalie ${response.code()}")
                        if (response.isSuccessful) {
                            showData(response.body()!!)
                        }
                    }
                })
        }
    }

    private fun showData(voluntaries: ArrayList<Voluntario>) {
        adapter = VoluntaryAdapter(voluntaries)
        recyclerView_voluntary.layoutManager = LinearLayoutManager(this.context)
        recyclerView_voluntary.adapter = adapter

    }

}