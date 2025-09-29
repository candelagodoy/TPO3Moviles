package com.example.tpo3moviles.ui.listar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.tpo3moviles.databinding.FragmentListarBinding;
import com.example.tpo3moviles.modelo.Producto;

import java.util.ArrayList;
import java.util.Objects;

public class ListarFragment extends Fragment {

    private FragmentListarBinding binding;
    private ListarViewModel vm;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        vm = new ViewModelProvider(this).get(ListarViewModel.class);

        binding = FragmentListarBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        vm.getMMensaje().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                binding.tvListado.setText(s);
            }
        });

        vm.getMListaProductos().observe(getViewLifecycleOwner(), new Observer<ArrayList<Producto>>() {
            @Override
            public void onChanged(ArrayList<Producto> productos) {
                binding.tvListado.setText("Listado de Productos");
                ProductoAdapter adapter = new ProductoAdapter(productos, getContext(), getLayoutInflater());
                //para elegir como mostrar mis tarjetas, cuantos y de que forma
                GridLayoutManager gLManager = new GridLayoutManager(getContext(), 1, GridLayoutManager.VERTICAL, false);
                //se lo seteo al recycler, como quiero que muestre
                binding.rvLista.setLayoutManager(gLManager);
                //qué quiero que muestre
                binding.rvLista.setAdapter(adapter);
            }

        });

        vm.listarProductos();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}