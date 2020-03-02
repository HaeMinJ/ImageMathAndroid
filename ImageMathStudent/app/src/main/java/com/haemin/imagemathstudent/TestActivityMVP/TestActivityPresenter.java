package com.haemin.imagemathstudent.TestActivityMVP;

import android.util.Log;
import com.haemin.imagemathstudent.Data.StudentTest;
import com.haemin.imagemathstudent.SingleTon.AppString;
import com.haemin.imagemathstudent.SingleTon.GlobalApplication;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TestActivityPresenter implements TestActivityContract.TestActivityPresenter {
    TestActivityContract.TestActivityView testView;

    public TestActivityPresenter(TestActivityContract.TestActivityView testView) {
        this.testView = testView;
    }

    @Override
    public void updateData(String testSeq) {
        GlobalApplication.getAPIService().getStudentTestInfo(GlobalApplication.getAccessToken(),testSeq)
                .enqueue(new Callback<StudentTest>() {
                    @Override
                    public void onResponse(Call<StudentTest> call, Response<StudentTest> response) {
                        if(response.code() == 200 && response.body() != null){
                            testView.updateView(response.body());
                        }else{
                            testView.showToast(AppString.ERROR_LOAD_LECTURE_LIST);
                        }
                    }

                    @Override
                    public void onFailure(Call<StudentTest> call, Throwable t) {
                        testView.showToast(AppString.ERROR_NETWORK_MESSAGE);
                        Log.e("TestActivityPresenter",t.getMessage(),t);
                    }
                });
    }

    @Override
    public void downloadAnswer() {

    }
}
