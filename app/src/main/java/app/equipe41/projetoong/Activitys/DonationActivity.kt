package app.equipe41.projetoong.Activitys

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import app.equipe41.projetoong.Models.Donation
import app.equipe41.projetoong.R
import app.equipe41.projetoong.Retrofit.RetrofitClient
import app.equipe41.projetoong.Service.DonationService
import kotlinx.android.synthetic.main.activity_donation.*
import retrofit2.Call
import retrofit2.Response

class DonationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_donation)

        val nome = intent.getStringExtra("nome_ong").toString()
        title_ong.text = "Ong Beneficiada: ${nome}"
    }

    fun donationOng (v: View) {

        val id = intent.getStringExtra("id").toString()
        val nome = nome_doador.text.toString()
        val email = email_doador.text.toString()
        val cpf = cpf_doador.text.toString()
        val valor = valor_doacao.text.toString()
        val donation = Donation("",nome,email,cpf,valor)

        RetrofitClient.getInstance.create(DonationService::class.java).postDonation(id,donation)
            .enqueue(object : retrofit2.Callback<Donation> {

                override fun onFailure(call: Call<Donation>, t: Throwable) {
                    Log.d("error", "onFailure: ${t.message}")
                }

                override fun onResponse(call: Call<Donation>, response: Response<Donation>) {
                    if (response.isSuccessful) {
                        Log.d("sucesso", "onResponse: ${response.code()}")

                        val toast = Toast.makeText(applicationContext, "Doaão feita com muita alegria!", Toast.LENGTH_SHORT)
                        toast.show()

                        startActivity(Intent(baseContext, MainActivity::class.java))

                    }
                }
            })
    }


}