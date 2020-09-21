package app.equipe41.projetoong.Activitys

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import app.equipe41.projetoong.Models.Voluntario
import app.equipe41.projetoong.R
import app.equipe41.projetoong.Retrofit.RetrofitClient
import app.equipe41.projetoong.Service.VoluntarioService
import kotlinx.android.synthetic.main.activity_register_voluntario.*
import kotlinx.android.synthetic.main.activity_registre_ong.*
import retrofit2.Call
import retrofit2.Response
import java.util.*

class RegisterVoluntarioActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_voluntario)

        supportActionBar?.title = "Fazendo uma boa ação."

        val nome = intent.getStringExtra("nome_ong").toString()
        title_ong_voluntary.text = "Ong Beneficiada: ${nome}"

    }

    fun registerVoluntario(v: View) {
        val nome = nomeVoluntario.text.toString()
        val telephone = telefonevoluntario.text.toString()
        val cpf = cpfVoluntario.text.toString()
        val email = emailVoluntario.text.toString()
        val endeared = enderecoVoluntario.text.toString()
        val numero = if (numeroVoluntario.text.toString() == "") 0 else numeroOng.text.toString().toInt()
        val id_ong = intent.getStringExtra("id").toString()
        val volunrario = Voluntario("",nome,telephone,cpf,email,endeared,numero,Date())

        if(validateForm(volunrario)) {
            savevoluntario(volunrario, id_ong)
        }else {
            Toast.makeText(applicationContext, "Todos os campos são obrigatórios!", Toast.LENGTH_SHORT).show()
        }
    }
    private fun validateForm(voluntario: Voluntario): Boolean {
        var valido = true
        if(voluntario.numero == 0) {
            valido = false
        }
        if(voluntario.nome_voluntario.isEmpty() || voluntario.cpf_voluntario.isEmpty() || voluntario.email.isEmpty() ||
            voluntario.telefone_voluntario.isEmpty() || voluntario.endereco.isEmpty()) {
            valido = false
        }
        return valido
    }
    private fun savevoluntario(voluntario: Voluntario, id_ong: String) {
        RetrofitClient.getInstance.create(VoluntarioService::class.java).postVoluntario(id_ong, voluntario)
            .enqueue(object : retrofit2.Callback<Voluntario> {

                override fun onFailure(call: Call<Voluntario>, t: Throwable) {
                    Log.d("error", "onFailure: ${t.message}")
                }
                override fun onResponse(call: Call<Voluntario>, response: Response<Voluntario>) {
                    if (response.isSuccessful) {
                        Log.d("sucesso", "onResponse: ${response.body()}")

                        Toast.makeText(applicationContext, "Ong Criada com Sucesso!", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(baseContext, MainActivity::class.java))
                    }
                }
            })
    }
    fun openLogin(v: View) {
        startActivity(Intent(this.baseContext,MainActivity::class.java))
    }
}
