package com.example.cocci
import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.cocci.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    var tough: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)



        setContentView(binding.root)
        Toast.makeText(this@MainActivity, "Press the difficult\nIf not pressed start in medium", Toast.LENGTH_LONG).show()



        val mediaPlayer = MediaPlayer.create(this, R.raw.sonic_audio)
        mediaPlayer.start()


        val Sonic_touch = findViewById(R.id.Sanic) as ImageView
        Sonic_touch.setOnClickListener {
            Toast.makeText(this@MainActivity, "Click on START or you will PERISH.", Toast.LENGTH_SHORT).show()
        }
        val btn_click_me = findViewById(R.id.Start) as Button
        btn_click_me.setOnClickListener {
            val intent= Intent(this,Game::class.java)

            startActivity(intent)
        }


    }
   /* inner class Holder(binding: ActivityMainBinding){
        private val difficult = listOf(
                binding.ez,
                binding.medium,
                binding.hard

        )



        init {
            for (btn in difficult) {
                btn.setOnClickListener(SetDifficult())
            }


    }



        }
    inner class SetDifficult: View.OnClickListener {
        override fun onClick(view: View?) {

            view as Button
            if(view.id == R.id.ez) {
                //numero vite = 10
                toughness = 20
            }
        }
    }*/
}

