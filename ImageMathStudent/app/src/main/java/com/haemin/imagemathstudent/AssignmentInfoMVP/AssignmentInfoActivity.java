package com.haemin.imagemathstudent.AssignmentInfoMVP;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.esafirm.imagepicker.features.ImagePicker;
import com.esafirm.imagepicker.model.Image;
import com.haemin.imagemathstudent.Data.StudentAssignment;
import com.haemin.imagemathstudent.R;
import com.haemin.imagemathstudent.Utils.ConfirmStarter;
import com.haemin.imagemathstudent.View.UI.FileButton;

import java.util.List;

public class AssignmentInfoActivity extends AppCompatActivity implements AssignmentInfoContract.AssignmentInfoView {

    String assignmentSeq;
    AssignmentInfoPresenter presenter;
    @BindView(R.id.img_submit_state)
    TextView textSubmitState;
    @BindView(R.id.text_assignment_name)
    TextView textAssignmentName;
    @BindView(R.id.text_lecture_name)
    TextView textLectureName;
    @BindView(R.id.text_assignment_notice)
    TextView textAssignmentNotice;
    @BindView(R.id.text_overlay_file)
    TextView textOverlayFile;
    @BindView(R.id.btn_answer_file)
    FileButton btnAnswerFile;
    @BindView(R.id.text_upload_day)
    TextView textUploadDay;
    @BindView(R.id.text_end_day)
    TextView textEndDay;
    @BindView(R.id.text_lecture_day)
    TextView textLectureDay;
    @BindView(R.id.btn_add_submit)
    Button btnAddSubmit;
    @BindView(R.id.recycler_submit)
    RecyclerView recyclerSubmit;


    public static void start(Context context, String assignmentSeq) {
        Intent starter = new Intent(context, AssignmentInfoActivity.class);
        starter.putExtra("lectureSeq", assignmentSeq);
        context.startActivity(starter);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment_info);
        Intent i = getIntent();
        ConfirmStarter.checkIntent(this, i);
        assignmentSeq = i.getStringExtra("lectureSeq");
        ButterKnife.bind(this);
        presenter = new AssignmentInfoPresenter(this);
        presenter.updateData(assignmentSeq);

    }

    @Override
    public void updateView(StudentAssignment studentAssignment) {
        switch (studentAssignment.getSubmitState()){
            case 0:
                textSubmitState.setText("미제출");
                textSubmitState.setTextColor(getResources().getColor(android.R.color.black));
                textOverlayFile.setVisibility(View.VISIBLE);
                break;
            case 1:
                textSubmitState.setText("제출");
                textSubmitState.setTextColor(getResources().getColor(R.color.etoos_color));
                textOverlayFile.setVisibility(View.GONE);
                btnAnswerFile.setOnClickListener(v -> {
                    presenter.downloadFile(studentAssignment.getAssignment().getSolutionFileUrl());
                });
                break;
            case 2:
                textSubmitState.setText("불성실");
                textSubmitState.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
                textOverlayFile.setVisibility(View.VISIBLE);
                break;
            case 3:
                textSubmitState.setText("예외");
                textSubmitState.setTextColor(getResources().getColor(android.R.color.darker_gray));
                textOverlayFile.setVisibility(View.VISIBLE);
                break;
        }
        textLectureName.setText(studentAssignment.getAssignment().getLectureName());
        textAssignmentName.setText(studentAssignment.getAssignment().getTitle());
        textAssignmentNotice.setText(studentAssignment.getAssignment().getContents());
        textEndDay.setText(studentAssignment.getAssignment().getEndDate());
        textLectureDay.setText(studentAssignment.getAssignment().getLectureDate());
        textUploadDay.setText(studentAssignment.getAssignment().getLectureDate());
        btnAddSubmit.setOnClickListener(v -> {
            ImagePicker.create(this)
                    .start();
        });
    }

    @Override
    public void showToast(String text) {
        Toast.makeText(this,text,Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, final int resultCode, Intent data) {
        if (ImagePicker.shouldHandle(requestCode, resultCode, data)) {
            // Get a list of picked images
            Image image = ImagePicker.getFirstImageOrNull(data);
            presenter.submitPicture(assignmentSeq, image);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


}
