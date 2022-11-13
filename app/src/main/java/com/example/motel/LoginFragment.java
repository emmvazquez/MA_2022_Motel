package com.example.motel;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.text.TextUtils;
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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.motel.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginFragment extends Fragment {
    EditText etusuario, etclave;
    String sUsuario,sClave;
    Button btniniciarsesion, btnregistrate;
    ProgressDialog progressDialog;
    RequestQueue requestQueue;
    String HttpURI = "https://motelesdepuebla.000webhostapp.com/apiiniciarsesion.php";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        etusuario = (EditText) view.findViewById(R.id.idetUsuario);
        etclave = (EditText) view.findViewById(R.id.idetClave);
        btniniciarsesion = (Button) view.findViewById(R.id.btniniciarsesion);
        btnregistrate = (Button) view.findViewById(R.id.btnregistrousuarios);

        requestQueue = Volley.newRequestQueue(getActivity());
        progressDialog = new ProgressDialog(getActivity());

        btniniciarsesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sUsuario = etusuario.getText().toString();
                sClave = etclave.getText().toString();

                if(sUsuario.isEmpty()){
                    etusuario.setError(getString(R.string.error_campo_obligatorio));
                    etusuario.requestFocus();
                } else{
                    if (sClave.isEmpty()){
                        etclave.setError(getString(R.string.error_campo_obligatorio));
                        etclave.requestFocus();
                    }else{
                        progressDialog.setMessage("Procesando...");
                        progressDialog.show();
                        StringRequest stringRequest = new StringRequest(Request.Method.POST, HttpURI,
                                new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String serverResponse) {
                                        progressDialog.dismiss();
                                        try {
                                            JSONObject obj = new JSONObject(serverResponse);
                                            Boolean error = obj.getBoolean("error");
                                            String mensaje = obj.getString("mensaje");

                                            if (error == true)
                                                Toast.makeText(getActivity(), mensaje,
                                                        Toast.LENGTH_SHORT).show();
                                            else {
                                                Toast.makeText(getActivity(), "Acceso correcto",
                                                        Toast.LENGTH_SHORT).show();
                                                Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_formularioMotelFragment);
                                            }
                                        }catch (JSONException e){
                                            e.printStackTrace();
                                        }
                                    }
                                },
                                new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        progressDialog.dismiss();
                                        Toast.makeText(getActivity(),error.toString(),
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }){
                            protected Map<String,String> getParams(){
                                Map<String,String> parametros = new HashMap<>();
                                parametros.put("usuario", sUsuario);
                                parametros.put("clave", sClave);
                                parametros.put("opcion","login");
                                return parametros;
                            }
                        };
                        requestQueue.add(stringRequest);

                    }
                }

            }
        });

        btnregistrate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_registrarseFragment);
            }
        });
    }
}