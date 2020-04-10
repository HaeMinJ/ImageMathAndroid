package com.haemin.imagemathstudent.TestFragmentMVP;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.haemin.imagemathstudent.Data.Lecture;
import com.haemin.imagemathstudent.Data.StudentTest;
import com.haemin.imagemathstudent.R;
import com.haemin.imagemathstudent.View.UI.ListPickerDialog;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class TestFragment extends Fragment implements TestFragmentContract.TestFragmentView {


    @BindView(R.id.text_lecture_name)
    TextView textLectureName;
    @BindView(R.id.test_chart)
    LineChart testChart;
    @BindView(R.id.text_recent_my_score)
    TextView textAverageRank;
    @BindView(R.id.text_average_score)
    TextView textAverageScore;
    @BindView(R.id.text_recent_rank)
    TextView textRecentScore;
    @BindView(R.id.recycler_test)
    RecyclerView recyclerView;

    TestFragmentContract.TestFragmentPresenter presenter;
    TestAdapter testAdapter;
    ArrayList<StudentTest> studentTests;


    public TestFragment() {
        // Required empty public constructor
    }

    @Override
    public void showToast(String text) {
        Toast.makeText(getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showDialog(ArrayList<Lecture> lectures) {
        ListPickerDialog<Lecture> lectureListPickerDialog = new ListPickerDialog<>(lectures, "수업을 선택해주세요.");
        lectureListPickerDialog.setOnItemClickListener(data -> {
            presenter.updateData(data.getLectureSeq()+"");
            updateLectureName(data.getName());
            lectureListPickerDialog.dismiss();
        });
        lectureListPickerDialog.show(getFragmentManager(), "TAG");

    }

    @Override
    public void updateChart(ArrayList<StudentTest> studentTests) {
        this.studentTests.clear();
        this.studentTests.addAll(studentTests);
        ArrayList<ILineDataSet> dataSets = new ArrayList<>();

        ArrayList<Entry> scoreEntries = new ArrayList<>();
        for (int i = 0; i < studentTests.size(); i++) {
            Entry entry = new Entry(i, studentTests.get(i).getScore());
            scoreEntries.add(entry);
        }

        ArrayList<Entry> averageEntries = new ArrayList<>();
        for (int i = 0; i < studentTests.size(); i++) {
            Entry entry = new Entry(i, studentTests.get(i).getAverageScore());
            averageEntries.add(entry);
        }

        LineDataSet d = new LineDataSet(scoreEntries, "내 점수");
        d.setLineWidth(2.5f);
        d.setCircleRadius(4f);
        d.setColor(R.color.etoos_color);
        d.setCircleColor(R.color.etoos_color);

        LineDataSet d2 = new LineDataSet(averageEntries, "평균 점수");
        d2.setLineWidth(2.5f);
        d2.setCircleRadius(4f);
        d2.setColor(R.color.ef_grey);
        d2.setCircleColor(R.color.ef_grey);

        dataSets.add(d);
        dataSets.add(d2);

        ((LineDataSet) dataSets.get(0)).enableDashedLine(10, 10, 0);
        ((LineDataSet) dataSets.get(0)).setColors(ColorTemplate.VORDIPLOM_COLORS);
        ((LineDataSet) dataSets.get(0)).setCircleColors(ColorTemplate.VORDIPLOM_COLORS);

        LineData data = new LineData(dataSets);
        testChart.setData(data);
        testChart.invalidate();


    }

    @Override
    public void updateRecycler(ArrayList<StudentTest> studentTests) {
        this.studentTests.clear();
        this.studentTests.addAll(studentTests);
        testAdapter.notifyDataSetChanged();
    }

    @Override
    public void updateAverages(String averageRank, String averageScore, String recentScore) {
        textAverageRank.setText(averageRank + "등");
        textAverageScore.setText(averageScore + "점");
        textRecentScore.setText(recentScore + "점");
    }

    @Override
    public void updateLectureName(String lectureName) {
        textLectureName.setText(lectureName);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        studentTests = new ArrayList<>();
        View v = inflater.inflate(R.layout.fragment_test, container, false);
        ButterKnife.bind(this, v);
        presenter = new TestFragmentPresenter(this);
        testAdapter = new TestAdapter(getContext(), studentTests);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        recyclerView.setAdapter(testAdapter);

        textLectureName.setOnClickListener(v1 -> {
            presenter.requestLecturePick();
        });
        return v;
    }


}
