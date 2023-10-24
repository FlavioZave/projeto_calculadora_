package com.example.calculadora

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.google.firebase.FirebaseApp
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drawer)

        val navigation: NavigationView = findViewById(R.id.navigation_view)
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val toolbar: Toolbar = findViewById(R.id.toolbar)

        setSupportActionBar(toolbar)
        val botao_toggle = ActionBarDrawerToggle (
            this, findViewById(R.id.drawer_layout), toolbar,
            R.string.abrir_drawer, R.string.fechar_drawer
        )
        drawerLayout.addDrawerListener(botao_toggle)
        botao_toggle.syncState()


        navigation.setNavigationItemSelectedListener { item ->
            when(item.itemId){
                R.id.fragmento_soma -> supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_container_view, SomaFragment())
                    .commit()

                R.id.fragmento_subtracao -> supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_container_view, SubtracaoFragment())
                    .commit()

                R.id.fragmento_divisao -> supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_container_view, DivisaoFragment())
                    .commit()

                R.id.fragmento_multiplicacao -> supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_container_view, MultiplicacaoFragment())
                    .commit()

                R.id.fragmento_potenciacao -> supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_container_view, PotenciacaoFragment())
                    .commit
                ()
            }
            drawerLayout.closeDrawer(GravityCompat.START)
            true
        }

    }

    /* - Function to introduce options from main_menu - */
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    /* - Options that will working on - */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.item_logout -> mostrarActivityLogout()
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun mostrarActivityLogout(): Boolean {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
        return true;
    }

    /* - To do none reaction on press back cell button - */
    private var doubleBack = false
    override fun onBackPressed() {

        if(doubleBack) {
            super.onBackPressed()
        }
        this.doubleBack = true
        Toast.makeText(this, "Pressione 2 vezes para sair.", Toast.LENGTH_SHORT).show()
        Handler(Looper.getMainLooper()).postDelayed(Runnable { doubleBack = false }, 2000)
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onPause() {
        super.onPause()
    }
}