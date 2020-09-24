package app.equipe41.projetoong.Fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import app.equipe41.projetoong.Activitys.MainActivity
import app.equipe41.projetoong.Activitys.PanelActivity
import app.equipe41.projetoong.Models.Ong
import app.equipe41.projetoong.R
import app.equipe41.projetoong.Retrofit.RetrofitClient
import app.equipe41.projetoong.Service.OngService
import app.equipe41.projetoong.Util.Constants
import kotlinx.android.synthetic.main.activity_registre_ong.*
import kotlinx.android.synthetic.main.fragment_alter_ong.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class AlterOngFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_alter_ong, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AlterOngFragment().apply {
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
            loadOng(id)
        }
        button_update_ong.setOnClickListener(View.OnClickListener {
            editOng()
        })

    }

    private fun loadOng(id: String) {
        RetrofitClient.getInstance.create(OngService::class.java)
            .getOneOng(id).enqueue(object : Callback<Ong> {
                override fun onFailure(call: Call<Ong>, t: Throwable) {

                }

                override fun onResponse(call: Call<Ong>, response: Response<Ong>) {
                    if (response.isSuccessful) {
                        showData(response.body()!!)
                    }
                }
            })
    }

    private fun showData(body: Ong?) {
        if (body != null) {
            title_ong_edit.setText(body.nome_ong)
            telefone_ong_edit.setText(body.telefone_ong)
            cnpj_ong_edit.setText(body.cnpj_ong)
            email_ong_edit.setText(body.email)
            senha_ong_edit.setText(body.senha)
            endereco_ong_edit.setText(body.endereco)
            descricao_ong_edit.setText(body.descricao)
//          numero_ong_edit.setText(body.numero)
        }
    }

    private fun editOng() {
        val id = activity!!.intent.getStringExtra(Constants.ID_ONG)
        val nome = title_ong_edit.text.toString()
        val telephone = telefone_ong_edit.text.toString()
        val cpnj = cnpj_ong_edit.text.toString()
        val email = email_ong_edit.text.toString()
        val password = senha_ong_edit.text.toString()
        val address = descricao_ong_edit.text.toString()
        val numberAddress = if (numero_ong_edit.text.toString() == "") 0 else numeroOng.text.toString().toInt()
        val description = descricao_ong_edit.text.toString()

        if(id != null ) {
            val ong = Ong(id, nome, telephone, cpnj, email, description, password, address, numberAddress)
            if(validateForm(ong)) {
                saveOng(ong)
            }else {
                Toast.makeText(this.context, "Todos os campos são obrigatórios!", Toast.LENGTH_SHORT).show()
            }
        }

    }
    private fun validateForm(ong: Ong): Boolean {
        var valido = true
        if(ong.numero == 0) {
            valido = false
        }
        if(ong.cnpj_ong.isEmpty() || ong.nome_ong.isEmpty() || ong.email.isEmpty() ||
            ong.descricao.isEmpty() || ong.senha.isEmpty() || ong.endereco.isEmpty() ||
            ong.telefone_ong.isEmpty()) {
            valido = false
        }
        return valido
    }
    private fun saveOng(ong: Ong) {
        RetrofitClient.getInstance.create(OngService::class.java).postOng(ong) // alterar para o update
            .enqueue(object : Callback<Ong> {

                override fun onFailure(call: Call<Ong>, t: Throwable) {
                    Log.d("error", "onFailure: ${t.message}")
                }
                override fun onResponse(call: Call<Ong>, response: Response<Ong>) {
                    if (response.isSuccessful) {
                        Log.d("sucesso", "onResponse: ${response.body()}")

                        Toast.makeText(context, "Alteração feita com Sucesso!", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(context, PanelActivity::class.java))
                    }else {
                        Toast.makeText(context, "Falha ao alterar Ong: ${response.errorBody()}", Toast.LENGTH_SHORT).show()
                    }
                }
            })
    }

}


