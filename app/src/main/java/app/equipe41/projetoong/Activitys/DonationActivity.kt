package app.equipe41.projetoong.Activitys

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import app.equipe41.projetoong.Models.Donation
import app.equipe41.projetoong.Models.Ong
import app.equipe41.projetoong.R
import app.equipe41.projetoong.Retrofit.RetrofitClient
import app.equipe41.projetoong.Service.DonationService
import kotlinx.android.synthetic.main.activity_donation.*
import retrofit2.Call
import retrofit2.Response
import java.util.*

class DonationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_donation)

        supportActionBar?.title = "Fazendo uma boa ação."

        val nome = intent.getStringExtra("nome_ong").toString()
        title_ong.text = "Ong Beneficiada: ${nome}"
       val locale = Locale("pt","BR")
        valor_doacao.locale = locale

    }

    fun donationOng(v: View) {
        val nome = nome_doador.text.toString()
        val email = email_doador.text.toString()
        val cpf = cpf_doador.text.toString()
        val valor = valor_doacao.rawValue.toString()
        val donation = Donation("", nome, email, cpf, valor, Date())

        if (validateForm(donation)) {
            saveDonation(donation)
        } else {
            Toast.makeText(
                applicationContext,
                "Todos os campos são obrigatórios!",
                Toast.LENGTH_SHORT
            ).show()
        }

    }

    private fun saveDonation(donation: Donation) {
        val id = intent.getStringExtra("id").toString()
        RetrofitClient.getInstance.create(DonationService::class.java).postDonation(id, donation)
            .enqueue(object : retrofit2.Callback<Donation> {

                override fun onFailure(call: Call<Donation>, t: Throwable) {
                    Log.d("error", "onFailure: ${t.message}")
                }

                override fun onResponse(call: Call<Donation>, response: Response<Donation>) {
                    if (response.isSuccessful) {
                        Toast.makeText(
                            applicationContext,
                            "Doaão feita com muita alegria!",
                            Toast.LENGTH_SHORT
                        ).show()
                        startActivity(Intent(baseContext, MainActivity::class.java))
                    } else {
                        Toast.makeText(
                            applicationContext,
                            "Error ${response.errorBody()}!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            })
    }

    private fun validateForm(donation: Donation): Boolean {
        var valido = true
        if (donation.nome_doador.isEmpty() || donation.cpf.isEmpty() || donation.email_doador.isEmpty() ||
            donation.valor_doacao.isEmpty()
        ) {
            valido = false
        }
        return valido
    }


}