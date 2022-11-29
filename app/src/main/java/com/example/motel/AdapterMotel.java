package com.example.motel;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;

public class AdapterMotel extends RecyclerView.Adapter<AdapterMotel.ViewHolder> implements View.OnClickListener {
    ArrayList<Moteles>ListaMoteles;
    Context context;
    private  View.OnClickListener listener;

    public AdapterMotel(ArrayList<Moteles> listaMoteles, Context context) {
        this.ListaMoteles = listaMoteles;
        this.context=context;
    }

    @NonNull
    @Override
    public AdapterMotel.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.list_recycler_moteles, null, false);
        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(layoutParams);
        return new ViewHolder(view);
    }

    public void setOnclickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterMotel.ViewHolder holder, int position) {
        holder.tvnombre.setText(ListaMoteles.get(position).getSnombre());
        holder.tvmunicipio.setText(ListaMoteles.get(position).getSmunicipio());
        holder.tvregion.setText(ListaMoteles.get(position).getSregion());

        if (ListaMoteles.get(position).getSimagen()!=null){
            cargarImagenWebService2(ListaMoteles.get(position).getSimagen(),holder);
        }else {
            holder.imImagenPublica.setImageResource(R.drawable.teziutlan);
        }


    }

    private void cargarImagenWebService2(String simagen, final AdapterMotel.ViewHolder holder) {
        String urlImagen=simagen;
        urlImagen=urlImagen.replace(" ","%20");
        ImageRequest imageRequest = new ImageRequest(urlImagen, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                holder.imImagenPublica.setImageBitmap(response);
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
        return ListaMoteles.size();
    }

    @Override
    public void onClick(View view) {
        if (listener!=null){
            listener.onClick(view);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imImagenPublica;
        TextView tvnombre;
        TextView tvmunicipio;
        TextView tvregion;

        public ViewHolder(@NonNull View itemView){
            super((itemView));
            imImagenPublica = (ImageView) itemView.findViewById(R.id.ivimagenpublica);
            tvnombre =(TextView) itemView.findViewById(R.id.txtNombre);
            tvmunicipio =(TextView) itemView.findViewById(R.id.txtMunicipio);
            tvregion =(TextView) itemView.findViewById(R.id.txtRegion);

        }
    }
}

