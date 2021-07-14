package com.example.sample;

import android.content.Context;
import android.media.Image;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private List<NewsData> mDataset;

    //  한 줄에 들어가는 항목화면 파일 연결하는 요소
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView TextView_title;
        public TextView TextView_content;
        public SimpleDraweeView ImageView_title;

        public MyViewHolder(View v) {
            super(v);
            //부모 xml 뷰가 누군지 알 수 없으므로 명확하게 row_news.xml 이 부모라고 지정해줌
            TextView_title   = v.findViewById(R.id.TextView_title);
            TextView_content = v.findViewById(R.id.TextView_content);
            ImageView_title  =  v.findViewById(R.id.ImageView_title);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(List<NewsData> myDataset, Context context) {
        mDataset = myDataset;
        Fresco.initialize(context);
    }

    // Create new views (invoked by the layout manager)
    //  onCreateViewHolder (row_news.xml) -> 항목화면 파일 연결
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        // create a new view
        //  inflate: 특정 컴포넌트(리사이클러 뷰)의 특정 항목의 레이아웃을 바꿔줌
        LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_news, parent, false);

        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    // Replace the content of a view (invoked by the layout manager)
    //  반복된 MyViewHolder 내용(값)을 대체(세팅)해줌
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        NewsData news = mDataset.get(position);
        holder.TextView_title.setText(news.getTitle());
        holder.TextView_content.setText(news.getDescription());

        Uri uri = Uri.parse(news.getUrlToImage());
        holder.ImageView_title.setImageURI(uri);
    }

    // Return the size of your dataset (invoked by the layout manager)
    //  MyViewHolder 를 length 만큼 반복
    @Override
    public int getItemCount() {
        return mDataset == null ? 0 : mDataset.size();
    }

}
