package com.example.motel;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

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

import Adapter.Adapter;


public class FormularioMotelFragment extends Fragment {
    FloatingActionButton floatingActionButton;
    ListView listView;
    Adapter adapter;

    public static ArrayList<Moteles>motelesArrayList=new ArrayList<>();
    String URL = "https://motelesdepuebla.000webhostapp.com/mostralmotel.php";
    Moteles moteles;
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

        floatingActionButton = view.findViewById(R.id.fab);
        listView = view.findViewById(R.id.listmostrar);
        adapter = new Adapter( getActivity(),motelesArrayList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                ProgressDialog progressDialog = new ProgressDialog(view.getContext());

                CharSequence[] dialogoItem={"ver informacion","Editar informacion","Eliminar motel"};
                builder.setTitle(motelesArrayList.get(position).getSnombre());
                builder.setItems(dialogoItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i){
                            case 0:
                                Navigation.findNavController(view).navigate(R.id.action_formularioMotelFragment_to_detallesMotelFragment);
                                break;
                            case 1:
                                Navigation.findNavController(view).navigate(R.id.action_formularioMotelFragment_to_editarMotelFragment);
                                break;
                            case 2:
                                //EliminarDatos(motelesArrayList.get(position).getId());
                                break;
                        }
                    }
                });
                builder.create().show();
            }
        });
        ListarDatos();


        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_formularioMotelFragment_to_agregarMotelFragment);
            }
        });
    }

    private void ListarDatos() {
        StringRequest request = new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        motelesArrayList.clear();

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String exito = jsonObject.getString("exito");
                            JSONArray jsonArray = jsonObject.getJSONArray("datos");
                            if (exito.equals("1")) {
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject object = jsonArray.getJSONObject(i);
                                    String idMotel = object.getString("idMotel");
                                    String nombre = object.getString("nombre");
                                    String region = object.getString("region");
                                    String municipio = object.getString("municipio");
                                    String direccion = object.getString("direccion");
                                    String precios = object.getString("precios");
                                    String horarios = object.getString("horarios");
                                    String servicios = object.getString("servicios");
                                    String telefono = object.getString("telefono");
                                    String paginaweb = object.getString("paginaweb");
                                    moteles = new Moteles(idMotel, nombre, region, municipio,
                                            direccion, precios, horarios, servicios, telefono, paginaweb);
                                    motelesArrayList.add(moteles);
                                    adapter.notifyDataSetChanged();
                                }
                            }
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
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(request);
    }
}