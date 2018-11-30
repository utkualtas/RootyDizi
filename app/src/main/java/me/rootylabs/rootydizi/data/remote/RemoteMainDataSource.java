package me.rootylabs.rootydizi.data.remote;

import javax.inject.Inject;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;
import me.rootylabs.rootydizi.data.source.MainDataSource;
import me.rootylabs.rootydizi.data.api.ApiService;
import me.rootylabs.rootydizi.data.models.GridModel;
import me.rootylabs.rootydizi.data.models.GridSerie;

public class RemoteMainDataSource implements MainDataSource {

    ApiService apiService;

    @Inject
    public RemoteMainDataSource(ApiService apiService) {
        this.apiService = apiService;
    }


    @Override
    public Flowable<GridSerie> getMainContent() {
        return Flowable.create(e ->  {
            apiService
                    .getMainContent()
                    .toFlowable()
                    .flatMapIterable(GridModel::getData)
                    .subscribeOn(Schedulers.io())
                    .subscribe(e::onNext);
        }, BackpressureStrategy.BUFFER);
    }
}
