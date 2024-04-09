package minhnqph38692.fpoly.dethithu;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class MainActivity extends AppCompatActivity {

    HttpRequest httpRequest;
    RecyclerView recyclerView;
    StudentAdapter adapter;
    TextInputEditText edtTimKiem;
    FloatingActionButton add;
    Item_student_Handle handle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycview);
        edtTimKiem=findViewById(R.id.edtTimKiem);
        add= findViewById(R.id.add);
        httpRequest = new HttpRequest();

        handle = new Item_student_Handle() {
            @Override
            public void Delete(String id) {
                DeleteD(id);
            }

            @Override
            public void Update(String id, StudentDTO studentDTO) {
                UpdateS(id,studentDTO);
            }
        };
        httpRequest.callAPI()
                .getListStudent()
                .enqueue(getStudentAPI);

        edtTimKiem.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_SEARCH){
                    String key = edtTimKiem.getText().toString();

                    httpRequest.callAPI()
                            .searchStudent(key)
                            .enqueue(getStudentAPI);
                    return true;
                }
                return false;
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Add();
            }
        });
    }
    private  void getData(ArrayList<StudentDTO> list){
        adapter=new StudentAdapter(this,list,handle);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }
    Callback<Response<ArrayList<StudentDTO>>> getStudentAPI = new Callback<Response<ArrayList<StudentDTO>>>() {

        @Override
        public void onResponse(Call<Response<ArrayList<StudentDTO>>> call, retrofit2.Response<Response<ArrayList<StudentDTO>>> response) {
            if(response.isSuccessful()){
                if(response.body().getStatus() == 200){
                    ArrayList<StudentDTO> list = response.body().getData();
                    getData(list);
                }
            }
        }

        @Override
        public void onFailure(Call<Response<ArrayList<StudentDTO>>> call, Throwable t) {
            Log.d(">>> GetListDitributor", " onFailure"+t.getMessage());
        }
    };

    public void  DeleteD(String id){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Thông báo!");
        builder.setMessage("Bạn có chắc xóa?");
        AlertDialog dialog = builder.create();

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                httpRequest.callAPI()
                        .deleteStudentById(id)
                        .enqueue(responDistributorAPI);
                Toast.makeText(MainActivity.this, "Xoas thanh cong", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.show();

    }

    public  void Add(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View v = inflater.inflate(R.layout.layout_them,null);
        builder.setView(v);
//        builder.setCancelable(false);
        AlertDialog dialog = builder.create();
        TextInputEditText ed_themten= v.findViewById(R.id.ed_ten);
        TextInputEditText ed_themque= v.findViewById(R.id.ed_que);
        TextInputEditText ed_themdiem= v.findViewById(R.id.ed_diem);
        TextInputEditText ed_themanh= v.findViewById(R.id.ed_anh);

        Button btn_themten = v.findViewById(R.id.btn_them);

        btn_themten.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ten = ed_themten.getText().toString();
                String que = ed_themque.getText().toString();
                String diem = ed_themdiem.getText().toString();
                String anh = ed_themanh.getText().toString();

                StudentDTO distributor = new StudentDTO();
                distributor.setHo_ten_ph38692(ten);
                distributor.setQue_quan_ph38692(que);
                distributor.setDiem_ph38692(diem);
                distributor.setHinh_anh_ph38692(anh);

                httpRequest.callAPI()
                        .addStudent(distributor)
                        .enqueue(responDistributorAPI);
                Toast.makeText(MainActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        dialog.show();

    }

    public  void UpdateS(String id, StudentDTO studentDTO  ){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View v = inflater.inflate(R.layout.layout_them,null);
        builder.setView(v);
//        builder.setCancelable(false);
        AlertDialog dialog = builder.create();
        TextInputEditText ed_themten= v.findViewById(R.id.ed_ten);
        TextInputEditText ed_themque= v.findViewById(R.id.ed_que);
        TextInputEditText ed_themdiem= v.findViewById(R.id.ed_diem);
        TextInputEditText ed_themanh= v.findViewById(R.id.ed_anh);
        Button btn_updateten = v.findViewById(R.id.btn_them);
        ed_themten.setText(studentDTO.getHo_ten_ph38692());
        ed_themque.setText(studentDTO.getQue_quan_ph38692());
        ed_themdiem.setText(studentDTO.getDiem_ph38692());


        btn_updateten.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ten = ed_themten.getText().toString();
//                 distributor = new Distributor();
                studentDTO.setHo_ten_ph38692(ten);
                httpRequest.callAPI()
                        .updateStudentById(id,studentDTO)
                        .enqueue(responDistributorAPI);
                Toast.makeText(MainActivity.this, "upadte thành công", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        dialog.show();

    }

    Callback<Response<StudentDTO>> responDistributorAPI = new Callback<Response<StudentDTO>>() {
        @Override
        public void onResponse(Call<Response<StudentDTO>> call, retrofit2.Response<Response<StudentDTO>> response) {
            if(response.isSuccessful()){
                if(response.body().getStatus() == 200){
                    httpRequest.callAPI()
                            .getListStudent()
                            .enqueue(getStudentAPI);
//                    Toast.makeText(MainActivity.this, response.body().getMessenger(), Toast.LENGTH_SHORT).show();
                }
            }
        }

        @Override
        public void onFailure(Call<Response<StudentDTO>> call, Throwable t) {
            Log.d(">>> GetListDitributor", " onFailure"+t.getMessage());
        }
    };
}