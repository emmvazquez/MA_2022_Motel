package com.example.motel;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;

public class AdapterMotel2 extends RecyclerView.Adapter<AdapterMotel2.ViewHolder> implements View.OnClickListener{
    ArrayList<Moteles> ListaMoteles2;
    private View.OnClickListener listener;

    public AdapterMotel2(ArrayList<Moteles> listaMoteles2) {
        ListaMoteles2 = listaMoteles2;

    }

    @NonNull
    @Override
    public AdapterMotel2.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.list_moteles, null, false);
        view.setOnClickListener(this);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterMotel2.ViewHolder holder, int position) {
        holder.tvNombre.setText(ListaMoteles2.get(position).getSnombre());

    }



    @Override
    public int getItemCount() {
        return ListaMoteles2.size();
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener=listener;
    }

    @Override
    public void onClick(View view) {
        if(listener!=null){
            listener.onClick(view);
        }

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNombre;

        public ViewHolder(@NonNull View itemView){
            super((itemView));
            tvNombre =(TextView) itemView.findViewById(R.id.txtnombre);

        }
    }
}
