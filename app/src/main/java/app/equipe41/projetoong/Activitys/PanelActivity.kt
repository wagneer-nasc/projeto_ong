package app.equipe41.projetoong.Activitys

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import app.equipe41.projetoong.Fragment.DonationFragment
import app.equipe41.projetoong.R
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

            when(it.itemId) {
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

}