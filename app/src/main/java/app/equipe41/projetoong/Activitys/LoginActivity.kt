package app.equipe41.projetoong.Activitys

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import app.equipe41.projetoong.Models.Auth
import app.equipe41.projetoong.Models.Ong
import app.equipe41.projetoong.R
import app.equipe41.projetoong.Retrofit.RetrofitClient
import app.equipe41.projetoong.Service.AuthService
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
        val auth = Auth()
        auth.email= email_login.text.toString()
        auth.senha= password_login.text.toString()
        startActivity(Intent(baseContext, PanelActivity::class.java))

      /*  if(validateForm(auth)) {
            callLogin(auth)
        }*/
    }

    private fun validateForm(auth: Auth): Boolean {
        if(auth.email.isEmpty() || auth.senha.isEmpty()) {
            Toast.makeText(applicationContext, "Todos os campos são obrigatórios!", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    private fun callLogin(auth: Auth) {

        startActivity(Intent(baseContext, PanelActivity::class.java))

    }
     fun openRegisterOng(v: View) {
        startActivity(Intent(this.baseContext,RegistreOngActivity::class.java))
    }
}