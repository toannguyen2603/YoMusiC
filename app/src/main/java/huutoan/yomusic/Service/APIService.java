package huutoan.yomusic.Service;

public class APIService {
    private static final String base_url = "https://gorest.co.in/public/v2/";
//    private static final String base_url = "http://localhost:8000/api/";

    public static DataService getService(){
        return APIRetrofitClient.getClient(base_url).create(DataService.class);
    }
}
