package com.udemy.eduardo.registrodesuperheroes

import android.graphics.Bitmap
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
        var bitmap = bundle.getParcelable<Bitmap>(MainActivity.BITMAP_KEY)!!

        binding.superheroe = superheroe
        binding.superHeroImage.setImageBitmap(bitmap)
    }
}