package com.example.motel;

import android.app.ProgressDialog;
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

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.motel.R;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegistrarseFragment extends Fragment {
    EditText nombrecompleto, usuario, clave, correoelectronico, telefono;
    Button RegistrarUsuario;
    String sNombrecompleto,sUsuario, sClave,sCorreoelectronico,sTelefono;
    String URL = "https://motelesdepuebla.000webhostapp.com/registrarusuario.php";

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
                ProgressDialog progressDialog = new ProgressDialog(getActivity());
                progressDialog.setMessage("cargando...");

                if(sNombrecompleto.isEmpty()){
                    nombrecompleto.setError(getString(R.string.error_campo_obligatorio));
                    nombrecompleto.requestFocus();
                   return;
                }else if(sUsuario.isEmpty()){
                    usuario.setError(getString(R.string.error_campo_obligatorio));
                    usuario.requestFocus();
                    return;
                }else if(sClave.isEmpty()){
                    clave.setError(getString(R.string.error_campo_obligatorio));
                    clave.requestFocus();
                    return;
                }else if(sCorreoelectronico.isEmpty()){
                    correoelectronico.setError(getString(R.string.error_campo_obligatorio));
                    correoelectronico.requestFocus();
                   return;
                }else if (sTelefono.isEmpty()){
                    telefono.setError(getString(R.string.error_campo_obligatorio));
                    telefono.requestFocus();
                    return;
                }else{
                                    progressDialog.show();
                                    StringRequest request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                                        @Override
                                        public void onResponse(String response) {
                                            if (response.equalsIgnoreCase("datos registrados")) {
                                                Toast.makeText(getActivity(), "datos registrados", Toast.LENGTH_SHORT).show();
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
                                    }
                                    ){
                                        protected Map<String, String> getParams() throws AuthFailureError{
                                            Map<String, String> params = new HashMap<String, String>();

                                            params.put("nombrecompleto",sNombrecompleto);
                                            params.put("usuario",sUsuario);
                                            params.put("clave",sClave);
                                            params.put("correoelectronico",sCorreoelectronico);
                                            params.put("telefono",sTelefono);

                                            return params;
                                        }
                                    };
                                    RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
                                    requestQueue.add(request);
                                }
                            }
        });

    }
    public  void onBackPressed(){
        super.getActivity().onBackPressed();
    }
}
