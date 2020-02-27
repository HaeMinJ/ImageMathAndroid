package com.haemin.imagemathstudent.View.UI;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.haemin.imagemathstudent.R;

public class TutorToolbar extends Toolbar {

    @BindView(R.id.btn_setting)
    ImageButton btnSetting;
    @BindView(R.id.btn_notification)
    ImageButton btnNotification;

    OnClickListener settingOnClickListener;
    OnClickListener notificationOnClickListener;

    public void setSettingOnClickListener(OnClickListener settingOnClickListener) {
        this.settingOnClickListener = settingOnClickListener;

        if(settingOnClickListener == null){
            settingOnClickListener = new OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            };
        }
        btnSetting.setOnClickListener(settingOnClickListener);

    }

    public void setNotificationOnClickListener(OnClickListener notificationOnClickListener) {
        this.notificationOnClickListener = notificationOnClickListener;

        if(notificationOnClickListener == null){
            notificationOnClickListener = new OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            };
        }
        btnNotification.setOnClickListener(notificationOnClickListener);
    }

    public TutorToolbar(Context context) {
        super(context);
        init(context);
    }

    public TutorToolbar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public TutorToolbar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }
    void init(Context context){
        View toolbar = LayoutInflater.from(context).inflate(R.layout.toolbar, this, false);
        addView(toolbar);
        ButterKnife.bind(this,toolbar);


    }
}
