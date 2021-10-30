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
        val superheroe = bundle.getParcelable<Superheroe>(MainActivity.SUPERHEROE_KEY)!!

        binding.heroName.text = superheroe.name
        binding.alterEgo.text = superheroe.alter_ego
        binding.bio.text = superheroe.bio
        binding.ratingBar.rating = superheroe.power
    }
}