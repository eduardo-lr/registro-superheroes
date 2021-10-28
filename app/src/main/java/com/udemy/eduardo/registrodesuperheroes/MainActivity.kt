package com.udemy.eduardo.registrodesuperheroes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.udemy.eduardo.registrodesuperheroes.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    companion object {
        const val SUPERHEROE_NAME_KEY = "superheroe_name"
        const val ALTER_EGO_KEY = "alter_ego"
        const val BIO_KEY = "bio"
        const val POWER_KEY = "power"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.saveButton.setOnClickListener {
            val superHeroeName = binding.heroNameEdit.text.toString()
            val alterEgo = binding.alterEgoEdit.text.toString()
            val bio = binding.bioEdit.text.toString()
            val power = binding.rattingBar.rating
            openDetailActivity(superHeroeName, alterEgo, bio, power)
        }
    }

    private fun openDetailActivity(superHeroeName: String, alterEgo: String,
                                    bio: String, power: Float) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(SUPERHEROE_NAME_KEY, superHeroeName)
        intent.putExtra(ALTER_EGO_KEY, alterEgo)
        intent.putExtra(BIO_KEY, bio)
        intent.putExtra(POWER_KEY, power)
        startActivity(intent)
    }
}