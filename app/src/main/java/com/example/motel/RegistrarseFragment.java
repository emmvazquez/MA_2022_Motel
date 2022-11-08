package com.example.motel;

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
    String sNombrecompleto,sUsuario, sClave,sCorreoelectronico,sTelefono;

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
                 sNombrecompleto = nombrecompleto.getText().toString();
                 sUsuario = usuario.getText().toString();
                 sClave = clave.getText().toString();
                 sCorreoelectronico = correoelectronico.getText().toString();
                 sTelefono = telefono.getText().toString();

                if(sNombrecompleto.isEmpty()){
                    nombrecompleto.setError(getString(R.string.error_campo_obligatorio));
                    nombrecompleto.requestFocus();
                    Toast.makeText(getActivity(),"Ingresa tu nombre completo", Toast.LENGTH_LONG).show();
                }else{
                    if (sUsuario.isEmpty()){
                        usuario.setError(getString(R.string.error_campo_obligatorio));
                        usuario.requestFocus();
                        Toast.makeText(getActivity(),"Ingresa un usuario", Toast.LENGTH_LONG).show();
                    }else{
                        if (sClave.isEmpty()){
                            clave.setError(getString(R.string.error_campo_obligatorio));
                            clave.requestFocus();
                            Toast.makeText(getActivity(),"Ingresa una contraseña", Toast.LENGTH_LONG).show();
                        }else{
                            if (sCorreoelectronico.isEmpty()){
                                correoelectronico.setError(getString(R.string.error_campo_obligatorio));
                                correoelectronico.requestFocus();
                                Toast.makeText(getActivity(),"Ingresa un correo electronico", Toast.LENGTH_LONG).show();
                            }else{
                                if (sTelefono.isEmpty()){
                                    telefono.setError(getString(R.string.error_campo_obligatorio));
                                   telefono.requestFocus();
                                    Toast.makeText(getActivity(),"Ingresa un numero de telefono", Toast.LENGTH_LONG).show();
                                }else{
                                    String url = "https://motelesdepuebla.000webhostapp.com/apiregistrousuario.php?nombrecompleto="
                                            + sNombrecompleto + "&usuario=" + sUsuario + "&clave=" + sClave + "&correoelectronico="
                                            + sCorreoelectronico + "&telefono=" + sTelefono;
                                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                                            Request.Method.GET, url, null,
                                            new Response.Listener<JSONObject>() {
                                                @Override
                                                public void onResponse(JSONObject response) {
                                                    Toast.makeText(view.getContext(), response.toString(),
                                                            Toast.LENGTH_LONG).show();
                                                    Navigation.findNavController(view).navigate(R.id.action_registrarseFragment_to_loginFragment);
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
                            }
                        }
                    }
                }
            }
        });

    }
}
