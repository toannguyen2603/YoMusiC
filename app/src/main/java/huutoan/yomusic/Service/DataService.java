package huutoan.yomusic.Service;

import java.util.List;

import huutoan.yomusic.Model.Charts;
import huutoan.yomusic.Model.MostLikedSongs;
import huutoan.yomusic.Model.PlayListSong;
import huutoan.yomusic.Model.Singer;
import huutoan.yomusic.Model.Song;
import huutoan.yomusic.Model.Topic;
import huutoan.yomusic.Model.Trending;
import retrofit2.Call;
import retrofit2.http.GET;

public interface DataService {

    @GET("api/getAllTrending")
    Call<List<Trending>> GetDataTrending();

    @GET("api/getSongPlayList")
    Call<List<PlayListSong>> GetDataSongPlayList();

    @GET("api/getAllTopic")
    Call<List<Topic>> GetDataTopic();

    @GET("api/getAllAlbum")
    Call<List<Singer>> GetDataSinger();

    @GET("api/getAllCategory")
    Call<List<Charts>> GetDataCharts();

    @GET("api/getMostLikeSong")
    Call<List<Song>> GetDataMostLikedSongsCurrent();

}
