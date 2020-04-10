package com.haemin.imagemathtutor.StudentManageMVP;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.haemin.imagemathtutor.Data.Lecture;
import com.haemin.imagemathtutor.Data.User;
import com.haemin.imagemathtutor.R;
import com.haemin.imagemathtutor.Utils.ConfirmStarter;
import java.util.ArrayList;

public class StudentManageActivity extends AppCompatActivity implements StudentManageContract.StudentManageView {

    String lectureSeq;
    String lectureName;

    StudentManageAdapter adapter;
    ArrayList<User> students;

    @BindView(R.id.btn_back)
    ImageButton btnBack;
    @BindView(R.id.text_lecture_name)
    TextView textLectureName;
    @BindView(R.id.recycler_student_manage)
    RecyclerView recyclerStudentManage;
    @BindView(R.id.btn_delete_students)
    Button btnDeleteStudents;

    StudentManagePresenter presenter;


    public static void start(Context context, Lecture lecture) {
        Intent starter = new Intent(context, StudentManageActivity.class);
        starter.putExtra("lectureSeq",lecture.getLectureSeq());
        starter.putExtra("lectureName",lecture.getName());
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_manage);
        ButterKnife.bind(this);

        {
            Intent fromOutside = getIntent();
            ConfirmStarter.checkIntent(this, fromOutside);
            presenter = new StudentManagePresenter(this);
            lectureSeq = fromOutside.getStringExtra("lectureSeq");
            lectureName = fromOutside.getStringExtra("lectureName");
        }

        {
            students = new ArrayList<>();
            adapter = new StudentManageAdapter(this, students);
        }

        {
            recyclerStudentManage.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
            recyclerStudentManage.setAdapter(adapter);
        }
        {
            btnBack.setOnClickListener(v -> finish());
            textLectureName.setText(lectureName);
            presenter.requestStudentList(lectureSeq);
            btnDeleteStudents.setOnClickListener(v -> {
                for(User user : students){
                    if(user.isChecked())presenter.requestDeleteStudent(lectureSeq,user.getUserSeq());
                }
                presenter.requestStudentList(lectureSeq);
            });
        }

    }

    @Override
    public void updateStudentList(ArrayList<User> students) {
        this.students.clear();
        this.students.addAll(students);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showToast(String text) {
        Toast.makeText(this,text,Toast.LENGTH_SHORT).show();
    }
}
