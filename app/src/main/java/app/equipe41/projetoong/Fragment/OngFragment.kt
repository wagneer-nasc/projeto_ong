package app.equipe41.projetoong.Fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Button
import androidx.appcompat.view.menu.MenuView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import app.equipe41.projetoong.Adapter.OngAdapter
import app.equipe41.projetoong.Models.Ong
import app.equipe41.projetoong.Models.User
import app.equipe41.projetoong.R
import app.equipe41.projetoong.Retrofit.RetrofitClient
import app.equipe41.projetoong.Service.OngService
import app.equipe41.projetoong.Service.UserService
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.adapter_ong.*
import kotlinx.android.synthetic.main.adapter_ong.view.*
import kotlinx.android.synthetic.main.fragment_ong.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [OngFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class OngFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            OngFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    private lateinit var adapter : OngAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }

    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {
     return inflater.inflate(R.layout.fragment_ong, container, false)

    }
    //Um Metodo onActivityCreated sera de extrema importancia para escrever coigos em um fragment
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        RetrofitClient.getInstance.create(OngService::class.java)
        .getOng().enqueue(object : Callback<ArrayList<Ong>> {
            override fun onFailure(call: Call<ArrayList<Ong>>, t: Throwable) {


            }
            override fun onResponse(call: Call<ArrayList<Ong>>, response: Response<ArrayList<Ong>>) {
                if (response.isSuccessful) {
                    showData(response.body()!!)
                }
            }
        })

    }

    private fun showData(ongs: ArrayList<Ong>) {
        adapter = OngAdapter(ongs)
        recyclerView.layoutManager = LinearLayoutManager(this.context)
        recyclerView.adapter = adapter

    }

}
