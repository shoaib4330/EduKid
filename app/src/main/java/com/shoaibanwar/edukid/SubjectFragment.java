package com.shoaibanwar.edukid;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.shoaibanwar.edukid.model.Lesson;

import java.util.ArrayList;
import java.util.List;

public class SubjectFragment extends Fragment {

    private static final String ARG_PARAM1 = "LessonsList";

    private RecyclerView recyclerView;
    private List<Lesson> lessonList;
    private LessonRecyclerAdapter adapter;

    public SubjectFragment() {
        // Required empty public constructor
    }

    public static SubjectFragment newInstance(ArrayList<Lesson> param1) {
        SubjectFragment fragment = new SubjectFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            lessonList = (List<Lesson>) getArguments().getSerializable(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_subject, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView_subjectFrag);
        adapter = new LessonRecyclerAdapter((ArrayList<Lesson>) lessonList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        return view;
    }


    //Adapter implementation starts here......
    private class LessonRecyclerAdapter extends RecyclerView.Adapter<LessonRecyclerAdapter.CustomViewHolder>{

        private List<Lesson> lessonList;

        public LessonRecyclerAdapter(ArrayList<Lesson> gList){
            this.lessonList = gList;
        }

        public void setLessonList(ArrayList<Lesson> gList){
            this.lessonList = gList;
        }

        public class CustomViewHolder extends RecyclerView.ViewHolder{
            public ImageView imageView;
            public TextView textView;

            public CustomViewHolder(View itemView) {
                super(itemView);

                imageView = (ImageView) itemView.findViewById(R.id.lessonImage_LessonView);
                textView = (TextView) itemView.findViewById(R.id.lessonTV_LessonView);

                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(SubjectFragment.this.getActivity(),DetailActivity.class);
                        intent.putExtra("LessonPosition",getLayoutPosition());
                        startActivity(intent);
                    }
                });
            }
        }

        @Override
        public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.lesson_view,parent,false);
            return new CustomViewHolder(view);
        }

        @Override
        public void onBindViewHolder(CustomViewHolder holder, int position) {

            Lesson lesson = lessonList.get(position);

            MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
            mediaMetadataRetriever.setDataSource(SubjectFragment.this.getContext(),Uri.parse(lesson.getAnimation_Uri()));
            Bitmap frame = mediaMetadataRetriever.getFrameAtTime(2000);

            holder.textView.setText(lesson.getName());
            holder.imageView.setImageBitmap(frame);
            holder.imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        }

        @Override
        public int getItemCount() {
            return lessonList.size();
        }
    }

}
