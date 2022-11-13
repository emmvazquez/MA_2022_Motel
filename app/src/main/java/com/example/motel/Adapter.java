package com.example.motel;

import static com.example.motel.R.*;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.motel.Moteles;
import com.example.motel.R;

import java.util.List;

public class Adapter extends ArrayAdapter<Moteles> {

    Context context;
    List<Moteles> arraymoteles;

    public Adapter(@NonNull Context context, List<Moteles>arraymoteles) {

        super(context, R.layout.list_moteles,arraymoteles);
        this.context = context;
        this.arraymoteles = arraymoteles;
    }

    @NonNull
    @Nullable
    public View getView(int position, @Nullable  View convertView, @NonNull  ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(layout.list_moteles,null,true);

        TextView tvId = view.findViewById(id.txtid);
        TextView tvNombre = view.findViewById(id.txtnombre);

        tvId.setText(arraymoteles.get(position).getIdMotel());
        tvNombre.setText(arraymoteles.get(position).getSnombre());

        return view;
    }

}
