package com.nc.poetry_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.nc.poetry_app.API.API_Interface;
import com.nc.poetry_app.API.ApiClient;
import com.nc.poetry_app.Adaptor.Poetry_Adaptor;
import com.nc.poetry_app.model.Poetry_Model;
import com.nc.poetry_app.model.Response.GetPoetryResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    Poetry_Adaptor poetry_adaptor;
    List<Poetry_Model> list;
    Retrofit retrofit;
    API_Interface apiInterface;

    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list=new ArrayList<>();
        initalization();
        setSupportActionBar(toolbar);
        getdata();
    }

    void initalization()
    {
        retrofit= ApiClient.getClinet();
        apiInterface=retrofit.create(API_Interface.class);
        recyclerView=findViewById(R.id.recyclerview);
        toolbar=findViewById(R.id.toolbar);

    }
    public void getdata()
    {
        apiInterface.getpoetry().enqueue(new Callback<GetPoetryResponse>() {
            @Override
            public void onResponse(Call<GetPoetryResponse> call, Response<GetPoetryResponse> response) {
                try {
                    if(response!=null)
                    {
                        if((response.body().getStatus()).equals("1"))
                        {
                            Toast.makeText(getApplicationContext(),     "if block", Toast.LENGTH_SHORT).show();

                            list=response.body().getData();
                            poetry_adaptor=new Poetry_Adaptor(getApplicationContext(),list);
                            LinearLayoutManager linearLayoutManager =new LinearLayoutManager(getApplicationContext());
                            recyclerView.setAdapter(poetry_adaptor);
                            recyclerView.setLayoutManager(linearLayoutManager);
                            List<Poetry_Model> l=response.body().getData();
                            for(Poetry_Model p:l)
                                System.out.println("=================-=-=-========-=-=-=-=-=-=-=-= "+p.getPoet_name()+"\t"+p.getId()+"\t"+p.getPretry_data()+"\t"+p.getData_time());
                        }
                        else {
                            showDialog(response.body().getMessage());
                        }
                    }

                }catch (Exception e)
                {
                    Log.d("naveen"," "+e.getLocalizedMessage());

                }
            }

            @Override
            public void onFailure(Call<GetPoetryResponse> call, Throwable t) {
                Log.d("naveen"," "+t.getLocalizedMessage());
            }
        });


    }
    public void showDialog(String  s)
    {
        Log.d("naveen"," "+s);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.manu_file,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.add:
                Intent i=new Intent(MainActivity.this,Add_Poetry.class);
                startActivity(i);
                break;
            default:
                return  super.onOptionsItemSelected(item);
        }

        return  true;
    }
}