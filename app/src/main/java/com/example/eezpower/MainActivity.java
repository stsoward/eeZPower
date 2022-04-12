package com.example.eezpower;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.bluetooth.BluetoothSocket;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    //Bluetooth
    private String deviceName = null;
    private String deviceAddress;

    private final static int CONNECTING_STATUS = 1; // used in bluetooth handler to identify message status
    private final static int MESSAGE_READ = 2; // used in bluetooth handler to identify message update
    //Bluetooth-end

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getSupportFragmentManager();

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        fragmentManager.beginTransaction().replace(R.id.flFragment, new BluetoothFragment()).commit();

        bottomNavigationView.setSelectedItemId(R.id.miBluetooth);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;

                switch(item.getItemId()){
                    case R.id.miBluetooth:
                        fragment = new BluetoothFragment();
                        break;
                    case R.id.miStats:
                        fragment = new StatsFragment();
                        break;
                    case R.id.miTrips:
                        fragment = new TripsFragment();
                        break;
                    case R.id.miMode:
                        fragment = new ModeFragment();
                        break;
                }
                fragmentManager.beginTransaction().replace(R.id.flFragment, fragment).commit();

                return true;
            }
        });
    }

    private class SendRecieve extends Thread{
        private final BluetoothSocket bluetoothSocket;
        private final InputStream inputStream;
        private final OutputStream outputStream;

        public SendRecieve(BluetoothSocket _bluetoothSocket) throws IOException {
            bluetoothSocket = _bluetoothSocket;
            InputStream tempIn = null;
            OutputStream tempOut = null;
            tempIn = bluetoothSocket.getInputStream();
            tempOut = bluetoothSocket.getOutputStream();
            inputStream = tempIn;
            outputStream = tempOut;

        }
        /*

        public void run(){
            byte[] buffer=new byte[1024];
            int bytes;

            while(true){
                try {
                    bytes=inputStream.read(buffer);
                    handler.obtaion
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }*/
    }
}