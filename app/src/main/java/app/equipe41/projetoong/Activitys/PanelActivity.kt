package app.equipe41.projetoong.Activitys

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment
import app.equipe41.projetoong.Fragment.DonationFragment
import app.equipe41.projetoong.R
import app.equipe41.projetoong.SharedPreference.MyPreference
import app.equipe41.projetoong.Util.Constants
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_panel.*

class PanelActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_panel)
        supportActionBar?.title = "Painel da Ong."
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val donationFragment = DonationFragment()
        makeCurrentFragment(donationFragment)

        bottom_navigation_panel.setOnNavigationItemSelectedListener {

            when (it.itemId) {
                R.id.ic_donation_panel -> makeCurrentFragment(donationFragment)

            }
            true
        }
    }

    private fun makeCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl_wrapper_panel, fragment)
            commit()

        }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu)
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        menu?.setGroupVisible(R.id.group_logado, true)
        return super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_sair -> logout()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun logout() {
        MyPreference.deleteToken(baseContext)
        startActivity(Intent(this.baseContext, MainActivity::class.java))
    }


}