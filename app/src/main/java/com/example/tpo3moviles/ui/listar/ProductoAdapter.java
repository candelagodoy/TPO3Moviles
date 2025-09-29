package com.example.tpo3moviles.ui.listar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tpo3moviles.R;
import com.example.tpo3moviles.modelo.Producto;

import java.util.List;

public class ProductoAdapter extends RecyclerView.Adapter<ProductoAdapter.ViewHolderProducto> {
    private List<Producto> listaProductos;
    private Context context;
    private LayoutInflater li;

    public ProductoAdapter(List<Producto> listaProductos, Context context, LayoutInflater li) {
        this.listaProductos = listaProductos;
        this.context = context;
        this.li = li;
    }

    @NonNull
    @Override
    public ViewHolderProducto onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = li.inflate(R.layout.item, parent, false);
        return new ViewHolderProducto(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderProducto holder, int position) {
        Producto productoActual = listaProductos.get(position);
        holder.tvCodigo.setText(productoActual.getCodigo());
        holder.tvDescripcion.setText(productoActual.getDescripcion());
        holder.tvPrecio.setText(String.valueOf(productoActual.getPrecio()));

    }

    @Override
    public int getItemCount() {
        return listaProductos.size();
    }

    public class ViewHolderProducto extends RecyclerView.ViewHolder{
    TextView tvCodigo, tvDescripcion, tvPrecio;
        public ViewHolderProducto(@NonNull View itemView) {
            super(itemView);
            tvCodigo = itemView.findViewById(R.id.tvCodigoItem);
            tvDescripcion= itemView.findViewById(R.id.tvDescripcionItem);
            tvPrecio = itemView.findViewById(R.id.tvPrecioItem);
        }
    }


}
