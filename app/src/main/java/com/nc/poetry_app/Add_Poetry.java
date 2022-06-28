package com.nc.poetry_app;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.nc.poetry_app.API.API_Interface;
import com.nc.poetry_app.API.ApiClient;
import com.nc.poetry_app.model.Poetry_Model;
import com.nc.poetry_app.model.Response.DeletePoetry;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Add_Poetry extends AppCompatActivity {


    Toolbar toolbar;
    EditText poeryName, poertydata;
    Button submit;
    API_Interface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_poetry);
        initialization();
        setToolbar();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String poetry_data = poertydata.getText().toString();
                String poet_name = poeryName.getText().toString();
                if (poetry_data == "") {
                    poertydata.setError("Please Fill!");
                } else if (poet_name == "") {
                    poertydata.setError("Fill Poet Name");
                } else {
                    call_API(poet_name,poetry_data);
//                    AlertDialog.Builder builder=new AlertDialog.Builder(getApplicationContext());
//                    builder.setMessage("Please Enter Valied Input");
//                    builder.setTitle("Wrong Input");
//                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialogInterface, int i) {
//
//                        }
//                    });
//                    AlertDialog alertDialog=new AlertDialog();
//                    alertDialog.show();


                }
            }
        });
    }

    private void initialization() {

        toolbar = findViewById(R.id.add_poetry_toobar);
        poertydata = findViewById(R.id.add_poetry_data);
        poeryName = findViewById(R.id.poet_name);
        submit = findViewById(R.id.submit_button);
        Retrofit retrofit = ApiClient.getClinet();
        apiInterface = retrofit.create(API_Interface.class);
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
    private  void call_API(String Poet_name,String PoetryData)
    {
        apiInterface.addpoetry(Poet_name,PoetryData).enqueue(new Callback<DeletePoetry>() {
            @Override
            public void onResponse(Call<DeletePoetry> call, Response<DeletePoetry> response) {
                try {
                    if(response.body().getStatus().equals("1"))
                    {
                        Toast.makeText(getApplicationContext(), "Data Inserted", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Log.i("naveen","data is Not inserted");
                    }
                }catch (Exception e)
                {
                    Log.e("naveen",e.getLocalizedMessage());
                }

            }

            @Override
            public void onFailure(Call<DeletePoetry> call, Throwable t) {
                Log.e("naveen",t.getLocalizedMessage());
            }
        });
    }
}