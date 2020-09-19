package app.equipe41.projetoong.Activitys

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import app.equipe41.projetoong.Models.Auth
import app.equipe41.projetoong.R
import app.equipe41.projetoong.Retrofit.RetrofitClient
import app.equipe41.projetoong.Service.AuthService
import app.equipe41.projetoong.SharedPreference.MyPreference
import app.equipe41.projetoong.Util.Constants.ID_ONG
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
        val auth = Auth()
        auth.email = email_login.text.toString()
        auth.senha = password_login.text.toString()

        if (validateForm(auth)) authentic(auth)
    }

    private fun validateForm(auth: Auth): Boolean {
        if (auth.email.isEmpty() || auth.senha.isEmpty()) {
            Toast.makeText(
                applicationContext,
                "Todos os campos são obrigatórios!",
                Toast.LENGTH_SHORT
            ).show()
            return false
        }
        return true
    }

    private fun authentic(auth: Auth) {
        RetrofitClient.getInstance.create(AuthService::class.java).authOng(auth)
            .enqueue(object : Callback<Auth> {

                override fun onFailure(call: Call<Auth>, t: Throwable) {
                    Toast.makeText(baseContext, "error, ${t.message}", Toast.LENGTH_SHORT).show()
                    Log.d("sucesso", "onResponse:${t.message}")
                }

                override fun onResponse(call: Call<Auth>, response: Response<Auth>) {
                    if (response.isSuccessful) {
                        Log.d("sucesso", "onResponse:${response.body()!!}")
                        val authentic: Auth = response.body()!!
                        MyPreference.setToken(baseContext, authentic)
                        openPanelActivity()

                    } else {
                        Toast.makeText(baseContext, "Email ou senha invalido!", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            })
    }

    fun openRegisterOng(v: View) {
        startActivity(Intent(this.baseContext, RegistreOngActivity::class.java))
    }

    private fun openPanelActivity() {
        val id = MyPreference.getIdOngLegate(applicationContext)
        startActivity(Intent(baseContext, PanelActivity::class.java).apply {
            putExtra(ID_ONG, id)
        })
    }


}