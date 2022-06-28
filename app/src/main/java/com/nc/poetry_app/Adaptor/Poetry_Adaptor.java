package com.nc.poetry_app.Adaptor;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.nc.poetry_app.API.API_Interface;
import com.nc.poetry_app.API.ApiClient;
import com.nc.poetry_app.R;
import com.nc.poetry_app.Update_Poetry;
import com.nc.poetry_app.model.Poetry_Model;
import com.nc.poetry_app.model.Response.DeletePoetry;

import java.util.List;
import java.util.zip.Inflater;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Poetry_Adaptor extends RecyclerView.Adapter<Poetry_Adaptor.viewHolder> {
    Context context;
    List<Poetry_Model> list;
    API_Interface apiInterface;

    public Poetry_Adaptor(Context context, List<Poetry_Model> list) {
        this.context = context;
        this.list = list;
        Retrofit retrofit = ApiClient.getClinet();
        apiInterface = retrofit.create(API_Interface.class);
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.poerty_list_design, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        int n=position;
        Poetry_Model object = list.get(position);
        holder.poetName.setText(object.getPoet_name());
        holder.poetry.setText(object.getPretry_data());
        holder.data_time.setText(object.getData_time());


        holder.deletebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteData(String.valueOf(object.getId()),n);
            }
        });
        holder.updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(context, Update_Poetry.class);
                intent.putExtra("id",String.valueOf(object.getId()));
                intent.putExtra("data",object.getPretry_data());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView poetName, poetry, data_time;
        AppCompatButton updateBtn, deletebtn;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            poetName = itemView.findViewById(R.id.text_view_poet_name);
            poetry = itemView.findViewById(R.id.text_view_poerty_data);
            data_time = itemView.findViewById(R.id.text_view_poetry_date);


            updateBtn = itemView.findViewById(R.id.poetry_updata);
            deletebtn = itemView.findViewById(R.id.poetry_delete);


        }
    }

    private void deleteData(String id,int position) {
        apiInterface.deletePoetry(id).enqueue(new Callback<DeletePoetry>() {
            @Override
            public void onResponse(Call<DeletePoetry> call, Response<DeletePoetry> response) {

                if (response.body() != null) {
                    Toast.makeText(context, "data deleted", Toast.LENGTH_SHORT).show();
                } else {
                    Log.d("naveen", "resoponse is null");
                }

                if (response.body().getStatus().equals("1")) {
                    list.remove(position);
                    notifyDataSetChanged();

                }


            }

            @Override
            public void onFailure(Call<DeletePoetry> call, Throwable t) {

                Log.d("naveen", "In poetry _Adaptor DeleteData method " + t.getLocalizedMessage());

            }
        });
    }





}
