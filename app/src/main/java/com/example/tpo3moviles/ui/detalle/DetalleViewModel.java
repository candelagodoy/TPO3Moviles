package com.example.tpo3moviles.ui.detalle;

import static com.example.tpo3moviles.MainActivity.listaProductos;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.tpo3moviles.modelo.Producto;

public class DetalleViewModel extends AndroidViewModel{

    private MutableLiveData<Producto> mutableProducto;
    private MutableLiveData<String> mMensaje;
    public DetalleViewModel(@NonNull Application application) {
        super(application);

    }

    public LiveData<String> getMMensaje(){
        if (mMensaje == null){
            mMensaje = new MutableLiveData<>();
        }
        return mMensaje;
    }
    public LiveData<Producto> getMutableProducto(){
        if(mutableProducto == null){
            mutableProducto = new MutableLiveData<>();
        }
        return mutableProducto;
    }

    public void cargarDatosProducto(Bundle bundle) {
        Producto producto = (Producto) bundle.getSerializable("producto");
        if(producto != null){
            mutableProducto.setValue(producto);
        }
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

       /* if(duplicado){
            mensaje.append("El código ingresado ya exite \n");
            valido = false;
        }*/

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