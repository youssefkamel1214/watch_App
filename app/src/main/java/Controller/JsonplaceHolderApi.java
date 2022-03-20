package Controller;

import java.util.ArrayList;
import java.util.Map;

import models.Movie;
import models.Result;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface JsonplaceHolderApi {
    @GET("popular")
    Call<Result> get_Result(@Query("api_key") String key,@Query("page")int page);

}
