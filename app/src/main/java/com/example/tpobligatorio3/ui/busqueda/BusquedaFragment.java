package com.example.tpobligatorio3.ui.busqueda;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.tpobligatorio3.R;
import com.example.tpobligatorio3.model.Producto;

public class BusquedaFragment extends Fragment {

    private EditText editCodigo;
    private Button btnBuscar;
    private BusquedaViewModel vm;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_busqueda, container, false);

        editCodigo = view.findViewById(R.id.editTextCodigo);
        btnBuscar = view.findViewById(R.id.buttonBuscar);

        vm = new ViewModelProvider(this).get(BusquedaViewModel.class);

        btnBuscar.setOnClickListener(v -> {
            String codigo = editCodigo.getText().toString().trim();
            vm.buscarProducto(codigo);
        });

        vm.getProductoEncontrado().observe(getViewLifecycleOwner(), producto -> {
            Bundle bundle = new Bundle();
            bundle.putSerializable("producto", producto);
            Navigation.findNavController(view).navigate(R.id.nav_detalle_producto, bundle);
        });

        vm.getMensajeError().observe(getViewLifecycleOwner(), mensaje -> {
            Toast.makeText(getContext(), mensaje, Toast.LENGTH_SHORT).show();
        });

        return view;
    }
}
