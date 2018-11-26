package me.rootylabs.rootydizi.ui.activities.main;

import android.annotation.SuppressLint;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import me.rootylabs.rootydizi.data.models.GridSerie;
import me.rootylabs.rootydizi.data.repository.MainRepository;
import me.rootylabs.rootydizi.utils.RxViewModel;

import static android.support.constraint.Constraints.TAG;


public class MainActivityViewModel extends RxViewModel {


    private final MainRepository mainRepository;
    private MutableLiveData<List<GridSerie>> contentLiveData = new MutableLiveData<>();

    @Inject
    public MainActivityViewModel(MainRepository mainRepository){
        this.mainRepository = mainRepository;
        loadPage();
    }

    public LiveData<List<GridSerie>> getDatas() {
        return contentLiveData;
    }

    public void loadPage(){
        disposable.add(mainRepository.
                getMainContent()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::addSeries));

    }

    private void addSeries(GridSerie gridSerie) {

        Log.e("STE", "addSeries: " + gridSerie.getName() );
        List<GridSerie> gridSeries = new ArrayList<>();
        gridSeries.add(gridSerie);
        contentLiveData.setValue(gridSeries);
    }


}
