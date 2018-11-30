package me.rootylabs.rootydizi.data.repository;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;
import me.rootylabs.rootydizi.data.source.MainDataSource;
import me.rootylabs.rootydizi.data.models.GridSerie;

public class MainRepository implements MainDataSource {

    private final MainDataSource mainDataSource;

    @Inject
    public MainRepository(@Named("mainData") MainDataSource mainDataSource) {
        this.mainDataSource = mainDataSource;
    }

    @Override
    public Flowable<GridSerie> getMainContent() {
        return mainDataSource.getMainContent().subscribeOn(Schedulers.io());
    }
}