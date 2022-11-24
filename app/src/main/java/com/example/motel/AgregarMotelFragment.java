package com.example.motel;

import static android.app.Activity.RESULT_OK;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
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

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class AgregarMotelFragment extends Fragment {

    private EditText  nombre, region, municipio, direccion, precios, horarios, servicios, telefono, paginaweb,nombreimagen;
    private Button buscarImagen, registrarmotel;
    private int PICK_IMAGE_REQUEST = 1;
    ImageView imagen;
    private Bitmap bitmap;
    private String UPLOAD_URL ="https://motelesdepuebla.000webhostapp.com/insertarmotel.php";
    private String KEY_IMAGEN = "foto";
    private String KEY_NOMBREIMAGEN = "nombrefoto";
    String  snombre, sregion, smunicipio, sdireccion, sprecios, shorarios, sservicios, stelefono, spaginaweb, simagen,snombreimagen;


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
        return view;
    }
    
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        
        buscarImagen        = (Button) view.findViewById(R.id.btnBuscarFoto);
        registrarmotel      = (Button) view.findViewById(R.id.btnRegistrarMotel);
        imagen              = (ImageView) view.findViewById(R.id.idfoto);

        nombre              =(EditText) view.findViewById(R.id.idNombre);
        region              =(EditText) view.findViewById(R.id.idRegion);
        municipio           =(EditText) view.findViewById(R.id.idMunicipio);
        direccion           =(EditText) view.findViewById(R.id.idDireccion);
        precios             =(EditText) view.findViewById(R.id.idPrecios);
        horarios            =(EditText) view.findViewById(R.id.idHorarios);
        servicios           =(EditText) view.findViewById(R.id.idServicios);
        telefono            =(EditText) view.findViewById(R.id.idTelefono);
        paginaweb           =(EditText) view.findViewById(R.id.idPaginaweb);
        nombreimagen        =(EditText) view.findViewById(R.id.idnombrefoto);
        
        buscarImagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cargarImagen();
            }
        });

        registrarmotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertamotel();
            }
        });
        
    }

    public String getStringImagen(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }
    private void insertamotel() {
        snombre= nombre.getText().toString();
        sregion= region.getText().toString();
        smunicipio= municipio.getText().toString();
        sdireccion= direccion.getText().toString();
        sprecios= precios.getText().toString();
        shorarios= horarios.getText().toString();
        sservicios= servicios.getText().toString();
        stelefono= telefono.getText().toString();
        spaginaweb= paginaweb.getText().toString();
        simagen = getStringImagen(bitmap);
        snombreimagen = nombreimagen.getText().toString();

        final ProgressDialog loading = ProgressDialog.show(getActivity(),"Registrando...","Espere por favor...",false,false);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, UPLOAD_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        loading.dismiss();
                        Toast.makeText(getActivity(), response, Toast.LENGTH_SHORT).show();
                        onBackPressed();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        loading.dismiss();
                        Toast.makeText(getActivity(), error.getMessage().toString(), Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected  Map<String,String> getParams() throws AuthFailureError{


                Map<String,String>params = new Hashtable<String,String>();
                params.put("nombre",snombre);
                params.put("region",sregion);
                params.put("municipio",smunicipio);
                params.put("direccion",sdireccion);
                params.put("precios",sprecios);
                params.put("horarios",shorarios);
                params.put("servicios",sservicios);
                params.put("telefono",stelefono);
                params.put("paginaweb",spaginaweb);
                params.put(KEY_IMAGEN, simagen);
                params.put(KEY_NOMBREIMAGEN,snombreimagen);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }

    private void cargarImagen() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_PICK);
        startActivityForResult(Intent.createChooser(intent, "Seleccion una Aplicacion"), PICK_IMAGE_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode,@Nullable Intent data){
        super.onActivityResult(requestCode, resultCode,data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri filePath = data.getData();
            try {
                //Cómo obtener el mapa de bits de la Galería
                bitmap = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), filePath);
                //Configuración del mapa de bits en ImageView
                imagen.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public  void onBackPressed(){
        super.getActivity().onBackPressed();

    }

}