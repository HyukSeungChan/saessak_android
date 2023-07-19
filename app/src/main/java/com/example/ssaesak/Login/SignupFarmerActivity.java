package com.example.ssaesak.Login;

import android.Manifest;
import android.app.Activity;
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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.ssaesak.Farmgroup.FarmgroupReview;
import com.example.ssaesak.Model.Farm;
import com.example.ssaesak.Model.User;
import com.example.ssaesak.R;
import com.example.ssaesak.Retrofit.MyRetrofit;

import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupFarmerActivity extends Activity {


    private static final int REQUEST_PERMISSION = 1;
    private static final int REQUEST_GALLERY = 2;

    EditText editTextTitle;
    EditText editTextDescription;
    TextView descriptionLength;
    EditText editTextPosition;
    EditText editTextPhone;
    ImageView imageView;

    Button nextButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_farmer);

        this.nextButton = findViewById(R.id.button_next);

        this.editTextTitle = findViewById(R.id.edittext_title);
        this.editTextDescription = findViewById(R.id.edittext_description);
        this.descriptionLength = findViewById(R.id.edittext_description);
//        this.editTextDescription.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                descriptionLength.setText(editTextDescription.getText().toString().length() + "/300자");
//                if (editTextDescription.getText().toString().length() > 300) {
//                    editTextDescription.setText(charSequence.subSequence(0, 300));
//                }
//            }
//            @Override
//            public void afterTextChanged(Editable editable) {}
//        });

        this.editTextPosition = findViewById(R.id.edittext_position);
        this.editTextPhone = findViewById(R.id.edittext_phone);
        this.imageView = findViewById(R.id.image);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(getBaseContext(), Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(SignupFarmerActivity.this,
                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            REQUEST_PERMISSION);
                } else {
                    openGallery();
                }
            }
        });


        this.nextButton.setOnClickListener(v -> {
            Farm.getInstance().setName(editTextTitle.getText().toString());
            Farm.getInstance().setIntroduction(editTextDescription.getText().toString());
            Farm.getInstance().setAddress(editTextPosition.getText().toString());
            Farm.getInstance().setPhone(editTextPhone.getText().toString());
            startActivity(new Intent(getBaseContext(), SignupFarmerAgricultureActivity.class));
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
