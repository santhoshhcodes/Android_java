package com.example.myproject.screen;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.graphics.Bitmap;
import androidx.appcompat.widget.Toolbar;


import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.myproject.R;


public class Expense extends AppCompatActivity {


    EditText TotalExpense, AdvAmount, BalanceAmount;
    ImageView SelectedImage;

    LinearLayout imageContainer;


    Button AddImage;

    private static final int SELECT_PICTURE = 200;
    private static final int CAMERA_PERMISSION_CODE = 100;
    private static final int CAMERA_REQUEST_CODE = 101;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        EdgeToEdge.enable(this);
        TotalExpense = findViewById(R.id.totalExpense);
        AdvAmount = findViewById(R.id.advAmount);
        BalanceAmount = findViewById(R.id.balanceAmount);


        TotalExpense.addTextChangedListener(balanceChange);
        AdvAmount.addTextChangedListener(balanceChange);

        AddImage = findViewById(R.id.addImage);
        imageContainer = findViewById(R.id.imageContainer);
        Toolbar toolbar = findViewById(R.id.Expencetoolbar);
        setSupportActionBar(toolbar);


        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }


        toolbar.setNavigationOnClickListener(v -> onBackPressed());


        //----Dailog box----
        AddImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] options = {"Take Photo", "Choose from Gallery", "Cancel"};
                AlertDialog.Builder builder = new AlertDialog.Builder(Expense.this);
                builder.setTitle("Select Image").setItems(options, (dialog, which) -> {
                    if (which == 0) {
                        openCameraWithPermission();
                    } else if (which == 1) {
                        imageChooser();
                    }
                }).show();
            }
        });
    }

    private void openCameraWithPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA},
                    CAMERA_PERMISSION_CODE);
        } else {
            openCamera();
        }
    }

    private void openCamera() {
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, CAMERA_REQUEST_CODE);
    }

    void imageChooser() {
        Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        i.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        i.setType("image/*");
        startActivityForResult(Intent.createChooser(i, "Select Picture"), SELECT_PICTURE);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == CAMERA_REQUEST_CODE && data != null) {
                Bitmap imageBitmap = (Bitmap) data.getExtras().get("data");
                ImageView imageView = new ImageView(this);
                imageView.setLayoutParams(new LinearLayout.LayoutParams(400, 400));
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                imageView.setPadding(10, 10, 10, 10);
                imageView.setImageBitmap(imageBitmap);
                imageContainer.addView(imageView);
            } else if (requestCode == SELECT_PICTURE && data != null) {
                if (data.getClipData() != null) {
                    int count = data.getClipData().getItemCount();
                    for (int i = 0; i < count; i++) {
                        Uri imageUri = data.getClipData().getItemAt(i).getUri();
                        addImageToView(imageUri);
                    }
                } else if (data.getData() != null) {
                    Uri imageUri = data.getData();
                    addImageToView(imageUri);
                }
            }
        }
    }


    private void addImageToView(Uri imageUri) {
        ImageView imageView = new ImageView(this);
        imageView.setLayoutParams(new LinearLayout.LayoutParams(400, 400));
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setPadding(10, 10, 10, 10);
        imageView.setImageURI(imageUri);
        imageContainer.addView(imageView);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CAMERA_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openCamera();
            } else {
                Toast.makeText(this, "Camera permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private final TextWatcher balanceChange = new TextWatcher() {
        @Override
        public void afterTextChanged(Editable s) {
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            calculatingBalance();

        }
    };

    public void calculatingBalance() {
        try {

            String totalStr = TotalExpense.getText().toString().trim();
            String advStr = AdvAmount.getText().toString().trim();

            if (totalStr.isEmpty() || advStr.isEmpty()) {
                BalanceAmount.setText("");
                return;
            }

            int totalexpense = Integer.parseInt(totalStr);
            int advanceAmount = Integer.parseInt(advStr);
            int balance = totalexpense - advanceAmount;

            BalanceAmount.setText(String.valueOf(balance));

        } catch (Exception e) {
            Toast.makeText(this, "Please enter Expence", Toast.LENGTH_SHORT).show();
        }


    }


}