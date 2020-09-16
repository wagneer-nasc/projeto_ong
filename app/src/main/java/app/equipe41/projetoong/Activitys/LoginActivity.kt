package app.equipe41.projetoong.Activitys

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import app.equipe41.projetoong.Models.Ong
import app.equipe41.projetoong.R
import app.equipe41.projetoong.Retrofit.RetrofitClient
import app.equipe41.projetoong.Service.OngService
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportActionBar?.title = "Login da Ong."
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }


    fun login(v: View) {
        val email = email_login.text.toString()
        val password = password_login.text.toString()

        if(validateForm(email,password)) {
            callLogin(email,password)
        }
    }

    private fun validateForm(email: String, password: String): Boolean {
        if(email.isEmpty() || password.isEmpty()) {
            Toast.makeText(applicationContext, "Todos os campos são obrigatórios!", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    private fun callLogin(email:String, password: String) {
        RetrofitClient.getInstance.create(OngService::class.java)
            .loginOng(email,password)
            .enqueue(object : Callback<String> {
                override fun onFailure(call: Call<String>, t: Throwable) {
                    Log.d("error", "onFailure: ${t.message}")
                }
                override fun onResponse(call: Call<String>, response: Response<String>) {
                    if (response.isSuccessful) {
                        Log.d("sucesso", "onResponse: ${response.body()}")

                        Toast.makeText(applicationContext, "Ong Criada com Sucesso!", Toast.LENGTH_SHORT).show()
                        //startActivity(Intent(baseContext, MainActivity::class.java))
                    }
                }
            })
    }
     fun openRegisterOng(v: View) {
        startActivity(Intent(this.baseContext,RegistreOngActivity::class.java))
    }
}