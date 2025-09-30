package com.example.tpobligatorio3.ui.detalle;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.tpobligatorio3.R;

public class DetalleProductoFragment extends Fragment {

    private DetalleProductoViewModel vm;
    private TextView codigo, precio, descripcion;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detalle_producto, container, false);

        codigo = view.findViewById(R.id.textCodigo);
        precio = view.findViewById(R.id.textPrecio);
        descripcion = view.findViewById(R.id.textDescripcion);

        vm = new ViewModelProvider(this).get(DetalleProductoViewModel.class);

        vm.getProducto().observe(getViewLifecycleOwner(), p -> {
            if (p != null) {
                codigo.setText(p.getCodigo());
                precio.setText(String.valueOf(p.getPrecio()));
                descripcion.setText(p.getDescripcion());
            } else {
                Toast.makeText(getContext(), "No se recibió producto", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        vm.inicializarProducto(getArguments());
    }
}
