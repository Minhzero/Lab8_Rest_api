package minhnqph38692.fpoly.dethithu;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiServices {
    public static String BASE_URL="http://10.0.2.2:3000/api/";
    @GET("get-list-student")
    Call<Response<ArrayList<StudentDTO>>> getListStudent();
    @GET("search-student")
    Call<Response<ArrayList<StudentDTO>>> searchStudent(@Query("key") String key);

    @DELETE("delete-student/{id}")
    Call<Response<StudentDTO>> deleteStudentById(@Path("id") String id);

    @POST("add-student")
    Call<Response<StudentDTO>> addStudent(@Body StudentDTO studentDTO);

    @PUT("update-student/{id}")
    Call<Response<StudentDTO>> updateStudentById(@Path("id") String id,@Body StudentDTO studentDTO);


}
