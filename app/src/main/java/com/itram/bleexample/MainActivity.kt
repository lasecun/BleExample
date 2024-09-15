package com.itram.bleexample

import android.annotation.SuppressLint
import android.bluetooth.BluetoothDevice
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.itram.bleexample.ui.theme.BleExampleTheme
import java.util.UUID

class MainActivity : ComponentActivity() {

    companion object {
        // Random UUID for our service known between the client and server to allow communication
//        val SERVICE_UUID: UUID = UUID.fromString("00002222-0000-1000-8000-00805f9b34fb")
        val SERVICE_UUID: UUID = UUID.fromString("00001800-0000-1000-8000-00805f9b34fb")

        // Same as the service but for the characteristic
//        val CHARACTERISTIC_UUID: UUID = UUID.fromString("00001111-0000-1000-8000-00805f9b34fb")
        val CHARACTERISTIC_UUID: UUID = UUID.fromString("00002a00-0000-1000-8000-00805f9b34fb")
    }

    @SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            BleExampleTheme {
                Scaffold { padding ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(padding)
                    ) {

                        var selectedDevice by remember {
                            mutableStateOf<BluetoothDevice?>(null)
                        }

                        AnimatedContent(targetState = selectedDevice, label = "Selected device") { device ->
                            if (device == null) {
                                // Scans for BT devices and handles clicks (see FindDeviceSample)
                                FindDevicesScreen {
                                    selectedDevice = it
                                }
                            } else {
                                // Once a device is selected show the UI and try to connect device
                                ConnectDeviceScreen(device = device) {
                                    selectedDevice = null
                                }
                            }
                        }
                    }
                }

            }

        }
    }
}


