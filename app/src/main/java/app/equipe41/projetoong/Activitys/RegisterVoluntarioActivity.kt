package app.equipe41.projetoong.Activitys

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import app.equipe41.projetoong.Models.Voluntario
import app.equipe41.projetoong.R
import app.equipe41.projetoong.Retrofit.RetrofitClient
import app.equipe41.projetoong.Service.VoluntarioService
import kotlinx.android.synthetic.main.activity_register_voluntario.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterVoluntarioActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_voluntario)

    }

    fun registerVoluntario(v: View) {
        val _id = ""
        val nome = nomeVoluntario.text.toString()
        val telefone = telefonevoluntario.text.toString()
        val cpf = cpfVoluntario.text.toString()
        val email = emailVoluntario.text.toString()
        val endereco = enderecoVoluntario.text.toString()
        val numero = numeroVoluntario.text.toString().toInt()
        val id_ong = intent.getStringExtra("id").toString()

        val voluntarios = Voluntario(_id, nome, telefone, cpf, email, endereco, numero, id_ong )
        RetrofitClient.getInstance.create(VoluntarioService::class.java).postVoluntario(voluntarios)
            .enqueue(object : Callback<Voluntario> {
                override fun onResponse(call: Call<Voluntario>, response: Response<Voluntario>) {
                    if (response.isSuccessful) {
                        Log.d("sucesso", "onResponse:${response.body()}")
                    }
                }

                override fun onFailure(call: Call<Voluntario>, t: Throwable) {
                    Log.d("error", "onFailure:${t.message}")
                }
            })
    }
}
