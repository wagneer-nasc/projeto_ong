package app.equipe41.projetoong.Activitys

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import app.equipe41.projetoong.Models.User
import app.equipe41.projetoong.R
import app.equipe41.projetoong.Retrofit.RetrofitClient
import app.equipe41.projetoong.Service.UserService

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val user = User("wagner", "wagner@live.com")
        //user.nome = "wagner"

        var api = RetrofitClient.getInstance.create(UserService::class.java)





    }
}