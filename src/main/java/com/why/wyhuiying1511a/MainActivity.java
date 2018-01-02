package com.why.wyhuiying1511a;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.gson.Gson;
import com.why.wyhuiying1511a.Bean.Title;
import com.why.wyhuiying1511a.Utils.OkHttpUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private CustomBanner banner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        banner = findViewById(R.id.custom_banner);
        //定义联网请求数据的方法
        getNetData();
    }

    private void getNetData() {
        //调用Okhttp工具类，获取联网方法
        OkHttpUtil.doGet("https://www.zhaoapi.cn/ad/getAd", new Callback() {

            private List<Title.DataBean> data;

            //失败的方法
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //当服务器响应成功的时候
                if(response.isSuccessful()){
                    String string = response.body().string();
                    Gson gson=new Gson();
                  final Title title = gson.fromJson(string, Title.class);
                    data = title.getData();

                    //主线程
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                         banner.setImageUrls(data);


                            //设置点击事件
                        banner.setClickListner(new CustomBanner.OnClickLisner() {
                            @Override
                            public void onItemClick(int position) {
                                if(data.get(position).getType()==0){
                                    Intent intent=new Intent(MainActivity.this,WebActivity.class);
                                    intent.putExtra("url",data.get(position).getUrl());
                                    startActivity(intent);
                                }else{
                                    Toast.makeText(MainActivity.this,"我要跳转到商品详情页",Toast.LENGTH_SHORT).show();

                                }

 }
                        });
                        }
                    });
                }
            }
        });
    }
}
