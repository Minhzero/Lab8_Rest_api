package minhnqph38692.fpoly.dethithu;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class StudentAdapter extends  RecyclerView.Adapter<StudentAdapter.ViewHolder> {

Context context;
ArrayList<StudentDTO> list;
Item_student_Handle handle;

    public StudentAdapter(Context context, ArrayList<StudentDTO> list, Item_student_Handle handle) {
        this.context = context;
        this.list = list;
        this.handle = handle;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_student,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        StudentDTO studentDTO = list.get(position);
        holder.txtnameFr.setText("Tên:"+studentDTO.getHo_ten_ph38692());
        holder.txtque.setText("Quê Quán:"+studentDTO.getQue_quan_ph38692());
        holder.txtdiem.setText("Giá:"+studentDTO.getDiem_ph38692());

//        holder.txtdisId.setText("Id_Dis:"+fruit.getDistributor().getId());
//        Glide.with(context)
//                .load(fruit.getImage().get(0))
//                .thumbnail(Glide.with(context).load(fruit.getImage().get(0)))
//                .into(holder.imghinhFr);
//set hình ảnh
        Glide.with(context).load(studentDTO.getHinh_anh_ph38692()).into(holder.imghinhFr);
        holder.xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handle.Delete(list.get(position).get_id());
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handle.Update(list.get(position).get_id(),studentDTO);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public  static  class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtnameFr,txtque,txtdiem, xoa,sua;
        ImageView imghinhFr;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtnameFr=itemView.findViewById(R.id.name1);
            txtque=itemView.findViewById(R.id.que);
            txtdiem=itemView.findViewById(R.id.diem);
            sua=itemView.findViewById(R.id.sua);
            xoa=itemView.findViewById(R.id.xoa);


            imghinhFr=itemView.findViewById(R.id.anh);
        }
    }
}
