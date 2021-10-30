package com.udemy.eduardo.registrodesuperheroes

import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.graphics.drawable.toBitmap
import com.udemy.eduardo.registrodesuperheroes.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var heroeImage : ImageView
    private var heroeBitmap : Bitmap? = null
    private val getContent = registerForActivityResult(ActivityResultContracts.TakePicturePreview()) {
        bitmap ->
        heroeBitmap = bitmap
        heroeImage.setImageBitmap(heroeBitmap!!)
    }

    companion object {
        const val SUPERHEROE_KEY = "superheroe"
        const val BITMAP_KEY = "bitmap"
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

        heroeImage = binding.heroImg

        heroeImage.setOnClickListener {
            openCamera()
        }
    }

    private fun openCamera() {
        getContent.launch(null)
    }

    private fun openDetailActivity(heroe: Superheroe) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(SUPERHEROE_KEY, heroe)
        intent.putExtra(BITMAP_KEY, heroeImage.drawable.toBitmap())
        startActivity(intent)
    }
}
