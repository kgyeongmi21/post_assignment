package com.example.post;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.example.post.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.download.setOnClickListener(v ->startSecondActivity());
        binding.uploadpic.setOnClickListener(v -> getPhoto());
        binding.writerName.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN &&
                        event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                    return true;
                }

                return false;
            }
        });


        binding.textContent.setMovementMethod(new ScrollingMovementMethod());

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) !=
                PackageManager.PERMISSION_GRANTED) {
            requestPermissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE);
        }
    }

    private ActivityResultLauncher<String> requestPermissionLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
                if (isGranted) {
                    Toast.makeText(this, "권한이 설정되었습니다", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "권한이 설정되지 않았습니다. 권한이 없으므로 앱을 종료합니다.", Toast.LENGTH_SHORT).show();
                    finish();
                }
            });

    private void getPhoto() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        resultLauncher.launch(intent);
    }

    private ActivityResultLauncher<Intent> resultLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                    result -> {
                        Intent data = result.getData();
                        Log.i("TEST", "data: " + data);

                        if(result.getResultCode() == RESULT_OK && null != data) {
                            Uri selectedImage = data.getData();
                            Bitmap bitmap = loadBitmap(selectedImage);
                            binding.mainimage.setImageBitmap(bitmap);
                        }
                    });

    private Bitmap loadBitmap(Uri uri) {
        String[] filePathColumn = {MediaStore.Images.Media.DATA};

        Cursor cursor = getContentResolver().query(uri,
                filePathColumn, null, null, null);
        cursor.moveToFirst();

        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
        String picturePath = cursor.getString(columnIndex);
        cursor.close();

        return BitmapFactory.decodeFile(picturePath);
    }

    public void startSecondActivity() {
        Intent intent = new Intent(this, SecondActivity.class);
        String message = binding.textContent.getText().toString();
        intent.putExtra("EXTRA_MESSAGE", message);
        startActivity(intent);

        //Intent intent = new Intent(this, SecondActivity.class);
        //String image = binding.mainimage.getImageAlpha()


    }

    private void moveScroll() {
        final int scrollAmount = binding.textContent.getLayout().getLineTop(binding.textContent.getLineCount()) - binding.textContent.getHeight();
        if (scrollAmount > 0)
            binding.textContent.scrollTo(0, scrollAmount);
        else
            binding.textContent.scrollTo(0,0);
        moveScroll();
    }

}