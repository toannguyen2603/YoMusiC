package huutoan.yomusic.Service;

import java.util.List;

import huutoan.yomusic.Model.PlayList;
import huutoan.yomusic.Model.Post;
import huutoan.yomusic.Model.Topic;
import huutoan.yomusic.Model.Trending;
import huutoan.yomusic.Model.User;
import retrofit2.Call;
import retrofit2.http.GET;

public interface DataService {

    @GET("api/getAllTrending")
    Call<List<Trending>> GetDataTrending();

    @GET("api/getAllPlayList")
    Call<List<PlayList>> GetDataPlayList();

    @GET("api/getAllTopic")
    Call<List<Topic>> GetDataTopic();




}
