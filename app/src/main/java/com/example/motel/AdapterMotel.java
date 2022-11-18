package com.example.motel;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class AdapterMotel extends RecyclerView.Adapter<AdapterMotel.ViewHolder> {
    ArrayList<Moteles>ListaMoteles;
    public AdapterMotel(ArrayList<Moteles> listaMoteles) {
        ListaMoteles = listaMoteles;
    }
    @NonNull
    @Override
    public AdapterMotel.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.list_recycler_moteles, null, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull AdapterMotel.ViewHolder holder, int position) {
        holder.tvnombre.setText(ListaMoteles.get(position).getSnombre());
        holder.tvmunicipio.setText(ListaMoteles.get(position).getSmunicipio());
        holder.tvregion.setText(ListaMoteles.get(position).getSregion());

    }
    @Override
    public int getItemCount() {
        return ListaMoteles.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvnombre;
        TextView tvmunicipio;
        TextView tvregion;

        public ViewHolder(@NonNull View itemView){
            super((itemView));
            tvnombre =(TextView) itemView.findViewById(R.id.txtNombre);
            tvmunicipio =(TextView) itemView.findViewById(R.id.txtMunicipio);
            tvregion =(TextView) itemView.findViewById(R.id.txtRegion);

        }
    }
}

