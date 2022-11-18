package com.example.motel;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class AdapterMotel2 extends RecyclerView.Adapter<AdapterMotel2.ViewHolder> {
    ArrayList<Moteles> ListaMoteles2;
    public AdapterMotel2(ArrayList<Moteles> listaMoteles2) {
        ListaMoteles2 = listaMoteles2;
    }
    @NonNull
    @Override
    public AdapterMotel2.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.list_moteles, null, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterMotel2.ViewHolder holder, int position) {
        //holder.tvId.setText(ListaMoteles2.get(position).getIdMotel());
        holder.tvNombre.setText(ListaMoteles2.get(position).getSnombre());
    }

    @Override
    public int getItemCount() {
        return ListaMoteles2.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvId;
        TextView tvNombre;

        public ViewHolder(@NonNull View itemView){
            super((itemView));
            //tvId =(TextView) itemView.findViewById(R.id.txtid);
            tvNombre =(TextView) itemView.findViewById(R.id.txtnombre);
        }
    }
}
