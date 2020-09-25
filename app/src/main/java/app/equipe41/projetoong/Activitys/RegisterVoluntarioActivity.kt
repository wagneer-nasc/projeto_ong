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
        val numberAddress = if (numeroVoluntario.text.toString() == "") 0 else numeroVoluntario.text.toString().toInt()
        val idOng = intent.getStringExtra("id").toString()
        val voluntaries = Voluntario("",nome,telephone,cpf,email,endeared,numberAddress,Date())

        if(validateForm(voluntaries)) {
            savevoluntario(voluntaries, idOng)
        }else {
            Toast.makeText(applicationContext, "Todos os campos são obrigatórios!", Toast.LENGTH_SHORT).show()
        }
    }
    private fun validateForm(voluntaries: Voluntario): Boolean {
        var valido = true
        if(voluntaries.numero == 0) {
            valido = false
        }
        if(voluntaries.nome_voluntario.isEmpty() || voluntaries.cpf_voluntario.isEmpty() || voluntaries.email.isEmpty() ||
            voluntaries.telefone_voluntario.isEmpty() || voluntaries.endereco.isEmpty()) {
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
