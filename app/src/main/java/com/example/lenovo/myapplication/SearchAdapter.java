package com.example.lenovo.myapplication;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> {
    Context context;
    ArrayList<String> ALaddress;
    ArrayList<String> ALcell;
    ArrayList<String> ALlicense;
    ArrayList<String> ALfullname;


    class SearchViewHolder extends RecyclerView.ViewHolder{

        ImageView scImage;
        TextView scfullname , scaddress , sccell , sclicense;

        public SearchViewHolder(View itemView) {
            super(itemView);
            scImage = (ImageView)itemView.findViewById(R.id.sl_profileImage);
            scfullname =(TextView)itemView.findViewById(R.id.sl_fullname);
            scaddress =(TextView)itemView.findViewById(R.id.sl_address);
            sccell =(TextView)itemView.findViewById(R.id.sl_cell);
            sclicense =(TextView)itemView.findViewById(R.id.sl_license);
        }
    }


    public SearchAdapter(Context context, ArrayList<String> ALfullname , ArrayList<String> ALaddress, ArrayList<String> ALcell, ArrayList<String> ALlicense) {
        this.context = context;
        this.ALaddress = ALaddress;
        this.ALcell = ALcell;
        this.ALlicense = ALlicense;
        this.ALfullname = ALfullname;
    }

    @Override
    public SearchAdapter.SearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.search_list_items , parent , false );
        return new SearchAdapter.SearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SearchViewHolder holder, int position) {
        holder.scfullname.setText(ALfullname.get(position));
        holder.scaddress.setText(ALaddress.get(position));
        holder.sccell.setText(ALcell.get(position));
        holder.sclicense.setText(ALlicense.get(position));

        Glide.with(context).load(ALcell.get(position)).asBitmap().placeholder(R.mipmap.ic_launcher_round).into(holder.scImage);
    }



    @Override
    public int getItemCount() {
        return ALfullname.size();
    }
}
