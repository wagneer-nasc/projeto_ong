package app.equipe41.projetoong.Activitys

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import app.equipe41.projetoong.Fragment.HomeFragment
import app.equipe41.projetoong.Fragment.InfoFragment
import app.equipe41.projetoong.Fragment.OngFragment
import app.equipe41.projetoong.R
import kotlinx.android.synthetic.main.activity_main.*

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
    }

    private fun makeCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl_wrapper, fragment)
        commit()

        }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu)
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
          val load = false

        if(load) {
            menu?.setGroupVisible(R.id.group_logado, true)

        }else{
            menu?.setGroupVisible(R.id.group_deslogado, true)
        }

        return super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.menu_cadastrar -> startActivity(Intent(this.baseContext,RegistreOngActivity::class.java))
            R.id.menu_login -> startActivity(Intent(this.baseContext,LoginActivity::class.java))
        }
        return super.onOptionsItemSelected(item)
    }


}

