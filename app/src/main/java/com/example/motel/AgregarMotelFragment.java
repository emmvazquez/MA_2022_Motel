package com.example.motel;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.motel.R;

import java.util.HashMap;
import java.util.Map;

public class AgregarMotelFragment extends Fragment {
    EditText nombre, region, municipio, direccion, precios, horarios, servicios, telefono, paginaweb;
    String  snombre, sregion, smunicipio, sdireccion, sprecios, shorarios, sservicios, stelefono, spaginaweb;
    Button registrarmotel;
    ProgressDialog progressDialog;
    String URL = "https://motelesdepuebla.000webhostapp.com/insertarmotel.php";

    public AgregarMotelFragment() {
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
        View view = inflater.inflate(R.layout.fragment_agregar_motel, container, false);

        nombre = view.findViewById(R.id.idNombre);
        region = view.findViewById(R.id.idRegion);
        municipio = view.findViewById(R.id.idMunicipio);
        direccion = view.findViewById(R.id.idDireccion);
        precios = view.findViewById(R.id.idPrecios);
        horarios = view.findViewById(R.id.idHorarios);
        servicios = view.findViewById(R.id.idServicios);
        telefono = view.findViewById(R.id.idTelefono);
        paginaweb = view.findViewById(R.id.idPaginaweb);
        registrarmotel = view.findViewById(R.id.btnRegistrarMotel);

        registrarmotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                snombre= nombre.getText().toString();
                sregion= region.getText().toString();
                smunicipio= municipio.getText().toString();
                sdireccion= direccion.getText().toString();
                sprecios= precios.getText().toString();
                shorarios= horarios.getText().toString();
                sservicios= servicios.getText().toString();
                stelefono= telefono.getText().toString();
                spaginaweb= paginaweb.getText().toString();


                ProgressDialog progressDialog = new ProgressDialog(getActivity());
                progressDialog.setMessage("cargando...");

                if(snombre.isEmpty()){
                    nombre.setError("Ingrese un nombre");
                    nombre.requestFocus();
                    return;
                }
                else if (sdireccion.isEmpty()) {
                    direccion.setError("Ingrese una direccion");
                    direccion.requestFocus();
                    return;
                }
                else if (smunicipio.isEmpty()) {
                    municipio.setError("Ingrese una municipio");
                    municipio.requestFocus();
                    return;
                }
                else if (sregion.isEmpty()){
                    region.setError("Ingrese una region");
                    region.requestFocus();
                    return;
                }
                else if (sprecios.isEmpty()) {
                    precios.setError("Ingrese una sus precios");
                    precios.requestFocus();
                    return;
                }
                else if (shorarios.isEmpty()) {
                    horarios.setError("Ingrese sus horarios");
                    horarios.requestFocus();
                    return;
                }
                else if (sservicios.isEmpty()) {
                    servicios.setError("Ingrese sus servicios");
                    servicios.requestFocus();
                    return;
                }
                else if (stelefono.isEmpty()) {
                    telefono.setError("Ingrese su telefono");
                    telefono.requestFocus();
                    return;
                }
                else if (spaginaweb.isEmpty()) {
                    paginaweb.setError("Ingrese pagina web");
                    paginaweb.requestFocus();
                    return;
                }else{
                    progressDialog.show();
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            if (response.equalsIgnoreCase("datos insertados")) {
                                Toast.makeText(getActivity(), "datos insertados", Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                                onBackPressed();
                            } else {
                                Toast.makeText(getActivity(), response, Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        }
                    }){
                        protected Map<String, String> getParams()throws AuthFailureError{
                            Map<String, String> params = new HashMap<>();
                            params.put("nombre",snombre);
                            params.put("region",sregion);
                            params.put("municipio",smunicipio);
                            params.put("direccion",sdireccion);
                            params.put("precios",sprecios);
                            params.put("horarios",shorarios);
                            params.put("servicios",sservicios);
                            params.put("telefono",stelefono);
                            params.put("paginaweb",spaginaweb);
                            return  params;
                        }
                    };
                    RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
                    requestQueue.add(stringRequest);
                }
            }
        });
        return view;
    }

    public  void onBackPressed(){
        super.getActivity().onBackPressed();

    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}