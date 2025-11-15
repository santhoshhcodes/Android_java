package com.example.myproject.screen;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;
import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.ImageCaptureException;
import androidx.camera.core.ImageProxy;
import androidx.camera.core.Preview;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.PreviewView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.myproject.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.common.util.concurrent.ListenableFuture;

import java.nio.ByteBuffer;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {

    GoogleMap mMap;
    FusedLocationProviderClient fusedLocationClient;

    PreviewView previewView;
    ImageView capturedImage;
    Button captureBtn, retakeBtn, okBtn;

    FrameLayout cameraContainer, capturedLayout;

    ImageCapture imageCapture;
    Toolbar toolbar;

    private static final int CAMERA_PERMISSION = 100;
    private static final int LOCATION_PERMISSION = 200;

    CameraSelector cameraSelector = CameraSelector.DEFAULT_FRONT_CAMERA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_map);

        previewView = findViewById(R.id.previewView);
        capturedImage = findViewById(R.id.capturedImage);

        captureBtn = findViewById(R.id.captureBtn);
        retakeBtn = findViewById(R.id.retakeBtn);
        okBtn = findViewById(R.id.okBtn);

        cameraContainer = findViewById(R.id.cameraContainer);
        capturedLayout = findViewById(R.id.capturedLayout);

        toolbar = findViewById(R.id.MapToolBarID);
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(v -> onBackPressed());

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);

        }



        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        askCameraPermission();

        captureBtn.setOnClickListener(v -> capturePhoto());
        retakeBtn.setOnClickListener(v -> retakePhoto());
        okBtn.setOnClickListener(v -> confirmPhoto());

    }



    private void askCameraPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA},
                    CAMERA_PERMISSION);

        } else {
            startCamera();
        }
    }


    private void startCamera() {
        ListenableFuture<ProcessCameraProvider> cameraProviderFuture =
                ProcessCameraProvider.getInstance(this);

        cameraProviderFuture.addListener(() -> {
            try {
                ProcessCameraProvider cameraProvider = cameraProviderFuture.get();

                Preview preview = new Preview.Builder().build();

                imageCapture = new ImageCapture.Builder().build();

                preview.setSurfaceProvider(previewView.getSurfaceProvider());

                previewView.setScaleX(-1f);

                cameraProvider.unbindAll();
                cameraProvider.bindToLifecycle(this, cameraSelector, preview, imageCapture);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }, ContextCompat.getMainExecutor(this));
    }

    private void capturePhoto() {

        imageCapture.takePicture(
                ContextCompat.getMainExecutor(this),
                new ImageCapture.OnImageCapturedCallback() {

                    @Override
                    public void onCaptureSuccess(@NonNull ImageProxy image) {

                        Bitmap bitmap = imageProxyToBitmap(image);
                        Matrix matrix = new Matrix();
                        matrix.preScale(-1f, 1f);
                        Bitmap mirroredBitmap = Bitmap.createBitmap(
                                bitmap,
                                0, 0,
                                bitmap.getWidth(),
                                bitmap.getHeight(),
                                matrix,
                                true
                        );
                        image.close();
                        showBitmap(mirroredBitmap);
                    }
                    @Override
                    public void onError(@NonNull ImageCaptureException exc) {
                        Toast.makeText(MapActivity.this, "Failed to capture!", Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }

    private Bitmap imageProxyToBitmap(ImageProxy image) {
        ByteBuffer buffer = image.getPlanes()[0].getBuffer();
        byte[] bytes = new byte[buffer.remaining()];
        buffer.get(bytes);
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }

    private void showBitmap(Bitmap bmp) {
        capturedImage.setImageBitmap(bmp);

        cameraContainer.setVisibility(View.GONE);
        capturedLayout.setVisibility(View.VISIBLE);
    }

    private void retakePhoto() {
        capturedLayout.setVisibility(View.GONE);
        cameraContainer.setVisibility(View.VISIBLE);
        startCamera();
    }

    private void confirmPhoto() {
        retakeBtn.setVisibility(View.GONE);
        okBtn.setVisibility(View.GONE);
        Toast.makeText(this, "Photo Confirmed!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;
        askLocationPermission();
    }

    private void askLocationPermission() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    LOCATION_PERMISSION);

        } else {
            if (isLocationEnabled()) {
                enableLocation();
            } else {
                promptEnableLocation();
            }
        }
    }

    private boolean isLocationEnabled() {
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
                || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }

    private void promptEnableLocation() {
        new android.app.AlertDialog.Builder(this)
                .setTitle("Enable Location")
                .setMessage("Location services are turned off. Please enable them to use the map.")
                .setPositiveButton("Settings", (dialog, which) -> {
                    Intent intent = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    startActivity(intent);
                })
                .setNegativeButton("Cancel", null)
                .show();
    }


    private void enableLocation() {
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED)
            return;

        mMap.setMyLocationEnabled(true);

        fusedLocationClient.getLastLocation().addOnSuccessListener(location -> {
            if (location != null) {
                LatLng pos = new LatLng(location.getLatitude(), location.getLongitude());
                mMap.addMarker(new MarkerOptions().position(pos).title("My Location"));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(pos, 15));
            }
        });


    }
}
