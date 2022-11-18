package com.example.motel;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.strictmode.WebViewMethodCalledOnWrongThreadViolation;
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

import java.util.HashMap;
import java.util.Map;


public class EditarMotelFragment extends Fragment {

    EditText eId,eNombre, eDireccion, eMunicipio, eRegion, ePrecios, eHorarios, eServicios, eTelefono, ePaginaweb;
    private int position;
    Button ActualizarMotel;
    String URL ="https://motelesdepuebla.000webhostapp.com/actualizarmotel.php" ;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_editar_motel, container, false);

        eId = view.findViewById(R.id.idID);
        eNombre = view.findViewById(R.id.idNombre);
        eDireccion = view.findViewById(R.id.idDireccion);
        eMunicipio = view.findViewById(R.id.idMunicipio);
        eRegion = view.findViewById(R.id.idRegion);
        ePrecios = view.findViewById(R.id.idPrecios);
        eHorarios = view.findViewById(R.id.idHorarios);
        eServicios = view.findViewById(R.id.idServicios);
        eTelefono = view.findViewById(R.id.idTelefono);
        ePaginaweb = view.findViewById(R.id.idPaginaweb);
        ActualizarMotel = view.findViewById(R.id.btnActualizarMotel);

        Intent intent = getActivity().getIntent();
        position = intent.getExtras().getInt("position");

        /*eId.setText(FormularioMotelFragment.motelesArrayList.get(position).getIdMotel());
        eNombre.setText(FormularioMotelFragment.motelesArrayList.get(position).getSnombre());
        eDireccion.setText(FormularioMotelFragment.motelesArrayList.get(position).getSdireccion());
        eMunicipio.setText(FormularioMotelFragment.motelesArrayList.get(position).getSmunicipio());
        eRegion.setText(FormularioMotelFragment.motelesArrayList.get(position).getSregion());
        ePrecios.setText(FormularioMotelFragment.motelesArrayList.get(position).getSprecios());
        eHorarios.setText(FormularioMotelFragment.motelesArrayList.get(position).getShorarios());
        eServicios.setText(FormularioMotelFragment.motelesArrayList.get(position).getSservicios());
        eTelefono.setText(FormularioMotelFragment.motelesArrayList.get(position).getStelefono());
        ePaginaweb.setText(FormularioMotelFragment.motelesArrayList.get(position).getSpaginaweb());*/

        ActualizarMotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String id = eId.getText().toString().trim();
                final String nombre = eNombre.getText().toString().trim();
                final String direccion = eDireccion.getText().toString().trim();
                final String municipio = eMunicipio.getText().toString().trim();
                final String region = eRegion.getText().toString().trim();
                final String precios = ePrecios.getText().toString().trim();
                final String horarios = eHorarios.getText().toString().trim();
                final String servicios = eServicios.getText().toString().trim();
                final String telefono = eTelefono.getText().toString().trim();
                final String paginaweb = ePaginaweb.getText().toString().trim();
                final ProgressDialog progressDialog = new ProgressDialog(getActivity());
                progressDialog.setMessage("Actualizando..");
                progressDialog.dismiss();

                StringRequest request = new StringRequest(Request.Method.POST, URL,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Toast.makeText(getActivity(), response, Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                                onBackPressed();
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(),error.getMessage(),Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }
                }){
                    protected Map<String, String> getParams()throws AuthFailureError{
                        Map<String, String> params = new HashMap<>();
                        params.put("idMotel",id);
                        params.put("nombre",nombre);
                        params.put("region",region);
                        params.put("municipio",municipio);
                        params.put("direccion",direccion);
                        params.put("precios",precios);
                        params.put("horarios",horarios);
                        params.put("servicios",servicios);
                        params.put("telefono",telefono);
                        params.put("paginaweb",paginaweb);
                        return  params;
                    }
                };
                RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
                requestQueue.add(request);


            }
        });

        return  view;

    }

    private void onBackPressed() {
        super.getActivity().onBackPressed();
    }


}