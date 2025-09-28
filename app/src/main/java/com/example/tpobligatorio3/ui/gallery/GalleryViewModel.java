package com.example.tpobligatorio3.ui.gallery;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tpobligatorio3.model.Producto;

import java.util.ArrayList;
import static com.example.tpobligatorio3.MainActivity.stock;

public class GalleryViewModel extends ViewModel {


    private MutableLiveData<ArrayList<Producto>> mLista;


    public LiveData<ArrayList<Producto>> getMLista(){
        if(mLista==null){
            mLista = new MutableLiveData<>();
        }
        return mLista;
    }

    public void cargarLista(){
        mLista.setValue(stock);
    }
}