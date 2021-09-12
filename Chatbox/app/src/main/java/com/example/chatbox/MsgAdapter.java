package com.example.chatbox;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MsgAdapter extends RecyclerView.Adapter<MsgAdapter.ViewHolder> {
    private List<Msg> myMsgList;

    @NonNull
    @Override
    public MsgAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType == Msg.TYPE_RECEIVED) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.left_layout, parent, false);
           // Log.e("zoe", "left view.toString():" + view.toString());
            return new ViewHolder(view);
        }
        else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.right_layout, parent, false);
           // Log.e("zoe", "right view.toString():" + view.toString());
            return new ViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Msg msg = myMsgList.get(position);
        if(msg.getType() == Msg.TYPE_RECEIVED) {
           // holder.left_layout.setVisibility(View.VISIBLE);
          //  holder.right_layout.setVisibility(View.GONE);
            holder.left_msg.setText(msg.getContent());
        }
        else {
          //  holder.left_layout.setVisibility(View.GONE);
          //  holder.right_layout.setVisibility(View.VISIBLE);
            holder.right_msg.setText(msg.getContent());
        }
    }

    @Override
    public int getItemViewType(int position) {
        Msg msg = myMsgList.get(position);
        return msg.getType();
    }

    @Override
    public int getItemCount() {
        return myMsgList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        TextView left_msg;
        TextView right_msg;
        //LinearLayout left_layout;
        //LinearLayout right_layout;

        public ViewHolder(View itemView) {
            super(itemView);

           // left_layout = (LinearLayout) itemView.findViewById(R.id.left_layout);
           // right_layout = (LinearLayout) itemView.findViewById(R.id.right_layout);
            left_msg = (TextView) itemView.findViewById(R.id.left_msg);
            right_msg = (TextView) itemView.findViewById(R.id.right_msg);
        }
    }

    public MsgAdapter(List<Msg> mMsgList) {
        myMsgList = mMsgList;
    }
}
