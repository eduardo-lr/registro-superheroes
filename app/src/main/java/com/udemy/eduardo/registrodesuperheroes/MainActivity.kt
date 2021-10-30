package com.udemy.eduardo.registrodesuperheroes

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.ImageView
import androidx.core.graphics.drawable.toBitmap
import com.udemy.eduardo.registrodesuperheroes.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var heroeImage : ImageView

    companion object {
        const val SUPERHEROE_KEY = "superheroe"
        const val BITMAP_KEY = "bitmap"
        const val CREATE_CAMERA_REQUEST_CODE = 1000
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
            createCamera()
        }
    }

    private fun createCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, CREATE_CAMERA_REQUEST_CODE)
    }

    private fun openDetailActivity(heroe: Superheroe) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(SUPERHEROE_KEY, heroe)
        intent.putExtra(BITMAP_KEY, heroeImage.drawable.toBitmap())
        startActivity(intent)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == CREATE_CAMERA_REQUEST_CODE) {
            val extras = data?.extras
            val heroeBitMap = extras?.getParcelable<Bitmap>("data")
            heroeImage.setImageBitmap(heroeBitMap)
        }
    }
}
