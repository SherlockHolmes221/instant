package myandroid.jike.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.jph.takephoto.model.TImage;

import java.io.File;
import java.util.ArrayList;

import myandroid.jike.R;


public class FaceResultActivity extends AppCompatActivity {
    ArrayList<TImage>images;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_result_layout);
        images= (ArrayList<TImage>) getIntent().getSerializableExtra("images");
        showImg();
    }
    private void showImg() {
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.llImages);
        for (int i = 0, j = images.size(); i < j - 1; i += 2) {
            View view = LayoutInflater.from(this).inflate(R.layout.activity_image_show, null);
            ImageView imageView1 = (ImageView) view.findViewById(R.id.imgShow1);
            ImageView imageView2 = (ImageView) view.findViewById(R.id.imgShow2);
            Glide.with(this).load(new File(images.get(i).getCompressPath())).into(imageView1);
            Glide.with(this).load(new File(images.get(i + 1).getCompressPath())).into(imageView2);

           Log.e("PATH1",images.get(i).getCompressPath());
           Log.e("PATH2",images.get(i+1).getCompressPath());

            linearLayout.addView(view);
        }
        if (images.size() % 2 == 1) {
            View view = LayoutInflater.from(this).inflate(R.layout.activity_image_show, null);
            ImageView imageView1 = (ImageView) view.findViewById(R.id.imgShow1);
            Glide.with(this).load(new File(images.get(images.size() - 1).getCompressPath())).into(imageView1);

            Log.e("PATH1",images.get(images.size() - 1).getCompressPath());

            linearLayout.addView(view);
        }

    }
}
