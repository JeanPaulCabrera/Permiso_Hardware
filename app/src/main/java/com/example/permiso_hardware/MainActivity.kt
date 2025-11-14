package com.example.permiso_hardware // Asegúrate que este sea tu package name

import android.Manifest
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    // 1. Registrar el "contrato" para la solicitud de permiso.
    // Esto se hace como una variable de la clase.
    // Recibe un booleano 'isGranted' (true si se concedió, false si se negó)
    private val requestLocationPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                // Permiso CONCEDIDO
                // Aquí podrías llamar a una función para obtener la ubicación
                showToast("Permiso de ubicación CONCEDIDO")
            } else {
                // Permiso DENEGADO
                // Informa al usuario que la función no está disponible
                showToast("Permiso de ubicación DENEGADO")
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 2. Encontrar el botón en el layout
        val btnRequest: Button = findViewById(R.id.btnRequestLocation)

        // 3. Configurar el OnClickListener del botón
        btnRequest.setOnClickListener {
            // 4. Lanzar la solicitud de permiso
            // (Pedimos ubicación PRECISA, pero puedes cambiarlo por COARSE)
            requestLocationPermissionLauncher.launch(
                Manifest.permission.ACCESS_FINE_LOCATION
            )
        }
    }

    // Función simple para mostrar Toasts
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}