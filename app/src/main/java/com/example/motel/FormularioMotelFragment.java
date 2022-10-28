package com.example.motel;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;


public class FormularioMotelFragment extends Fragment {
    EditText nombre, region, municipio, direccion, precios, horarios, servicios, telefono, paginaweb;
    Button RegistrarMotel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_formulario_motel, container, false);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        nombre = view.findViewById(R.id.idNombre);
        region = view.findViewById(R.id.idRegion);
        municipio = view.findViewById(R.id.idMunicipio);
        direccion = view.findViewById(R.id.idDireccion);
        precios = view.findViewById(R.id.idPrecios);
        horarios = view.findViewById(R.id.idHorarios);
        servicios = view.findViewById(R.id.idServicios);
        telefono = view.findViewById(R.id.idTelefono);
        paginaweb = view.findViewById(R.id.idPaginaweb);
        RegistrarMotel = view.findViewById(R.id.btnRegistrarMotel);

        RequestQueue queue = Volley.newRequestQueue(getActivity());

        RegistrarMotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sNombre = nombre.getText().toString();
                String sRegion = region.getText().toString();
                String sMunicipio = municipio.getText().toString();
                String sDireccion = direccion.getText().toString();
                String sPrecios = precios.getText().toString();
                String sHorarios = horarios.getText().toString();
                String sServicios = servicios.getText().toString();
                String sTelefono = telefono.getText().toString();
                String sPaginaweb = paginaweb.getText().toString();

                String url = "http://192.168.8.92/motel/apiregistromotel.php?nombre="
                        +sNombre+"&region="+sRegion+"&municipio="+sMunicipio+"&direccion="
                        +sDireccion+"&precios="+sPrecios+"&horarios="+sHorarios+"&servicios="
                        +sServicios+"&telefono="+sTelefono+"&paginaweb="+sPaginaweb;

                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                        Request.Method.GET, url, null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                Toast.makeText(view.getContext(), response.toString(),
                                        Toast.LENGTH_LONG).show();
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(view.getContext(),error.toString(),
                                Toast.LENGTH_LONG).show();
                    }
                }
                );
                queue.add(jsonObjectRequest);
            }
        });

    }
}