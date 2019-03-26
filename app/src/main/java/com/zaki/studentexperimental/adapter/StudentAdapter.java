package com.zaki.studentexperimental.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.ads.AdView;
import com.zaki.studentexperimental.R;
import com.zaki.studentexperimental.activity.MainActivity;
import com.zaki.studentexperimental.model.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentAdapter  extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private List<Object> recyclerList=new ArrayList<>();
    private Context context;

    private final static int ITEM_STUDENT_DATA=0;
    private final static int ITEM_AD_DATA=1;

    public StudentAdapter(List<Object> recyclerList, Context context) {
        this.recyclerList = recyclerList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        switch(viewType){
            case ITEM_AD_DATA:
                View bannerAd=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.ads_view,viewGroup,false);
                return new ADHolder(bannerAd);
            default:
                View studentItem= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.student_details,viewGroup,false);
                return new StudentHolder(studentItem);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        int viewType =getItemViewType(i);

        switch (viewType){
            case ITEM_AD_DATA:
                ADHolder adHolder=(ADHolder) viewHolder;
                AdView adView=(AdView) recyclerList.get(i);

                ViewGroup adCardView=(ViewGroup) adHolder.itemView;

                if(adCardView.getChildCount()>0){
                    adCardView.removeAllViews();
                }

                if (adCardView.getParent()!=null){
                    ((ViewGroup) adView.getParent()).removeView(adView);
                }

                adCardView.addView(adView);
                break;
            default:
                StudentHolder studentHolder=(StudentHolder) viewHolder;
                Student student=(Student)   recyclerList.get(i);

                studentHolder.ivStudent.setImageResource(R.drawable.ic_profile_image);
                studentHolder.tvStudentName.setText(student.getName());
                studentHolder.tvStudentAge.setText(String.valueOf(student.getAge()));
        }
    }

    @Override
    public int getItemCount() {
        return recyclerList.size();
    }

    @Override
    public int getItemViewType(int position) {

        if(position%MainActivity.RANDOM_COUNT==0)
            return ITEM_AD_DATA;
        else
            return ITEM_STUDENT_DATA;
    }

    public static class StudentHolder extends RecyclerView.ViewHolder{

        private ImageView ivStudent;
        private TextView tvStudentName;
        private TextView tvStudentAge;

        public StudentHolder(@NonNull View itemView) {
            super(itemView);
            ivStudent=itemView.findViewById(R.id.ivStudentImage);
            tvStudentName=itemView.findViewById(R.id.tv_name);
            tvStudentAge=itemView.findViewById(R.id.tv_age);
        }
    }

    public static class ADHolder extends RecyclerView.ViewHolder{

        public ADHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

}
