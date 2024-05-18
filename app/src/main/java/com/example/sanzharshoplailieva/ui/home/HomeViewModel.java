package com.example.sanzharshoplailieva.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.sanzharshoplailieva.models.ModelM;
import com.example.sanzharshoplailieva.repositories.ShopRepository;

import java.util.List;

public class HomeViewModel extends ViewModel {
    private ShopRepository jemRepository;
    private LiveData<List<ModelM>> modelMResponseLiveData;

    public HomeViewModel() {
        ShopRepository jemRepository = new ShopRepository();
        modelMResponseLiveData= jemRepository.getDashJeminyList();
    }

    public LiveData<List<ModelM>> getModelMResponseLiveData() {
        return modelMResponseLiveData;
    }

}