package com.example.motel;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.motel.FormularioMotelFragment;
import com.example.motel.R;

public class DetallesMotelFragment extends Fragment {

    TextView tv1,tv2,tv3,tv4,tv5,tv6,tv7,tv8,tv9,tv10;
    int position;

    public DetallesMotelFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detalles_motel, container, false);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tv1 = view.findViewById(R.id.tdid);
        tv2 = view.findViewById(R.id.tdnombre);
        tv3 = view.findViewById(R.id.tddireccion);
        tv4 = view.findViewById(R.id.tdmunicipio);
        tv5 = view.findViewById(R.id.tdregion);
        tv6 = view.findViewById(R.id.tdprecios);
        tv7 = view.findViewById(R.id.tdhorarios);
        tv8 = view.findViewById(R.id.tdservicios);
        tv9 = view.findViewById(R.id.tdtelefono);
        tv10 = view.findViewById(R.id.tdpaginaweb);

        Intent intent = getActivity().getIntent();
        position = intent.getExtras().getInt("position");

        tv1.setText("idMotel: "+ FormularioMotelFragment.motelesArrayList.get(position).getIdMotel());
        tv2.setText("nombre: "+ FormularioMotelFragment.motelesArrayList.get(position).getSnombre());
        tv3.setText("direccion: "+ FormularioMotelFragment.motelesArrayList.get(position).getSdireccion());
        tv4.setText("municipio: "+ FormularioMotelFragment.motelesArrayList.get(position).getSmunicipio());
        tv5.setText("region: "+ FormularioMotelFragment.motelesArrayList.get(position).getSregion());
        tv6.setText("precios: "+ FormularioMotelFragment.motelesArrayList.get(position).getSprecios());
        tv7.setText("horarios: "+ FormularioMotelFragment.motelesArrayList.get(position).getShorarios());
        tv8.setText("servicios: "+ FormularioMotelFragment.motelesArrayList.get(position).getSservicios());
        tv9.setText("telefono: "+ FormularioMotelFragment.motelesArrayList.get(position).getStelefono());
        tv10.setText("paginaweb: "+ FormularioMotelFragment.motelesArrayList.get(position).getSpaginaweb());

    }
}