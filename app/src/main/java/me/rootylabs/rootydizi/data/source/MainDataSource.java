package me.rootylabs.rootydizi.data.source;

import io.reactivex.Flowable;
import me.rootylabs.rootydizi.data.models.GridSerie;

public interface MainDataSource {

    Flowable<GridSerie> getMainContent();

}
