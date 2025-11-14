package com.example.permiso_hardware

import android.Manifest
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    // Launcher para solicitar permiso de ubicación
    private val requestLocationPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                showToast("Permiso de ubicación CONCEDIDO")
            } else {
                showToast("Permiso de ubicación DENEGADO")
            }
        }

    // Launcher para solicitar permiso de micrófono
    private val requestMicPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                showToast("Permiso de micrófono CONCEDIDO")
            } else {
                showToast("Permiso de micrófono DENEGADO")
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Botón de ubicación
        val btnRequestLocation: Button = findViewById(R.id.btnRequestLocation)
        btnRequestLocation.setOnClickListener {
            requestLocationPermissionLauncher.launch(
                Manifest.permission.ACCESS_FINE_LOCATION
            )
        }

        // Botón de micrófono
        val btnRequestMic: Button = findViewById(R.id.btnRequestMic)
        btnRequestMic.setOnClickListener {
            requestMicPermissionLauncher.launch(
                Manifest.permission.RECORD_AUDIO
            )
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
