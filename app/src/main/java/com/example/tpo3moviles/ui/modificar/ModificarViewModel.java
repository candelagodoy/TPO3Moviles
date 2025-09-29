package com.example.tpo3moviles.ui.modificar;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import static com.example.tpo3moviles.MainActivity.*;

import com.example.tpo3moviles.modelo.Producto;


public class ModificarViewModel extends AndroidViewModel {

    private MutableLiveData<String> mMensajeBusqueda;
    private MutableLiveData<Producto> mProducto;

    public ModificarViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<String> getMMensajeBusqueda(){
        if(mMensajeBusqueda == null){
            mMensajeBusqueda = new MutableLiveData<>();
        }
        return mMensajeBusqueda;
    }

    public LiveData<Producto> getMProducto(){
        if(mProducto == null){
            mProducto = new MutableLiveData<>();
        }
        return mProducto;
    }

    public void buscarProducto(String codigo){
        int i = 0;
        Producto encontrado = null ;
        while(i < listaProductos.size() && encontrado == null){
            if(codigo.equals(listaProductos.get(i).getCodigo())){
                mProducto.setValue(listaProductos.get(i));
                encontrado = listaProductos.get(i);
            }
            i++;
        }
        if(encontrado == null){
            mMensajeBusqueda.setValue("Producto no encontrado");
        }

    }


}