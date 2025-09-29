package com.example.tpobligatorio3.ui.detalle;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.tpobligatorio3.R;
import com.example.tpobligatorio3.model.Producto;

public class DetalleProductoFragment extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detalle_producto, container, false);

        @SuppressWarnings("deprecation")
        Producto producto = (Producto) getArguments().getSerializable("producto");

        TextView codigo = view.findViewById(R.id.textCodigo);
        TextView precio = view.findViewById(R.id.textPrecio);
        TextView descripcion = view.findViewById(R.id.textDescripcion);

        if (producto != null) {
            codigo.setText(producto.getCodigo());
            precio.setText(String.valueOf(producto.getPrecio()));
            descripcion.setText(producto.getDescripcion());
        } else {
            Toast.makeText(getContext(), "No se recibió producto", Toast.LENGTH_SHORT).show();
        }

        return view;
    }
}
