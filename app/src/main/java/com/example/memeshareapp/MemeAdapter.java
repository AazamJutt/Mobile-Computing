package com.example.memeshareapp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class MemeAdapter extends RecyclerView.Adapter<MemeAdapter.MemeViewHolder> {
    List<Meme> memes;
    Activity context;

    public MemeAdapter(Activity context, List<Meme> memes) {
        this.memes = memes;
        this.context = context;
    }


    @NonNull
    @Override
    public MemeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.meme_post, parent, false);
        return new MemeViewHolder(view);
    }

    @SuppressLint("SetWorldReadable")
    @Override
    public void onBindViewHolder(@NonNull MemeViewHolder holder, int position) {
        Meme currMeme = memes.get(position);
        holder.setAuthor(currMeme.getAuthor());
        holder.setCaption(currMeme.getCaption());
        holder.setImage(currMeme.getUrl());

        Glide.with(context).load(currMeme.getUrl()).diskCacheStrategy(DiskCacheStrategy.ALL)
                .dontAnimate().into(holder.meme);

        holder.view.findViewById(R.id.launchBtn).setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(currMeme.getPostLink()));
            context.startActivity(intent);
        });
        holder.view.findViewById(R.id.shareBtn).setOnClickListener(v -> {
            Bitmap bitmap =getBitmapFromView(holder.meme);
            try {
                File file = new File(context.getExternalCacheDir(),"logicchip.jpg");
                FileOutputStream fOut = new FileOutputStream(file);
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, fOut);
                fOut.flush();
                fOut.close();
                file.setReadable(true, false);
                final Intent intent = new Intent(android.content.Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_TEXT, currMeme.caption);
                intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file));
                intent.setType("image/jpg");
                context.startActivity(intent);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public int getItemCount() {
        return memes.size();
    }


    private Bitmap getBitmapFromView(View view) {
        Bitmap returnedBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(),Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(returnedBitmap);
        Drawable bgDrawable =view.getBackground();
        if (bgDrawable!=null) {
            bgDrawable.draw(canvas);
        }   else{
            canvas.drawColor(Color.WHITE);
        }
        view.draw(canvas);
        return returnedBitmap;
    }

    public class MemeViewHolder extends RecyclerView.ViewHolder {
        TextView author;
        TextView caption;
        ImageView meme;
        View view;
        public MemeViewHolder(@NonNull View memeView) {
            super(memeView);
            this.view = memeView;
            meme = view.findViewById(R.id.memeImg);
            // Like button function
            this.view.findViewById(R.id.likeBtn).setOnClickListener(v -> {
                view.findViewById(R.id.likeBtn).setBackgroundResource(R.drawable.ic_round_thumb_up_24);
            });
        }
        public void setAuthor(String author){
            this.author = view.findViewById(R.id.author);
            this.author.setText(author);
        }
        public void setCaption(String caption){
            this.caption = view.findViewById(R.id.caption);
            this.caption.setText(caption);
        }
        public void setImage(String url){
            this.meme = view.findViewById(R.id.memeImg);
        }
    }
}
