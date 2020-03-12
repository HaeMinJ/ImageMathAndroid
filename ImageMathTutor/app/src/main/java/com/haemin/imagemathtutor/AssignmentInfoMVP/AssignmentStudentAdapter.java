package com.haemin.imagemathtutor.AssignmentInfoMVP;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.ceylonlabs.imageviewpopup.ImagePopup;
import com.haemin.imagemathtutor.AppString;
import com.haemin.imagemathtutor.Data.Assignment;
import com.haemin.imagemathtutor.Data.ServerFile;
import com.haemin.imagemathtutor.Data.StudentAssignment;
import com.haemin.imagemathtutor.Data.User;
import com.haemin.imagemathtutor.GlobalApplication;
import com.haemin.imagemathtutor.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;

public class AssignmentStudentAdapter extends RecyclerView.Adapter<AssignmentStudentAdapter.StudentViewHolder> {
    Context context;
    ArrayList<StudentAssignment> studentAssignments;
    Assignment mainAssignment;

    public AssignmentStudentAdapter(Context context, ArrayList<StudentAssignment> studentAssignments, Assignment assignment) {
        this.context = context;
        this.studentAssignments = studentAssignments;
        mainAssignment = assignment;
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.recycler_student_assignment,parent,false);
        return new StudentViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
        StudentAssignment assignment = studentAssignments.get(position);

        switch (assignment.getSubmitState()){
            case 0:
                holder.textAuthStatus.setText("미제출");
                holder.textAuthStatus.setTextColor(context.getResources().getColor(android.R.color.black));
                break;
            case 1:
                holder.textAuthStatus.setText("제출");
                holder.textAuthStatus.setTextColor(context.getResources().getColor(R.color.yellow));

                break;
            case 2:
                holder.textAuthStatus.setText("불성실");
                holder.textAuthStatus.setTextColor(context.getResources().getColor(android.R.color.holo_red_dark));
                break;
            case 3:
                holder.textAuthStatus.setText("통과");
                holder.textAuthStatus.setTextColor(context.getResources().getColor(R.color.etoos_color));
                break;
        }
        if((assignment.getSubmitState() == 1 || assignment.getSubmitState() == 3)){
            GlobalApplication.getAPIService().getAssignmentSubmitFiles(GlobalApplication.getAccessToken(),assignment.getAssignmentSeq()+"",assignment.getUserSeq()).enqueue(new Callback<ArrayList<ServerFile>>() {
                @Override
                public void onResponse(Call<ArrayList<ServerFile>> call, Response<ArrayList<ServerFile>> response) {
                    if(response.code() == 200 && response.body() != null){
                        assignment.getSubmitFiles().addAll(response.body());
                        ImagePopup imagePopup = new ImagePopup(context);
                        imagePopup.setBackgroundColor(Color.BLACK);  // Optional
                        imagePopup.setFullScreen(false); // Optional
                        imagePopup.setHideCloseIcon(false);  // Optional
                        imagePopup.setImageOnClickClose(false);  // Optional
                        imagePopup.initiatePopupWithGlide(assignment.getSubmitFiles().get(0).getFileUrl());

                        holder.textCheckSubmit.setOnClickListener(v -> {
                            imagePopup.viewPopup();
                        });
                    }else{
                        Toast.makeText(context,"제출된 과제정보를 불러올 수 없습니다.",Toast.LENGTH_SHORT).show();
                        Log.e("AssignmentStudentAdp",response.message());
                    }
                }

                @Override
                public void onFailure(Call<ArrayList<ServerFile>> call, Throwable t) {
                    Log.e("AssignmentStudentAdp",t.getMessage(),t);
                }
            });

        }else{
            holder.textCheckSubmit.setOnClickListener(v -> {
                Toast.makeText(context,"학생이 과제를 제출하지 않았습니다.",Toast.LENGTH_SHORT).show();
            });
        }

        GlobalApplication.getAPIService().getUserInfo(GlobalApplication.getAccessToken(),assignment.getUserSeq()).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.code() == 200 && response.body() != null){
                    User user = response.body();
                    holder.textStudentName.setText(user.getName());
                    holder.textStudentSeq.setText(user.getUserSeq());

                    if (mainAssignment.getEndTime() > System.currentTimeMillis()) {
                        holder.imageAuthStatus.setImageDrawable(context.getDrawable(R.drawable.icon_neglect));
                    }
                }else{
                    Toast.makeText(context, AppString.ERROR_LOAD_USER_LIST,Toast.LENGTH_SHORT).show();
                    Log.e("AssignmentStudentAdapt",response.message());
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(context, AppString.ERROR_LOAD_USER_LIST,Toast.LENGTH_SHORT).show();
                Log.e("AssignmentStudentAdapt",t.getMessage(),t);
            }
        });
    }

    @Override
    public int getItemCount() {
        return studentAssignments.size();
    }

    class StudentViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.text_auth_status)
        TextView textAuthStatus;
        @BindView(R.id.img_auth_status)
        ImageView imageAuthStatus;
        @BindView(R.id.text_student_name)
        TextView textStudentName;
        @BindView(R.id.text_student_seq)
        TextView textStudentSeq;
        @BindView(R.id.text_check_submit)
        TextView textCheckSubmit;

        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
