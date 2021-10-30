package com.udemy.eduardo.registrodesuperheroes

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import androidx.core.graphics.drawable.toBitmap
import com.udemy.eduardo.registrodesuperheroes.databinding.ActivityMainBinding
import java.io.File

class MainActivity : AppCompatActivity() {

    private lateinit var heroeImage : ImageView
    private var heroeBitmap : Bitmap? = null
    private var picturePath = ""

    private val getContent = registerForActivityResult(ActivityResultContracts.TakePicture()) {
        succes ->
            if (succes && picturePath.isNotEmpty()) {
                heroeBitmap = BitmapFactory.decodeFile(picturePath)
                heroeImage.setImageBitmap(heroeBitmap!!)
            }
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
        val file = createImageFile()
        var uri = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            FileProvider.getUriForFile(this, "$packageName.provider", file)
        } else {
            Uri.fromFile(file)
        }
        getContent.launch(uri)
    }

    private fun createImageFile(): File {
        val file_name = "superheroe_image"
        val fileDirectory = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        val file = File.createTempFile(file_name, ".jpg", fileDirectory)
        picturePath = file.absolutePath
        return file
    }

    private fun openDetailActivity(heroe: Superheroe) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(SUPERHEROE_KEY, heroe)
        intent.putExtra(BITMAP_KEY, picturePath)
        startActivity(intent)
    }
}
