package com.example.tpobligatorio3.ui.busqueda;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tpobligatorio3.model.Producto;
import com.example.tpobligatorio3.model.RepositorioProductos;

public class BusquedaViewModel extends ViewModel {

    private final MutableLiveData<Producto> productoEncontrado = new MutableLiveData<>();
    private final MutableLiveData<String> mensajeError = new MutableLiveData<>();

    public LiveData<Producto> getProductoEncontrado() {
        return productoEncontrado;
    }

    public LiveData<String> getMensajeError() {
        return mensajeError;
    }

    public void buscarProducto(String codigo) {
        Producto producto = RepositorioProductos.buscarPorCodigo(codigo);
        if (producto != null) {
            productoEncontrado.setValue(producto);
        } else {
            mensajeError.setValue("Producto no encontrado");
        }
    }
}
