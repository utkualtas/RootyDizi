package me.rootylabs.rootydizi.ui.activities.main;

import android.annotation.SuppressLint;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.util.Log;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;
import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import me.rootylabs.rootydizi.R;
import me.rootylabs.rootydizi.data.api.ApiService;
import me.rootylabs.rootydizi.data.models.GridModel;
import me.rootylabs.rootydizi.data.models.GridSerie;
import me.rootylabs.rootydizi.databinding.ActivityMainBinding;

public class MainActivity extends DaggerAppCompatActivity {

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    ActivityMainBinding binding;

    MainActivityViewModel feedViewModel;

    @Inject
    ApiService apiService;



    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        feedViewModel = ViewModelProviders.of(this, viewModelFactory).get(MainActivityViewModel.class);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);




        /* NOT: Bu veri çekme işini muhtemelen başka yerde yapıcaz ben şimdilik denemek amaçlı burda bir kaç örnek yaptım.
        Normalde veriyi ApiService üzerinde Single olarak tanımladık fakat burda toObservale ile Observable a dönüştürdük.
        Aynı Şekilde toFlowable ile Flowable a da dönüştürebilirdik. Hangisini kullanmak istersek.

        apiService
                .getMainContent()
                .toObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<GridModel>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(GridModel gridModel) {
                        Log.e("datamiz", gridModel.getCode().toString() );
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("datamizError", e.toString() );

                    }

                    @Override
                    public void onComplete() {

                    }
                });
         */

        /*Burda java 8 in nimetinden faydalanarak lamda kullandık ve kodumuz çok daha kısa bir hale geldi ayrıca burda herhangi bir dönüştürme yapmadık.
          Veriyi Single olarak kullanmaya devam ediyoruz.

        apiService.getMainContent()
                .subscribeOn(Schedulers.io()) //Bildiğim kadarıyla boşta thread varsa onu kullanır yok ise yeni bir tane oluşturur.
                .subscribe(myData -> {
                    Log.e("datamiz", myData.getCode());
                    // handle data fetched successfully and API call completed
                }, throwable -> {
                    // handle error event
                    Log.e("datamiz", throwable.toString());
                });
        */

        /* Aynı Şekilde Flowable a dönüştürek kullandık. Labda ile daha kısa kod.*/

        apiService.getMainContent()
                .subscribeOn(Schedulers.io())
                .toFlowable()
                .subscribe( myData -> {
                  Log.e("datamiz", myData.getCode().toString());
                }, throwable -> {
                    Log.e("datamiz", throwable.toString());
                });





        /*Log.e("datamizSubOldu", d.toString() );
        Log.e("datamiz", gridModel.getCode().toString() );
        Log.e("datamizError", e.toString() );*/




     }


}
