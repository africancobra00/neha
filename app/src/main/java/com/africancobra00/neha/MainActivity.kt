package com.africancobra00.neha

import android.os.Bundle
import android.speech.tts.TextToSpeech
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import java.util.Locale

class MainActivity : ComponentActivity() {
    private lateinit var tts: TextToSpeech

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        tts = TextToSpeech(this) { status ->
            if (status == TextToSpeech.SUCCESS) {
                tts.language = Locale.US
                tts.setPitch(0.95f)
                tts.setSpeechRate(0.9f)
                // optional: use pre-recorded cinematic welcome sound instead for production
                speak("Welcome. Neha is online and ready to assist you.")
            }
        }

        setContent {
            JarvisOpeningScreen {
                // navigate to main Neha interface
            }
        }
    }

    private fun speak(message: String) {
        tts.speak(message, TextToSpeech.QUEUE_FLUSH, null, "neha_welcome")
    }

    override fun onDestroy() {
        tts.stop()
        tts.shutdown()
        super.onDestroy()
    }
}
