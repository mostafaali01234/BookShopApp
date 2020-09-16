package com.example.mostafasalem.pro;


import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class BookAdapter extends BaseAdapter {

    Context context;
    ArrayList<Books> arrayList;

    public BookAdapter(Context context, ArrayList<Books> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }


    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return arrayList.indexOf(getItem(i));
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        Books book = arrayList.get(i);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (view == null) {
            view = inflater.inflate(R.layout.model, null);
        }
        final TextView title = view.findViewById(R.id.title);
        TextView price = view.findViewById(R.id.price);
        TextView sub_title = view.findViewById(R.id.sub_title);
        ImageView imageView = view.findViewById(R.id.img);
       // Button btn = view.findViewById(R.id.addCart);



        title.setTextColor(Color.WHITE);
        sub_title.setTextColor(Color.WHITE);

        title.setText(arrayList.get(i).getTitle());
        price.setText(arrayList.get(i).getPrice());
        sub_title.setText(arrayList.get(i).getSubtitle());
        Picasso.with(context).load(book.getImgid()).into(imageView);

        /*btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipboardManager clipboardManager =(ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clipData = ClipData.newPlainText("title",title.getText().toString());
                clipboardManager.setPrimaryClip(clipData);
            }
        });*/


        return view;


    }


    private static class ImageRcquest extends AsyncTask<String, Void, Bitmap> {
        private ImageView imageView;

        public ImageRcquest(ImageView imageView) {
            this.imageView = imageView;
        }


        @Override
        protected Bitmap doInBackground(String... strings) {
            String urldisplay = strings[0];
            Bitmap mIcon = null;
            try {
                InputStream inputStream = new java.net.URL(urldisplay).openStream();
                mIcon = BitmapFactory.decodeStream(inputStream);
            } catch (IOException e) {
                return null;
            }
            return mIcon;

        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            imageView.setImageBitmap(bitmap);
        }
    }
}