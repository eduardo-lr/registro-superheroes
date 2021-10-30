package com.udemy.eduardo.registrodesuperheroes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.udemy.eduardo.registrodesuperheroes.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    companion object {
        const val SUPERHEROE_KEY = "superheroe"
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
            val heroe = Superheroe(superHeroeName, alterEgo, bio, power)
            openDetailActivity(heroe)
        }
    }

    private fun openDetailActivity(heroe: Superheroe) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(SUPERHEROE_KEY, heroe)
        startActivity(intent)
    }
}