package app.equipe41.projetoong.Activitys

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import app.equipe41.projetoong.Fragment.HomeFragment
import app.equipe41.projetoong.Fragment.InfoFragment
import app.equipe41.projetoong.Fragment.OngFragment
import app.equipe41.projetoong.Models.User
import app.equipe41.projetoong.R
import app.equipe41.projetoong.Retrofit.RetrofitClient
import app.equipe41.projetoong.Service.UserService
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.adapter_ong.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val homeFragment = HomeFragment()
        val infoFragment = InfoFragment()
        val ongFragment = OngFragment()

        makeCurrentFragment(homeFragment)
      bottom_navigation.setOnNavigationItemSelectedListener {
          when(it.itemId) {
              R.id.ic_info -> makeCurrentFragment(infoFragment)
              R.id.ic_home -> makeCurrentFragment(homeFragment)
              R.id.ic_ong -> makeCurrentFragment(ongFragment)
          }
          true
      }
      //  val user = User("wagner", "wagner@live.com")
        //user.nome = "wagner"

       var api = RetrofitClient.getInstance.create(UserService::class.java)

        api.getUser().enqueue(object: Callback<List<User>> {
            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                Log.d("felipe", "onFailure: ${t.message}")

            }

            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
            if(response.isSuccessful) {
                showData(response.body()!!)


            }
              //  Log.d("felipe", "onResponse: ${response.body()}")


            }
            private fun showData(users: List<User>) {
               users.forEach{
                    Log.d("wagner", "onResponse: ${it.nome}")
                  //  print("NOMES : ${it.nome}")
                }
            }
        })


    }
    private fun makeCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply { replace(R.id.fl_wrapper, fragment)
        commit()
        }

}