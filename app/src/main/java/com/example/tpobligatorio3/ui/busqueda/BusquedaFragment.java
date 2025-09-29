package com.example.tpobligatorio3.ui.busqueda;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tpobligatorio3.R;
import com.example.tpobligatorio3.model.Producto;
import com.example.tpobligatorio3.model.RepositorioProductos;


public class BusquedaFragment extends Fragment {
    private EditText editCodigo;
    private Button btnBuscar;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_busqueda, container, false);

        editCodigo = view.findViewById(R.id.editTextCodigo);
        btnBuscar = view.findViewById(R.id.buttonBuscar);

        btnBuscar.setOnClickListener(v -> {
            String codigo = editCodigo.getText().toString();
            Producto producto = RepositorioProductos.buscarPorCodigo(codigo);

            if (producto != null) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("producto", producto);
                Navigation.findNavController(view).navigate(R.id.nav_detalle_producto, bundle);
            } else {
                Toast.makeText(getContext(), "Producto no encontrado", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}