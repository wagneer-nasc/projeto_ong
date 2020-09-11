package app.equipe41.projetoong.Activitys

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import app.equipe41.projetoong.Models.User
import app.equipe41.projetoong.R
import app.equipe41.projetoong.Retrofit.RetrofitClient
import app.equipe41.projetoong.Service.UserService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

      //  val user = User("wagner", "wagner@live.com")
        //user.nome = "wagner"

        var api = RetrofitClient.getInstance.create(UserService::class.java)

        api.getUser().enqueue(object: Callback<List<User>> {
            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                Log.d("felipe", "onFailure: ${t.message}")

            }

            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {

                Log.d("felipe", "onResponse: ${response.body()}")


            }
        })





    }
}