package maa.gameboyfilter;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;

import maa.gameboyfilter.GameBoyFilter.BitmapHelper;
import maa.gameboyfilter.GameBoyFilter.GameBoyDithering;

public class MainActivity extends AppCompatActivity {
    ImageView img;
    Button Load,Convert;
    Bitmap preview,FinalBitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        img = findViewById(R.id.img);
        Convert = findViewById(R.id.convert);
        Load = findViewById(R.id.load);
        Load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Choose Picture"), 1);
            }
        });
        Convert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(preview!=null){
                    GameBoyDithering gameBoyDithering = new GameBoyDithering();
                    BitmapHelper bitmapHelper = new BitmapHelper();
                    FinalBitmap = bitmapHelper.getResized(preview, 310, false);
                    FinalBitmap = gameBoyDithering.ordered2By2Bayer(FinalBitmap);
                    FinalBitmap = bitmapHelper.ContrastBrightness(FinalBitmap, 1, -24);
                    FinalBitmap = bitmapHelper.changeColor(FinalBitmap, getResources().getColor(R.color.gameboycolor));
                    img.setImageBitmap(FinalBitmap);
                }else{
                    Toast.makeText(MainActivity.this, "Select image first", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            final Uri selectedimg = data.getData();
            try {
                preview = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedimg);
                img.setImageBitmap(preview);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
