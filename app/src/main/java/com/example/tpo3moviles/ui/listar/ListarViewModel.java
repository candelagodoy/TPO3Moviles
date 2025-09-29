package com.example.tpo3moviles.ui.listar;

import static com.example.tpo3moviles.MainActivity.listaProductos;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tpo3moviles.MainActivity.*;

import com.example.tpo3moviles.modelo.Producto;

import java.util.ArrayList;
import java.util.Comparator;

public class ListarViewModel extends ViewModel {

    private MutableLiveData<String> mMensaje;
    private MutableLiveData<ArrayList<Producto>> mListaProductos;


    public LiveData<ArrayList<Producto>> getMListaProductos(){
        if(mListaProductos == null){
            mListaProductos = new MutableLiveData<>();
        }
        return mListaProductos;
    }

    public LiveData<String> getMMensaje(){
        if(mMensaje == null){
            mMensaje = new MutableLiveData<>();
        }
        return mMensaje;
    }

    public void listarProductos(){
        if (listaProductos.isEmpty()){
            mMensaje.setValue("No hay productos para mostrar");
        }else{
            listaProductos.sort(new Comparator<Producto>() {
                @Override
                public int compare(Producto o1, Producto o2) {
                    return o1.getDescripcion().compareToIgnoreCase(o2.getDescripcion());
                }
            });
            mListaProductos.setValue(listaProductos);
        }
    }
}