package com.uog.mhike.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.uog.mhike.R;
import com.uog.mhike.database.Hike;

import java.util.List;

public class HikeAdapter extends RecyclerView.Adapter<HikeAdapter.ViewHolder> {

    public interface ClickListener{
        public void onButtonClick(int position, View v, long id);
    }

    private static ClickListener listener;
    public void setListener(ClickListener listener){this.listener=listener;}
private List<Hike> hikeList;

public HikeAdapter(List<Hike> list){this.hikeList=list;}

    public void setHikeList(List<Hike> list){this.hikeList=list;}
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.hike_list_item,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Hike hike=hikeList.get(position);

        holder.lblhikename.setText(hike.getName());
        holder.lblhikelocation.setText(hike.getLocation());
        holder.lblhikedate.setText(hike.getDate());

    }

    @Override
    public int getItemCount() {
        return hikeList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView lblhikename, lblhikelocation, lblhikedate;
Button btnedit,btndetail,btndelete;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            lblhikename=itemView.findViewById(R.id.lblhikename);
            lblhikelocation=itemView.findViewById(R.id.lblhikelocation);
            lblhikedate=itemView.findViewById(R.id.lblhikedate);
            btnedit=itemView.findViewById(R.id.btnedit);
            btndetail=itemView.findViewById(R.id.btndetail);
            btndelete=itemView.findViewById(R.id.btndelete);


            btndetail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    listener.onButtonClick(getAdapterPosition(),view,R.id.btndetail);
                }
            });

            btnedit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onButtonClick(getAdapterPosition(),view,R.id.btnedit);
                }
            });

            btndelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onButtonClick(getAdapterPosition(),view,R.id.btndelete);
                }
            });
        }
    }
}
