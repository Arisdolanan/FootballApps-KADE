package com.hokagelab.www.footballmatchdua

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.hokagelab.www.footballmatchdua.mainMenu.teams.logoTeam.logo.TeamsFragment
import com.hokagelab.www.footballmatchdua.mainMenu.favorit.favFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId){
                R.id.matches ->{
                    matchFragment(savedInstanceState)
                }
                R.id.teams ->{
                    teamsFragment(savedInstanceState)
//                    val moveIntent = Intent(this@MainActivity, TeamActivity::class.java)
//                    startActivity(moveIntent)
                }
                R.id.favorit ->{
                    favoritFragment(savedInstanceState)
                }
            }
            true
        }
        navigation.selectedItemId = R.id.matches

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun matchFragment(savedInstanceState: Bundle?){
        if (savedInstanceState == null){
            supportFragmentManager
                .beginTransaction()
                .replace(
                    R.id.main_container,
                    com.hokagelab.www.footballmatchdua.mainMenu.favorit.favMatch.matchFragment(), com.hokagelab.www.footballmatchdua.mainMenu.favorit.favMatch.matchFragment::class.java.simpleName)
                .commit()
        }
    }

    private fun teamsFragment(savedInstanceState: Bundle?){
        if (savedInstanceState == null){
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_container,
                    TeamsFragment(), TeamsFragment::class.java.simpleName)
                .commit()
        }
    }

    private fun favoritFragment(savedInstanceState: Bundle?){
        if (savedInstanceState == null){
            supportFragmentManager
                .beginTransaction()
                .replace(
                    R.id.main_container,
                    favFragment(), favFragment::class.java.simpleName)
                .commit()
        }
    }

}