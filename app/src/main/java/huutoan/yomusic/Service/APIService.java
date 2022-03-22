package huutoan.yomusic.Service;

public class APIService {
    private static final String base_url = "https://gorest.co.in/public/v2/";

    public static DataService getService(){
        return APIRetrofitClient.getClient(base_url).create(DataService.class);
    }

}
