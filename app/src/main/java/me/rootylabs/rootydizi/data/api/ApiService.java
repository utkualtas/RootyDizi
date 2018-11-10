package me.rootylabs.rootydizi.data.api;

import io.reactivex.Single;
import me.rootylabs.rootydizi.data.models.GridModel;
import retrofit2.Response;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {

    @POST("/login")
    Single<Response> getLoginResponse(@Path("username") String user, @Path("password") String password);

    @POST("/grid.php")
    Single<GridModel> getMainContent();


}
