package com.example.minlifeapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.minlifeapp.R;
import com.example.minlifeapp.entity.CommentEntity;
import java.util.ArrayList;

public class CommentAdapter  extends BaseAdapter {

    Context mcontext;
    Activity activity;
    ArrayList<CommentEntity> commentEntities;
    LayoutInflater mInflater;
    public CommentAdapter ( Context context,Activity activity, ArrayList<CommentEntity> commentEntities){
        this.mcontext = context;
        this.activity = activity;
        this.commentEntities = commentEntities;
        mInflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return commentEntities.size();
    }

    @Override
    public Object getItem(int i) {
        return commentEntities.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    TextView comment,username , updatetime;
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = mInflater.inflate(R.layout.item_comment_layout,null);

        comment = view.findViewById(R.id.tv_name);
        username = view.findViewById(R.id.tv_username);
        updatetime =  view.findViewById(R.id.tv_price);

        comment.setText(commentEntities.get(i).getComment());
        username.setText(commentEntities.get(i).getUsername());
        updatetime.setText(commentEntities.get(i).getUpdatetime());
        return  view;
    }
}
