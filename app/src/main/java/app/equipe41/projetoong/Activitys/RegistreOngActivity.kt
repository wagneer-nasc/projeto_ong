package app.equipe41.projetoong.Activitys

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import app.equipe41.projetoong.Models.Ong
import app.equipe41.projetoong.R
import app.equipe41.projetoong.Retrofit.RetrofitClient
import app.equipe41.projetoong.Service.OngService
import kotlinx.android.synthetic.main.activity_registre_ong.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegistreOngActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registre_ong)

        supportActionBar?.title = "Cadastre sua Ong."
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    fun registreOng(v: View) {
        val id = ""
        val nome = title_ong.text.toString()
        val telephone = telefoneOng.text.toString()
        val cpnj = cnpjOng.text.toString()
        val email = emailOng.text.toString()
        val password = senhaOng.text.toString()
        val address = enderecoOng.text.toString()
        val numberAddress = if (numeroOng.text.toString() == "") 0 else numeroOng.text.toString().toInt()
        val description = descricaoOng.text.toString()

        val ong = Ong(id, nome, telephone, cpnj, email, description, password, address, numberAddress)
        if(validateForm(ong)) {
            saveOng(ong)
        }else {
            Toast.makeText(applicationContext, "Todos os campos são obrigatórios!", Toast.LENGTH_SHORT).show()
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
        RetrofitClient.getInstance.create(OngService::class.java).postOng(ong)
            .enqueue(object : Callback<Ong> {

                override fun onFailure(call: Call<Ong>, t: Throwable) {
                    Log.d("error", "onFailure: ${t.message}")
                }
                override fun onResponse(call: Call<Ong>, response: Response<Ong>) {
                    if (response.isSuccessful) {
                        Log.d("sucesso", "onResponse: ${response.body()}")

                        Toast.makeText(applicationContext, "Ong Criada com Sucesso!", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(baseContext, MainActivity::class.java))
                    }else {
                        Toast.makeText(applicationContext, "Falha ao criar Ong: ${response.errorBody()}", Toast.LENGTH_SHORT).show()
                    }
            }
        })
    }
    fun openLogin(v: View) {
        startActivity(Intent(this.baseContext, LoginActivity::class.java))
    }



}


