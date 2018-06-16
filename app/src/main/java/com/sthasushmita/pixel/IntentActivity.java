package com.example.sujal.pocketmed;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by sujal on 6/2/2017.
 */
public class IntentActivity extends AppCompatActivity {

          ImageButton  capture,pick;

    ImageView img;
    static public int CAPTURE_IMAGE_ACTIVITY_REQ = 200;
    static public int PICK_GALLERY= 300;
    static public int REQ_B = 100;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == REQ_B) {
                String returnData = data.getExtras().getString("return");

                Toast.makeText(IntentActivity.this, returnData, Toast.LENGTH_SHORT).show();
            } else if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQ) {
                Bitmap photo = (Bitmap) data.getExtras().get("data");

                img.setImageBitmap(photo);
                Intent i=new Intent(IntentActivity.this,Detail.class);
                i.putExtra("photo", photo);
                startActivity(i);



            }
            else
            {

                Uri selectedImage=data.getData();


                img.setImageURI(selectedImage);
            }

        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent);

        img=(ImageView)findViewById(R.id.image);


        pick = (ImageButton)findViewById(R.id.pick);
        pick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickFromGallery(v);
            }
        });

        capture = (ImageButton)findViewById(R.id.click);
        capture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                capture(v);
            }
        });
    }
    public void capture(View v)
    {
        if(getApplicationContext().getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA))
        {
            Intent intnt=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intnt.putExtra(MediaStore.EXTRA_OUTPUT,"img");
            startActivityForResult(intnt,CAPTURE_IMAGE_ACTIVITY_REQ);
        }
    }
    public void pickFromGallery (View v)
    {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent,PICK_GALLERY);
    }
}

