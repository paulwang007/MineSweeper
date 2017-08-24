package com.example.paul.mine;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView space1;
    ImageView space2;
    ImageView space3;
    ImageView space4;
    ImageView space5;
    ImageView space6;
    ImageView space7;
    ImageView space8;
    ImageView space9;

    private int minePosition = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startGame();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Random random = new Random();
        minePosition = random.nextInt(8) + 1;
    }

    @Override
    public void onClick(View view) {
        if ((int) view.getTag() == minePosition) {
            loadMineImage((ImageView) view);
        } else {
            LoadNotMineImage((ImageView) view);
        }
    }

    private void startGame() {
        space1 = (ImageView) findViewById(R.id.space1);
        space2 = (ImageView) findViewById(R.id.space2);
        space3 = (ImageView) findViewById(R.id.space3);
        space4 = (ImageView) findViewById(R.id.space4);
        space5 = (ImageView) findViewById(R.id.space5);
        space6 = (ImageView) findViewById(R.id.space6);
        space7 = (ImageView) findViewById(R.id.space7);
        space8 = (ImageView) findViewById(R.id.space8);
        space9 = (ImageView) findViewById(R.id.space9);

        space1.setBackgroundColor(getResources().getColor(R.color.grey));
        space2.setBackgroundColor(getResources().getColor(R.color.grey));
        space3.setBackgroundColor(getResources().getColor(R.color.grey));
        space4.setBackgroundColor(getResources().getColor(R.color.grey));
        space5.setBackgroundColor(getResources().getColor(R.color.grey));
        space6.setBackgroundColor(getResources().getColor(R.color.grey));
        space7.setBackgroundColor(getResources().getColor(R.color.grey));
        space8.setBackgroundColor(getResources().getColor(R.color.grey));
        space9.setBackgroundColor(getResources().getColor(R.color.grey));

        space1.setOnClickListener(this);
        space2.setOnClickListener(this);
        space3.setOnClickListener(this);
        space4.setOnClickListener(this);
        space5.setOnClickListener(this);
        space6.setOnClickListener(this);
        space7.setOnClickListener(this);
        space8.setOnClickListener(this);
        space9.setOnClickListener(this);

        space1.setTag(1);
        space2.setTag(2);
        space3.setTag(3);
        space4.setTag(4);
        space5.setTag(5);
        space6.setTag(6);
        space7.setTag(7);
        space8.setTag(8);
        space9.setTag(9);
    }

    private void LoadNotMineImage(ImageView imageView) {
        imageView.setBackgroundColor(getResources().getColor(R.color.white));
    }

    private void loadMineImage(ImageView imageView) {
        imageView.setImageBitmap(decodeSampledBitmapFromResource(getResources(), R.drawable.mine, 100, 100));
    }

    public Bitmap decodeSampledBitmapFromResource(Resources res, int resId,
                                                  int reqWidth, int reqHeight) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }


    private int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) >= reqHeight
                    && (halfWidth / inSampleSize) >= reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }
}
