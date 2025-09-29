package com.example.tpo3moviles.ui.detalle;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tpo3moviles.R;
import com.example.tpo3moviles.databinding.FragmentDetalleBinding;
import com.example.tpo3moviles.databinding.FragmentListarBinding;
import com.example.tpo3moviles.databinding.FragmentModificarBinding;
import com.example.tpo3moviles.modelo.Producto;
import com.example.tpo3moviles.ui.listar.ListarViewModel;

public class DetalleFragment extends Fragment {

    private DetalleViewModel mViewModel;

    private FragmentDetalleBinding binding;

    public static DetalleFragment newInstance() {
        return new DetalleFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        mViewModel = new ViewModelProvider(this).get(DetalleViewModel.class);

        binding = FragmentDetalleBinding.inflate(inflater, container, false);

        View root = binding.getRoot();

        mViewModel.getMutableProducto().observe(getViewLifecycleOwner(), new Observer<Producto>() {
            @Override
            public void onChanged(Producto producto) {
                binding.etCodigoModificacion.setText(producto.getCodigo());
                binding.etDescripcionModificacion.setText(producto.getDescripcion());
                binding.etPrecioModificacion.setText(String.valueOf(producto.getPrecio()));

            }
        });

        mViewModel.cargarDatosProducto(getArguments());

        binding.btCargarProductoModificado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String codigo = binding.etCodigoModificacion.getText().toString();
                String descripcion = binding.etDescripcionModificacion.getText().toString();
                String precio = binding.etPrecioModificacion.getText().toString();
                mViewModel.cargarProductos(codigo,descripcion,precio);
            }
        });
        mViewModel.getMMensaje().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                binding.tvMensajeModificacion.setText(s);
                limpiarCampos();
            }
        });
        return root;
    }

    private void limpiarCampos() {
        binding.etCodigoModificacion.setText("");
        binding.etDescripcionModificacion.setText("");
        binding.etPrecioModificacion.setText("");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(DetalleViewModel.class);
        // TODO: Use the ViewModel
    }

}