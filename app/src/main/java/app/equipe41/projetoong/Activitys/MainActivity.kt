package app.equipe41.projetoong.Activitys

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import app.equipe41.projetoong.Fragment.HomeFragment
import app.equipe41.projetoong.Fragment.InfoFragment
import app.equipe41.projetoong.Fragment.OngFragment
import app.equipe41.projetoong.Models.Auth
import app.equipe41.projetoong.R
import app.equipe41.projetoong.Retrofit.RetrofitClient
import app.equipe41.projetoong.Service.AuthService
import app.equipe41.projetoong.SharedPreference.MyPreference
import app.equipe41.projetoong.Util.Constants
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        validatingToken(baseContext)
         val homeFragment = HomeFragment()
        val infoFragment = InfoFragment()
        val ongFragment = OngFragment()
        makeCurrentFragment(homeFragment)

        bottom_navigation.setOnNavigationItemSelectedListener {

            when (it.itemId) {
                R.id.ic_info -> makeCurrentFragment(infoFragment)
                R.id.ic_home -> makeCurrentFragment(homeFragment)
                R.id.ic_ong -> makeCurrentFragment(ongFragment)
            }
            true
        }

    }

    private fun makeCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl_wrapper, fragment)
            commit()

        }

    var isValidToken = ""
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu)
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        if (isValidToken == "true") menu?.setGroupVisible(R.id.group_logado, true)
        else menu?.setGroupVisible(R.id.group_deslogado, true)
        return super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_cadastrar -> startActivity(
                Intent(
                    this.baseContext,
                    RegistreOngActivity::class.java
                )
            )
            R.id.menu_login -> startActivity(Intent(this.baseContext, LoginActivity::class.java))
            R.id.menu_painel -> openPanelActivity()
            R.id.menu_sair -> logout()

        }
        return super.onOptionsItemSelected(item)
    }

    private fun logout() {
        MyPreference.deleteToken(baseContext)
        startActivity(Intent(this.baseContext, MainActivity::class.java))
    }

    private fun validatingToken(context: Context) {
        val auth = Auth()
        auth.token = MyPreference.getToken(context)

        RetrofitClient.getInstance.create(AuthService::class.java).authValidate(auth)
            .enqueue(object : Callback<Auth> {
                override fun onFailure(call: Call<Auth>, t: Throwable) {
                    Log.d("error", "onResponse:${t.message}")
                }

                override fun onResponse(call: Call<Auth>, response: Response<Auth>) {
                    if (response.isSuccessful) {
                        isValidToken = response.body()!!.token
                    }
                }
            })
    }
    private fun openPanelActivity() {
        val id = MyPreference.getIdOngLegate(applicationContext)
        startActivity(Intent(baseContext, PanelActivity::class.java).apply {
            putExtra(Constants.ID_ONG, id)
        })
    }

}

