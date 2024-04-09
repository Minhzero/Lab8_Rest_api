package minhnqph38692.fpoly.dethithu;

import static minhnqph38692.fpoly.dethithu.ApiServices.BASE_URL;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpRequest {

    private  ApiServices apiServices;

    public HttpRequest(){
        apiServices = new Retrofit.Builder()//khởi tạo dối tượng Restrofit thông qua Restrofit.Build
                .baseUrl(BASE_URL)//cáu hình các thông số như base URL
                .addConverterFactory(GsonConverterFactory.create())//chuyển đổi đối tượng gson sang dối tượng
                .build().create(ApiServices.class);
    }
    public ApiServices callAPI(){
        return apiServices;
    }
}

