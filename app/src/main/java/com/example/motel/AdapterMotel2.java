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

public class AdapterMotel2 extends RecyclerView.Adapter<AdapterMotel2.ViewHolder> {
    ArrayList<Moteles> ListaMoteles2;
    Context context;

    public AdapterMotel2(ArrayList<Moteles> listaMoteles2, Context context) {
        this.ListaMoteles2 = listaMoteles2;
        this.context=context;
    }

    @NonNull
    @Override
    public AdapterMotel2.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.list_moteles, null, false);
        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(layoutParams);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterMotel2.ViewHolder holder, int position) {
        holder.tvNombre.setText(ListaMoteles2.get(position).getSnombre().toString());
        
        if (ListaMoteles2.get(position).getSimagen()!=null){
            cargarImagenWebService(ListaMoteles2.get(position).getSimagen(),holder);
        }else {
            holder.imImagen.setImageResource(R.drawable.teziutlan);
        }

    }

    private void cargarImagenWebService(String simagen, final ViewHolder holder) {
        String urlImagen=simagen;
        urlImagen=urlImagen.replace(" ","%20");
        ImageRequest imageRequest = new ImageRequest(urlImagen, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                holder.imImagen.setImageBitmap(response);
            }
        }, 0, 0, ImageView.ScaleType.CENTER, null, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context,"Error al cargar la imagen",Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(imageRequest);
    }


    @Override
    public int getItemCount() {
        return ListaMoteles2.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imImagen;
        TextView tvNombre;

        public ViewHolder(@NonNull View itemView){
            super((itemView));
            imImagen =(ImageView) itemView.findViewById(R.id.ivimagen);
            tvNombre =(TextView) itemView.findViewById(R.id.txtnombre);

        }
    }
}
