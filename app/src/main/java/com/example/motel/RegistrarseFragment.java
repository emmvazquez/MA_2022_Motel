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

public class RegistrarseFragment extends Fragment {
    EditText nombrecompleto, usuario, clave, correoelectronico, telefono;
    Button RegistrarUsuario;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_registrarse, container, false);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        nombrecompleto = view.findViewById(R.id.idNombreCompleto);
        usuario = view.findViewById(R.id.idUsuario);
        clave = view.findViewById(R.id.idClave);
        correoelectronico = view.findViewById(R.id.idCorreoelectronico);
        telefono = view.findViewById(R.id.idTelefono);
        RegistrarUsuario = view.findViewById(R.id.btnRegistrarUsuario);

        RequestQueue queue = Volley.newRequestQueue(getActivity());

        RegistrarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sNombrecompleto = nombrecompleto.getText().toString();
                String sUsuario = usuario.getText().toString();
                String sClave = clave.getText().toString();
                String sCorreoelectronico = correoelectronico.getText().toString();
                String sTelefono = telefono.getText().toString();

                String url = "http://192.168.8.92/motel/apiregistrousuario.php?nombrecompleto="
                        + sNombrecompleto + "&usuario=" + sUsuario + "&clave=" + sClave + "&correoelectronico="
                        + sCorreoelectronico + "&telefono=" + sTelefono;
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
                                Toast.LENGTH_LONG). show();
                    }
                }
                );
                queue.add(jsonObjectRequest);
            }
        });

    }
}