package huutoan.yomusic.Service;

import java.util.List;

import huutoan.yomusic.Model.Post;
import huutoan.yomusic.Model.Trending;
import huutoan.yomusic.Model.User;
import retrofit2.Call;
import retrofit2.http.GET;

public interface DataService {
    @GET("users")
    Call<List<User>> GetDataUser();

    @GET("posts")
    Call<List<Post>> GetDataPost();

    @GET("api/getAllTrending")
    Call<List<Trending>> GetDataTrending();


}
