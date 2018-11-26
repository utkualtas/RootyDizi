package me.rootylabs.rootydizi.data;

import io.reactivex.Flowable;
import me.rootylabs.rootydizi.data.models.GridModel;
import me.rootylabs.rootydizi.data.models.GridSerie;

public interface MainDataSource {

    Flowable<GridSerie> getMainContent();

}
