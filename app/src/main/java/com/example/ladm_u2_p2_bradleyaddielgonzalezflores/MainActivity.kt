package com.example.ladm_u2_p2_bradleyaddielgonzalezflores

import android.graphics.Color
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ladm_u2_p2_bradleyaddielgonzalezflores.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.lang.Exception
import kotlin.coroutines.EmptyCoroutineContext

class MainActivity : AppCompatActivity() {

    // Variables
    lateinit var binding: ActivityMainBinding
    val cartas = arrayOf(
        R.drawable.carta1,
        R.drawable.carta2,
        R.drawable.carta3,
        R.drawable.carta4,
        R.drawable.carta5,
        R.drawable.carta6,
        R.drawable.carta7,
        R.drawable.carta8,
        R.drawable.carta9,
        R.drawable.carta10,
        R.drawable.carta11,
        R.drawable.carta12,
        R.drawable.carta13,
        R.drawable.carta14,
        R.drawable.carta15,
        R.drawable.carta16,
        R.drawable.carta17,
        R.drawable.carta18,
        R.drawable.carta19,
        R.drawable.carta20,
        R.drawable.carta21,
        R.drawable.carta22,
        R.drawable.carta23,
        R.drawable.carta24,
        R.drawable.carta25,
        R.drawable.carta26,
        R.drawable.carta27,
        R.drawable.carta28,
        R.drawable.carta29,
        R.drawable.carta30,
        R.drawable.carta31,
        R.drawable.carta32,
        R.drawable.carta33,
        R.drawable.carta34,
        R.drawable.carta35,
        R.drawable.carta36,
        R.drawable.carta37,
        R.drawable.carta38,
        R.drawable.carta39,
        R.drawable.carta40,
        R.drawable.carta41,
        R.drawable.carta42,
        R.drawable.carta43,
        R.drawable.carta44,
        R.drawable.carta45,
        R.drawable.carta46,
        R.drawable.carta47,
        R.drawable.carta48,
        R.drawable.carta49,
        R.drawable.carta50,
        R.drawable.carta51,
        R.drawable.carta52,
        R.drawable.carta53,
        R.drawable.carta54
    )
    val audios = arrayOf(
        R.raw.audio1,
        R.raw.audio2,
        R.raw.audio3,
        R.raw.audio4,
        R.raw.audio5,
        R.raw.audio6,
        R.raw.audio7,
        R.raw.audio8,
        R.raw.audio9,
        R.raw.audio10,
        R.raw.audio11,
        R.raw.audio12,
        R.raw.audio13,
        R.raw.audio14,
        R.raw.audio15,
        R.raw.audio16,
        R.raw.audio17,
        R.raw.audio18,
        R.raw.audio19,
        R.raw.audio20,
        R.raw.audio21,
        R.raw.audio22,
        R.raw.audio23,
        R.raw.audio24,
        R.raw.audio25,
        R.raw.audio26,
        R.raw.audio27,
        R.raw.audio28,
        R.raw.audio29,
        R.raw.audio30,
        R.raw.audio31,
        R.raw.audio32,
        R.raw.audio33,
        R.raw.audio34,
        R.raw.audio35,
        R.raw.audio36,
        R.raw.audio37,
        R.raw.audio38,
        R.raw.audio39,
        R.raw.audio40,
        R.raw.audio41,
        R.raw.audio42,
        R.raw.audio43,
        R.raw.audio44,
        R.raw.audio45,
        R.raw.audio46,
        R.raw.audio47,
        R.raw.audio48,
        R.raw.audio49,
        R.raw.audio50,
        R.raw.audio51,
        R.raw.audio52,
        R.raw.audio53,
        R.raw.audio54
    )
    var cartaTirada = BooleanArray(cartas.size)
    val indices = IntArray(cartas.size)
    var gameOver = false
    var tirar = GlobalScope.launch(EmptyCoroutineContext, CoroutineStart.LAZY) {}
    var restantes = GlobalScope.launch(EmptyCoroutineContext, CoroutineStart.LAZY) {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar!!.hide()

        // Carta inicial
        binding.carta.setImageResource(R.drawable.carta0)

        binding.startButton.setOnClickListener {
            if (gameOver) {
                restantes.cancel()
                hilo(this).start()
            }
            // Desactivar botones
            binding.startButton.isEnabled = false
            binding.verifyButton.isEnabled = false
            // Activar botones
            binding.lotteryButton.isEnabled = true
            // Cambiar colores
            binding.startButton.setBackgroundColor(Color.LTGRAY)
            binding.verifyButton.setBackgroundColor(Color.LTGRAY)
            binding.lotteryButton.setBackgroundColor(Color.parseColor("#288C54"))
            // Barajeamos las cartas
            for (i in 0..53) {
                indices[i] = i
            }
            indices.shuffle()
            // Iniciamos
            tirar = GlobalScope.launch(EmptyCoroutineContext, CoroutineStart.LAZY) {
                for (i in 0..53) {
                    println("Carta: $i")
                    // Imagen
                    runOnUiThread {
                        binding.carta.setImageResource(cartas[indices[i]])
                        cartaTirada[indices[i]] = true
                    }
                    // Audio
                    try {
                        var audio =
                            MediaPlayer.create(this@MainActivity, audios[indices[i]])
                        audio.start()
                        audio.setOnCompletionListener { mp -> mp.release() }
                    } catch (e: Exception) {
                        println(e.message)
                    }
                    delay(2200L)
                }
            }
            tirar.start()
        }

        binding.lotteryButton.setOnClickListener {
            // Dejamos de tirar cartas
            tirar.cancel()
            // Desactivar boton
            binding.lotteryButton.isEnabled = false
            // Activar boton
            binding.verifyButton.isEnabled = true
            // Cambiar colores
            binding.verifyButton.setBackgroundColor(Color.parseColor("#D63534"))
            binding.lotteryButton.setBackgroundColor(Color.LTGRAY)
            // Audio
            try {
                var audio =
                    MediaPlayer.create(this@MainActivity, R.raw.victory)
                audio.start()
                audio.setOnCompletionListener { mp -> mp.release() }
            } catch (e: Exception) {
                println(e.message)
            }
        }

        binding.verifyButton.setOnClickListener {
            // Desactivar boton
            binding.verifyButton.isEnabled = false
            // Activar boton
            binding.startButton.isEnabled = true
            // Cambiar colores
            binding.startButton.setBackgroundColor(Color.parseColor("#288C54"))
            binding.verifyButton.setBackgroundColor(Color.LTGRAY)
            // Iniciamos verificación
            restantes = GlobalScope.launch(EmptyCoroutineContext, CoroutineStart.LAZY) {
                // Audio
                gameOver = true
                try {
                    var audio =
                        MediaPlayer.create(this@MainActivity, R.raw.verificar)
                    audio.start()
                    audio.setOnCompletionListener { mp -> mp.release() }
                } catch (e: Exception) {
                    println(e.message)
                }
                delay(3000)
                // Filtrar las cartas tiradas
                for (i in 0..53) {
                    println("Carta: $i -> ${cartaTirada[indices[i]]}")
                    if (!cartaTirada[indices[i]]) {
                        // Imagen
                        runOnUiThread {
                            binding.carta.setImageResource(cartas[indices[i]])
                            cartaTirada[indices[i]] = true
                        }
                        // Audio
                        try {
                            var audio =
                                MediaPlayer.create(this@MainActivity, audios[indices[i]])
                            audio.start()
                            audio.setOnCompletionListener { mp -> mp.release() }
                        } catch (e: Exception) {
                            println(e.message)
                        }
                        delay(2000L)
                    }
                }
            }
            restantes.start()
        }
    }
}

class hilo(activity: MainActivity) : Thread() {
    var pointer = activity
    override fun run() {
        super.run()
        // Reestablecer estado de cartas a false
        for (i in 0..53) {
            pointer.cartaTirada[i] = false
        }
        pointer.gameOver = false
        // Audio
        try {
            var audio =
                MediaPlayer.create(pointer.applicationContext, R.raw.newgame)
            audio.start()
            audio.setOnCompletionListener { mp -> mp.release()  }
        } catch (e: Exception) {
            println(e.message)
        }
    }
}