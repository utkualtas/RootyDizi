package me.rootylabs.rootydizi.data.api;

import io.reactivex.Single;
import me.rootylabs.rootydizi.data.models.GridModel;
import me.rootylabs.rootydizi.data.models.UserContainer;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {

    @GET("/login.php")
    Single<UserContainer> getLoginResponse(@Query("username") String username, @Query("password") String password);

    @POST("/grid.php")
    Single<GridModel> getMainContent();


}
