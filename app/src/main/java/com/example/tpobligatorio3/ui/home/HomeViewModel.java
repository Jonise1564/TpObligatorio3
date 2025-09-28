package com.example.tpobligatorio3.ui.home;

import static com.example.tpobligatorio3.MainActivity.stock;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tpobligatorio3.model.Producto;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mError, mCorrecto;

    public LiveData<String> getMError() {
        if (mError == null) {
            mError = new MutableLiveData<>();
        }
        return mError;
    }

    public LiveData<String> getMCorrecto() {
        if (mCorrecto == null) {
            mCorrecto = new MutableLiveData<>();
        }
        return mCorrecto;
    }

    public void validar(String codigo, String descrip, String precio) {
        // Validación de campos vacíos
        if (codigo.trim().isEmpty() || descrip.trim().isEmpty() || precio.trim().isEmpty()) {
            mError.setValue("Todos los campos son obligatorios.");
            return;
        }

        // Validación de formato de precio
        if (precio.startsWith(".")) {
            mError.setValue("El precio no puede comenzar con un punto.");
            return;
        }

        if (precio.equals("0") || precio.equals("0.0") || precio.equals("0.00")) {
            mError.setValue("El precio no puede ser cero.");
            return;
        }

        if (!precio.matches("^(0|[1-9]\\d*)(\\.\\d{1,2})?$")) {
            mError.setValue("Ingrese un precio válido (máximo dos decimales).");
            return;
        }

        // Validación de duplicados en el stock
        for (Producto p : stock) {
            if (p.getCodigo().equalsIgnoreCase(codigo)) {
                mError.setValue("El código ya está registrado.");
                return;
            }
            if (p.getDescripcion().equalsIgnoreCase(descrip)) {
                mError.setValue("La descripción ya existe.");
                return;
            }
        }

        // Si todo está correcto, se agrega el producto
        try {
            double precioFinal = Double.parseDouble(precio);
            Producto producto = new Producto(codigo.trim(), descrip.trim(), precioFinal);
            stock.add(producto);
            mCorrecto.setValue("Producto cargado correctamente.");
        } catch (NumberFormatException e) {
            mError.setValue("Error al convertir el precio. Verifique el formato.");
        }
    }
}