package com.example.tpobligatorio3.ui.home;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.tpobligatorio3.databinding.FragmentHomeBinding;
import com.example.tpobligatorio3.model.Producto;
import com.example.tpobligatorio3.model.RepositorioProductos;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private HomeViewModel mv;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        mv = ViewModelProvider.AndroidViewModelFactory
                .getInstance(getActivity().getApplication())
                .create(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Botón "Agregar"
        binding.btAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mv.validar(
                        binding.etCodigo.getText().toString(),
                        binding.etDescrip.getText().toString(),
                        binding.etPrecio.getText().toString()
                );
            }
        });

        // Observador de error
        mv.getMError().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                new AlertDialog.Builder(getContext())
                        .setTitle("ERROR")
                        .setMessage(s)
                        .setNeutralButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // No hace nada
                            }
                        })
                        .show();
            }
        });

        // Observador de éxito
        mv.getMCorrecto().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();

                // ✅ Crear el producto y agregarlo al repositorio
                String codigo = binding.etCodigo.getText().toString();
                String descripcion = binding.etDescrip.getText().toString();
                double precio = Double.parseDouble(binding.etPrecio.getText().toString());

                Producto nuevo = new Producto(codigo, descripcion, precio);
                RepositorioProductos.agregarProducto(nuevo);
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
