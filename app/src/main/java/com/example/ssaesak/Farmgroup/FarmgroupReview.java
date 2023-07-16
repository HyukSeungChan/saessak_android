package com.example.ssaesak.Farmgroup;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.ssaesak.Main.MainActivity;
import com.example.ssaesak.R;

import java.util.regex.Pattern;

public class FarmgroupReview  extends AppCompatActivity {

    private static final int REQUEST_PERMISSION = 1;
    private static final int REQUEST_GALLERY = 2;

    private LinearLayout btnPickImage;
    private ImageView imageView;
    private Button nextButton;
    private TextView tooltip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_farmgroup);

        btnPickImage = findViewById(R.id.gallery);
        imageView = findViewById(R.id.testImage);
        nextButton = findViewById(R.id.button_next);
        tooltip = findViewById(R.id.tooltip);

        EditText editText = findViewById(R.id.edittext);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (editText.getText().toString().length() > 400){
                    editText.setText(charSequence.subSequence(0, 400));
                    editText.setSelection(400);
                } else if (editText.getText().toString().length() > 0) {
                    nextButton.setClickable(true);
                    nextButton.setBackground(getResources().getDrawable(R.drawable.button_primary, null));
                    tooltip.setText(editText.getText().toString().length() + "/400자");
                } else {
                    nextButton.setClickable(false);
                    nextButton.setBackground(getResources().getDrawable(R.drawable.button_gray, null));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }

        });


        btnPickImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(getBaseContext(), Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(FarmgroupReview.this,
                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            REQUEST_PERMISSION);
                } else {
                    openGallery();
                }
            }
        });

    }

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, REQUEST_GALLERY);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openGallery();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_GALLERY && resultCode == RESULT_OK && data != null) {
            Uri selectedImageUri = data.getData();
            Log.e("image", selectedImageUri.toString());
            imageView.setImageURI(selectedImageUri);
        }
    }
}