package com.example.makharijulhuruf;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

public class LearnActivity extends AppCompatActivity {

    LinearLayout view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn);
        view = findViewById(R.id.view);
        setupView();
    }
    public void setupView(){
        final View image = getLayoutInflater().inflate(R.layout.image_layout,null,false);
        ImageView img = image.findViewById(R.id.img);
        img.setImageResource(R.drawable.image_1);
        view.addView(image);

        final View image2 = getLayoutInflater().inflate(R.layout.image_layout,null,false);
        ImageView img2 = image2.findViewById(R.id.img);
        img2.setImageResource(R.drawable.image_2);
        view.addView(image2);

        final View image3 = getLayoutInflater().inflate(R.layout.image_layout,null,false);
        ImageView img3 = image3.findViewById(R.id.img);
        img3.setImageResource(R.drawable.image_3);
        view.addView(image3);

        final View image4 = getLayoutInflater().inflate(R.layout.image_layout,null,false);
        ImageView img4 = image4.findViewById(R.id.img);
        img4.setImageResource(R.drawable.image_4);
        view.addView(image4);

        final View image5 = getLayoutInflater().inflate(R.layout.image_layout,null,false);
        ImageView img5 = image5.findViewById(R.id.img);
        img5.setImageResource(R.drawable.image_5);
        view.addView(image5);

        final View image6 = getLayoutInflater().inflate(R.layout.image_layout,null,false);
        ImageView img6 = image6.findViewById(R.id.img);
        img6.setImageResource(R.drawable.image_6);
        view.addView(image6);

        final View image7 = getLayoutInflater().inflate(R.layout.image_layout,null,false);
        ImageView img7 = image7.findViewById(R.id.img);
        img7.setImageResource(R.drawable.image_7);
        view.addView(image7);

        final View image8 = getLayoutInflater().inflate(R.layout.image_layout,null,false);
        ImageView img8 = image8.findViewById(R.id.img);
        img8.setImageResource(R.drawable.image_8);
        view.addView(image8);

        final View image9 = getLayoutInflater().inflate(R.layout.image_layout,null,false);
        ImageView img9 = image9.findViewById(R.id.img);
        img9.setImageResource(R.drawable.image_9);
        view.addView(image9);

        final View image10 = getLayoutInflater().inflate(R.layout.image_layout,null,false);
        ImageView img10 = image10.findViewById(R.id.img);
        img10.setImageResource(R.drawable.image_10);
        view.addView(image10);

        final View image11 = getLayoutInflater().inflate(R.layout.image_layout,null,false);
        ImageView img11 = image11.findViewById(R.id.img);
        img11.setImageResource(R.drawable.image_11);
        view.addView(image11);

        final View image12 = getLayoutInflater().inflate(R.layout.image_layout,null,false);
        ImageView img12 = image12.findViewById(R.id.img);
        img12.setImageResource(R.drawable.image_12);
        view.addView(image12);

        final View image13 = getLayoutInflater().inflate(R.layout.image_layout,null,false);
        ImageView img13 = image13.findViewById(R.id.img);
        img13.setImageResource(R.drawable.image_13);
        view.addView(image13);

        final View image14 = getLayoutInflater().inflate(R.layout.image_layout,null,false);
        ImageView img14 = image14.findViewById(R.id.img);
        img14.setImageResource(R.drawable.image_14);
        view.addView(image14);

        final View image15 = getLayoutInflater().inflate(R.layout.image_layout,null,false);
        ImageView img15 = image15.findViewById(R.id.img);
        img15.setImageResource(R.drawable.image_15);
        view.addView(image15);

        final View image16 = getLayoutInflater().inflate(R.layout.image_layout,null,false);
        ImageView img16 = image16.findViewById(R.id.img);
        img16.setImageResource(R.drawable.image_16);
        view.addView(image16);

        final View image17 = getLayoutInflater().inflate(R.layout.image_layout,null,false);
        ImageView img17 = image17.findViewById(R.id.img);
        img17.setImageResource(R.drawable.image_17);
        view.addView(image17);

        final View image18 = getLayoutInflater().inflate(R.layout.image_layout,null,false);
        ImageView img18 = image18.findViewById(R.id.img);
        img18.setImageResource(R.drawable.image_18);
        view.addView(image18);

        final View image19 = getLayoutInflater().inflate(R.layout.image_layout,null,false);
        ImageView img19 = image19.findViewById(R.id.img);
        img19.setImageResource(R.drawable.image_19);
        view.addView(image19);

        final View image20 = getLayoutInflater().inflate(R.layout.image_layout,null,false);
        ImageView img20 = image20.findViewById(R.id.img);
        img20.setImageResource(R.drawable.image_20);
        view.addView(image20);

        final View image21 = getLayoutInflater().inflate(R.layout.image_layout,null,false);
        ImageView img21 = image21.findViewById(R.id.img);
        img21.setImageResource(R.drawable.image_21);
        view.addView(image21);

        final View image22 = getLayoutInflater().inflate(R.layout.image_layout,null,false);
        ImageView img22 = image22.findViewById(R.id.img);
        img22.setImageResource(R.drawable.image_22);
        view.addView(image22);

        final View image23 = getLayoutInflater().inflate(R.layout.image_layout,null,false);
        ImageView img23 = image23.findViewById(R.id.img);
        img23.setImageResource(R.drawable.image_23);
        view.addView(image23);

        final View image24 = getLayoutInflater().inflate(R.layout.image_layout,null,false);
        ImageView img24 = image24.findViewById(R.id.img);
        img24.setImageResource(R.drawable.image_24);
        view.addView(image24);

        final View image25 = getLayoutInflater().inflate(R.layout.image_layout,null,false);
        ImageView img25 = image25.findViewById(R.id.img);
        img25.setImageResource(R.drawable.image_25);
        view.addView(image25);

        final View image26 = getLayoutInflater().inflate(R.layout.image_layout,null,false);
        ImageView img26 = image26.findViewById(R.id.img);
        img26.setImageResource(R.drawable.image_26);
        view.addView(image26);

        final View image27 = getLayoutInflater().inflate(R.layout.image_layout,null,false);
        ImageView img27 = image27.findViewById(R.id.img);
        img27.setImageResource(R.drawable.image_27);
        view.addView(image27);

        final View image28 = getLayoutInflater().inflate(R.layout.image_layout,null,false);
        ImageView img28 = image28.findViewById(R.id.img);
        img28.setImageResource(R.drawable.image_28);
        view.addView(image28);

    }

}