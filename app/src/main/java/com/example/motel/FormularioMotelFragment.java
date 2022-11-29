package com.example.motel;

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
import android.widget.ListAdapter;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class FormularioMotelFragment extends Fragment {
    FloatingActionButton floatingActionButton;
    RecyclerView recyclerView2;
    ArrayList<Moteles> ListaMoteles2;
    JsonObjectRequest jsonObjectRequest;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_formulario_motel, container, false);

        ListaMoteles2 = new ArrayList<>();
        floatingActionButton = view.findViewById(R.id.fab);
        recyclerView2 = view.findViewById(R.id.recyclerview2);
        recyclerView2.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView2.setHasFixedSize(true);


        //abrir fragment de agregar
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_formularioMotelFragment_to_agregarMotelFragment);
            }
        });
        ListarDatos2();
        return view;
    }

    //mostrar los datos en un listview
    private void ListarDatos2() {
        RequestQueue requestQueue;
        requestQueue = Volley.newRequestQueue(getActivity());
        String URL = "https://motelesdepuebla.000webhostapp.com/mostralmotel.php";
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URL, (JSONObject) null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Moteles moteles2 = null;
                        JSONArray jsonArray = response.optJSONArray("motel");
                        try {
                            for (int i = 0; i < jsonArray.length(); i++) {
                                moteles2 = new Moteles();
                                JSONObject jsonObject = null;
                                jsonObject = jsonArray.getJSONObject(i);
                                moteles2.setIdMotel(jsonObject.optInt("idMotel"));
                                moteles2.setSnombre(jsonObject.optString("nombre"));
                                moteles2.setSregion(jsonObject.optString("region"));
                                moteles2.setSmunicipio(jsonObject.optString("municipio"));
                                moteles2.setSdireccion(jsonObject.optString("direccion"));
                                moteles2.setSprecios(jsonObject.optString("precios"));
                                moteles2.setShorarios(jsonObject.optString("horarios"));
                                moteles2.setSservicios(jsonObject.optString("servicios"));
                                moteles2.setStelefono(jsonObject.optString("telefono"));
                                moteles2.setSpaginaweb(jsonObject.optString("paginaweb"));
                                moteles2.setSimagen(jsonObject.optString("foto"));
                                ListaMoteles2.add(moteles2);
                            }
                            AdapterMotel2 adapterMotel2 = new AdapterMotel2(ListaMoteles2, getContext());
                            recyclerView2.setAdapter(adapterMotel2);
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

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}