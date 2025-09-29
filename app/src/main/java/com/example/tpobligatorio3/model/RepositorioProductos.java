package com.example.tpobligatorio3.model;

import java.util.ArrayList;
import java.util.List;

public class RepositorioProductos {
    private static List<Producto> listaProductos = new ArrayList<>();

    public static void cargarProductos(List<Producto> productos) {
        listaProductos.clear();
        listaProductos.addAll(productos);
    }

    public static void agregarProducto(Producto producto) {
        listaProductos.add(producto);
    }

    public static Producto buscarPorCodigo(String codigo) {
        for (Producto p : listaProductos) {
            if (p.getCodigo().equalsIgnoreCase(codigo)) {
                return p;
            }
        }
        return null;
    }

    public static List<Producto> getTodos() {
        return new ArrayList<>(listaProductos);
    }
}
