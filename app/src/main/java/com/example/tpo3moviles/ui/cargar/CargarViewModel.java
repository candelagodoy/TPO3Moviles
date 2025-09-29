package com.example.tpo3moviles.ui.cargar;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.AndroidViewModel;
import static com.example.tpo3moviles.MainActivity.*;

import com.example.tpo3moviles.databinding.FragmentCargarBinding;
import com.example.tpo3moviles.modelo.Producto;

public class CargarViewModel extends AndroidViewModel {
    private MutableLiveData<String> mMensaje;

    public CargarViewModel(@NonNull Application application) {
        super(application);
    }
    public LiveData<String> getMMensaje(){
        if (mMensaje == null){
            mMensaje = new MutableLiveData<>();
        }
        return mMensaje;
    }

    public void cargarProductos(String codigo, String descripcion, String precio){
        boolean valido = validarProducto(codigo,descripcion,precio);

        if(valido){
            listaProductos.add(new Producto(codigo,descripcion,Double.parseDouble(precio)));
            mMensaje.setValue("Producto cargado");
        }
    }

    public boolean validarProducto(String codigo, String descripcion, String precio){
        boolean duplicado = false;
        boolean valido = true;

        StringBuilder mensaje = new StringBuilder();

        try{
            double precio1 = Double.parseDouble(precio);
            duplicado = listaProductos.contains(new Producto(codigo,descripcion,precio1));

        }catch (NumberFormatException e){
            mensaje.append("El precio debe ser un número \n");
            valido = false;
        }

        if(duplicado){
            mensaje.append("El código ingresado ya exite \n");
            valido = false;
        }

        if(codigo.isBlank()){
            mensaje.append("El campo código  no puede estar vacio \n");
            valido = false;
        }
        if(descripcion.isBlank()){
            mensaje.append("El campo descripción no puede estar vacio \n");
            valido = false;
        }
        if(precio.isBlank()){
            mensaje.append("El campo precio no puede estar vacio \n");
            valido = false;
        }

        mMensaje.setValue(mensaje.toString());
        return valido;

    }


}