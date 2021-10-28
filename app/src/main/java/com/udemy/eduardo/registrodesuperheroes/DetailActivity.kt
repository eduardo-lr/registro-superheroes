package com.udemy.eduardo.registrodesuperheroes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.udemy.eduardo.registrodesuperheroes.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent.extras!!
        val superHeroeName = bundle.getString(MainActivity.SUPERHEROE_NAME_KEY) ?: ""
        val alterEgo = bundle.getString(MainActivity.ALTER_EGO_KEY) ?: ""
        val bio = bundle.getString(MainActivity.BIO_KEY) ?: ""
        val power = bundle.getFloat(MainActivity.POWER_KEY)

        binding.heroName.text = superHeroeName
        binding.alterEgo.text = alterEgo
        binding.bio.text = bio
        binding.ratingBar.rating = power
    }
}