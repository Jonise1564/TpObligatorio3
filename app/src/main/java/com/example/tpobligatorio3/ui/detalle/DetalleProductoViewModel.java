package com.example.tpobligatorio3.ui.detalle;

import android.os.Bundle;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tpobligatorio3.model.Producto;

public class DetalleProductoViewModel extends ViewModel {

    private final MutableLiveData<Producto> producto = new MutableLiveData<>();

    public LiveData<Producto> getProducto() {
        return producto;
    }

    public void inicializarProducto(Bundle args) {
        if (args != null && args.containsKey("producto")) {
            try {
                @SuppressWarnings("deprecation")
                Producto p = (Producto) args.getSerializable("producto");
                producto.setValue(p);
            } catch (Exception e) {
                producto.setValue(null);
            }
        } else {
            producto.setValue(null);
        }
    }
}
