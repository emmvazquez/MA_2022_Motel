package com.example.motel;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

public class MotelesFragment extends Fragment {
    RecyclerView recyclerView;
    ArrayList<Moteles>ListaMoteles;
    JsonObjectRequest jsonObjectRequest;

    Activity actividad;
    iComunicaFragments interfaceComunicaFragments;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_moteles, container, false);

        ListaMoteles = new ArrayList<>();
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setHasFixedSize(true);
        ListarDatos();
        return view;
    }


    private void ListarDatos() {
        RequestQueue requestQueue;
        requestQueue = Volley.newRequestQueue(getActivity());
        String URL = "https://motelesdepuebla.000webhostapp.com/mostralmotel.php";
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URL, (JSONObject) null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Moteles moteles = null;
                        JSONArray jsonArray = response.optJSONArray("motel");
                        try {
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    moteles = new Moteles();
                                    JSONObject jsonObject = null;
                                    jsonObject = jsonArray.getJSONObject(i);
                                    moteles.setIdMotel(jsonObject.optInt("idMotel"));
                                    moteles.setSnombre(jsonObject.optString("nombre"));
                                    moteles.setSregion(jsonObject.optString("region"));
                                    moteles.setSmunicipio(jsonObject.optString("municipio"));
                                    moteles.setSdireccion(jsonObject.optString("direccion"));
                                    moteles.setSprecios(jsonObject.optString("precios"));
                                    moteles.setShorarios(jsonObject.optString("horarios"));
                                    moteles.setSservicios(jsonObject.optString("servicios"));
                                    moteles.setStelefono(jsonObject.optString("telefono"));
                                    moteles.setSpaginaweb(jsonObject.optString("paginaweb"));
                                    moteles.setSimagen(jsonObject.optString("foto"));
                                    ListaMoteles.add(moteles);
                                }
                        AdapterMotel adapterMotel = new AdapterMotel(ListaMoteles,getContext());
                        recyclerView.setAdapter(adapterMotel);

                        adapterMotel.setOnclickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                String nombre = ListaMoteles.get(recyclerView.getChildAdapterPosition(view)).getSnombre();
                                Toast.makeText(getContext(), "Seleccion:"+nombre,Toast.LENGTH_SHORT).show();
                                interfaceComunicaFragments.enviarmotel(ListaMoteles.get(recyclerView.getChildAdapterPosition(view)));
                            }
                        });
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        requestQueue.add(jsonObjectRequest);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof  Activity){
            this.actividad = (Activity) context;
            interfaceComunicaFragments = (iComunicaFragments) this.actividad;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}