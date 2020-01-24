package com.utkarsh.recyclerview;

import android.graphics.Bitmap;

class Example {

    private Bitmap bitmap;
    private String data,time;

    Example(String data,String time,Bitmap bitmap) {
        this.data = data;
        this.time = time;
        this.bitmap = bitmap;
    }

    String getData() {
        return data;
    }
    String getTime() {
        return time;
    }
    Bitmap getBitmap() {
        return bitmap;
    }
}
