package com.nc.poetry_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.nc.poetry_app.API.API_Interface;
import com.nc.poetry_app.API.ApiClient;
import com.nc.poetry_app.model.Response.DeletePoetry;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Update_Poetry extends AppCompatActivity {
        Toolbar toolbar;
        Button update;
        EditText poerty_data;
        String id;
        String data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_poetry);

        initialisation();
        setToolbar();
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data=poerty_data.getText().toString();
                    if(data=="")
                    {
                        poerty_data.setError("Fill all data");
                    }
                    else
                    updatepoetry();
                }


        });
    }

    private void updatepoetry() {
        id=getIntent().getStringExtra("id");

        Retrofit retrofit= ApiClient.getClinet();
        API_Interface apiInterface =retrofit.create(API_Interface.class);
        apiInterface.updatePoetry(id,data).enqueue(new Callback<DeletePoetry>() {
            @Override
            public void onResponse(Call<DeletePoetry> call, Response<DeletePoetry> response) {
                try {
                    if(response.body().getStatus().equals("1"))
                    {
                        Toast.makeText(getApplicationContext(), "Data Updated", Toast.LENGTH_SHORT).show();
                    }

                }catch (Exception e)
                {
                    Log.e("naveen","Error On Update Poetry"+e.getLocalizedMessage());
                }

            }

            @Override
            public void onFailure(Call<DeletePoetry> call, Throwable t) {
                Log.d("naveen",""+t.getLocalizedMessage());
                System.out.println("------------------------"+t.getLocalizedMessage());
            }
        });
    }

    private void initialisation() {
        toolbar=findViewById(R.id.toolbar_Update_poetry);
        update=findViewById(R.id.update_poerty_data_btn);
        poerty_data=findViewById(R.id.update_poerty_input_data);
        poerty_data.setText(getIntent().getStringExtra("data"));


    }
    void setToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}