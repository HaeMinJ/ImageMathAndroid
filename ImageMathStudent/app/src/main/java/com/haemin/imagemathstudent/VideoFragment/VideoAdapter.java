package com.haemin.imagemathstudent.VideoFragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Build;
import android.text.format.DateUtils;
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
import com.bumptech.glide.Glide;
import com.haemin.imagemathstudent.Data.ServerFile;
import com.haemin.imagemathstudent.Data.Video;
import com.haemin.imagemathstudent.R;
import com.haemin.imagemathstudent.SingleTon.AppString;
import com.haemin.imagemathstudent.SingleTon.GlobalApplication;
import com.haemin.imagemathstudent.VideoDetailView.VideoPlayActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.ViewHolder> {
    Context context;
    ArrayList<Video> videos;

    public VideoAdapter(Context context, ArrayList<Video> videos) {
        this.context = context;
        this.videos = videos;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.recycler_video, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Video video = videos.get(position);

        holder.textTitle.setText(video.getTitle());
        holder.textContents.setText(video.getContents());
        holder.textUpdateTime.setText(DateUtils.getRelativeTimeSpanString(context, video.getUploadTime()));

        GlobalApplication.getAPIService().getVideoFileList(GlobalApplication.getAccessToken(), ""+video.getVideoSeq())
                .enqueue(new Callback<ArrayList<ServerFile>>() {
                    @Override
                    public void onResponse(Call<ArrayList<ServerFile>> call, Response<ArrayList<ServerFile>> response) {
                        if(response.code() == 200 &&response.body() != null && response.body().size() > 0){
                            ServerFile videoFile = response.body().get(0);

                            Bitmap bitmap;
                            bitmap = retriveVideoFrameFromVideo(videoFile.getFileUrl());
                            if (bitmap != null) {
                                bitmap = Bitmap.createScaledBitmap(bitmap, 240, 240, false);
                                holder.imageThumbnail.setImageBitmap(bitmap);
                            }

                            holder.imageThumbnail.setOnClickListener(v -> VideoPlayActivity.start(context, videoFile.getFileUrl()));
                        }else{
                            Toast.makeText(context, "동영상 파일을 가져오는데 실패했습니다.", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<ServerFile>> call, Throwable t) {
                        Toast.makeText(context, AppString.ERROR_NETWORK_MESSAGE, Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public int getItemCount() {
        return videos.size();
    }
    public static Bitmap retriveVideoFrameFromVideo(String videoPath){
        Bitmap bitmap = null;
        MediaMetadataRetriever mediaMetadataRetriever = null;
        try {
            mediaMetadataRetriever = new MediaMetadataRetriever();
            if (Build.VERSION.SDK_INT >= 14)
                mediaMetadataRetriever.setDataSource(videoPath, new HashMap<String, String>());
            else
                mediaMetadataRetriever.setDataSource(videoPath);

            bitmap = mediaMetadataRetriever.getFrameAtTime(1, MediaMetadataRetriever.OPTION_CLOSEST);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("VideoAdapter", "Thumbnail Error!", new Throwable(
                    "Exception in retriveVideoFrameFromVideo(String videoPath)"
                            + e.getMessage()));

        } finally {
            if (mediaMetadataRetriever != null) {
                mediaMetadataRetriever.release();
            }
        }
        return bitmap;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.text_update_time)
        TextView textUpdateTime;
        @BindView(R.id.text_title)
        TextView textTitle;
        @BindView(R.id.image_thumbnail)
        ImageView imageThumbnail;
        @BindView(R.id.text_contents)
        TextView textContents;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
