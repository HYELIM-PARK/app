package com.example.sample;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NewsActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private String[] mDataset ={"1","2"};
    RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        mRecyclerView = findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // RequestQueue 초기화
        queue = Volley.newRequestQueue(this);
        getNews();


        //1. 화면 로딩 -> 뉴스 정보 받아온다
        //2. 정보 -> 어댑터에 넘겨준다
        //3. 어댑터 -> 세팅

    }

    public void getNews() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://newsapi.org/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiInterface apiInterface = retrofit.create(ApiInterface.class);

        Call<ResponseBody> call = apiInterface.getData("kr", "key");
        call.enqueue(new Callback<ResponseBody>() {

            @Override
            public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
//                Log.d("NEWS", response.toString());

                if (!response.isSuccessful()) {
                    Log.d("error", "-----------error-------------");
                    return;
                }
                try {
                    String result = response.body().string();

                    JSONObject jsonObj = new JSONObject(result);

                    // "articles" 라는 키값을 가진 값 ( -> '배열' 가져오기)
                    JSONArray arrayArticles =  jsonObj.getJSONArray("articles");

                    // response -> NewsData Class 분류 (원하는 데이터만..)
                    List<NewsData> news = new ArrayList<>();

                    for (int i = 0, j = arrayArticles.length(); i < j; i++) {
                        JSONObject obj = arrayArticles.getJSONObject(i);

                        Log.d("NEWS", obj.toString());

                        NewsData newsData = new NewsData();
                        newsData.setTitle(obj.getString("title"));
                        newsData.setUrlToImage(obj.getString("urlToImage"));
                        newsData.setContent(obj.getString("content"));
                        news.add(newsData);

                    }

                    // 정보를 어댑터로 넘겨줌
                    mAdapter = new MyAdapter(news, NewsActivity.this);
                    mRecyclerView.setAdapter(mAdapter);


                } catch (JSONException | IOException e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });



        // RequestQueue에 요청 추가
//        queue.add(stringRequest);

    }

}