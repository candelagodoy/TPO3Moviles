package com.example.tpo3moviles.ui.modificar;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tpo3moviles.R;
import com.example.tpo3moviles.databinding.FragmentListarBinding;
import com.example.tpo3moviles.databinding.FragmentModificarBinding;
import com.example.tpo3moviles.modelo.Producto;
import com.example.tpo3moviles.ui.cargar.CargarViewModel;
import com.example.tpo3moviles.ui.detalle.DetalleFragment;
import com.example.tpo3moviles.ui.listar.ListarViewModel;

public class ModificarFragment extends Fragment {

    private ModificarViewModel mViewModel;
    private FragmentModificarBinding binding;

    public static ModificarFragment newInstance() {
        return new ModificarFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        mViewModel = new ViewModelProvider(this).get(ModificarViewModel.class);

        binding = FragmentModificarBinding.inflate(inflater, container, false);

        View root = binding.getRoot();

        mViewModel.getMMensajeBusqueda().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                binding.tvMensajeBusquedaModificacion.setText(s);
            }
        });

        mViewModel.getMProducto().observe(getViewLifecycleOwner(), new Observer<Producto>() {
            @Override
            public void onChanged(Producto producto) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("producto", producto);
                NavHostFragment.findNavController(ModificarFragment.this)
                        .navigate(R.id.nav_detalle, bundle);

            }
        });

        binding.btBuscarCodigo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewModel.buscarProducto(binding.etCodigoIngresado.getText().toString());
            }
        });




        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ModificarViewModel.class);
        // TODO: Use the ViewModel
    }

}