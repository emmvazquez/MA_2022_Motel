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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detalles_motel, container, false);

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

        return view;
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}