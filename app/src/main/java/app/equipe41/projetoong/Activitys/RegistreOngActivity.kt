package app.equipe41.projetoong.Activitys

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import app.equipe41.projetoong.Models.Ong
import app.equipe41.projetoong.Models.User
import app.equipe41.projetoong.R
import app.equipe41.projetoong.Retrofit.RetrofitClient
import app.equipe41.projetoong.Service.OngService
import app.equipe41.projetoong.Service.UserService
import kotlinx.android.synthetic.main.activity_registre_ong.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegistreOngActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registre_ong)
    }

    fun registreOng(v: View) {
        val _id = ""
        var nome = nomeOng.text.toString()
        var telefone = telefoneOng.text.toString()
        var cpnj = cnpjOng.text.toString()
        var email = emailOng.text.toString()
        var senha = senhaOng.text.toString()
        var endereco = enderecoOng.text.toString()
        var numero = numeroOng.text.toString()
        var descricao = descricaoOng.text.toString()
//FALTA VALIDACOES
            val ong = Ong(_id, nome, telefone, cpnj, email, descricao, senha, endereco, numero.toInt())
                    RetrofitClient.getInstance.create(OngService::class.java).postOng(ong)
                        .enqueue(object : Callback<Ong> {

                            override fun onFailure(call: Call<Ong>, t: Throwable) {
                                Log.d("error", "onFailure: ${t.message}")
                            }

                            override fun onResponse(call: Call<Ong>, response: Response<Ong>) {
                                if (response.isSuccessful) {
                                    Log.d("sucesso", "onResponse: ${response.body()}")

                                    val toast = Toast.makeText(applicationContext, "Ong Criada com Sucesso!", Toast.LENGTH_SHORT)
                                    toast.show()

                                    startActivity(Intent(baseContext, MainActivity::class.java))

                                }
                            }
                        })
}

}


