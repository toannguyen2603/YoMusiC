package huutoan.yomusic.Service;

public class APIService {
    private static final String base_url = "https://yomusic-api.herokuapp.com/";

    public static DataService getService(){
        return APIRetrofitClient.getClient(base_url).create(DataService.class);
    }
}
