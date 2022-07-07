package com.example.mysearchgithubuserapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> {
    private ArrayList<ModelSearchData> modelSearchDataList= new ArrayList<>();
    private Context context;

    public SearchAdapter(Context context) {
        this.context = context;
    }

    public void setSearchUserList(ArrayList<ModelSearchData> items) {
        modelSearchDataList.clear();
        modelSearchDataList.addAll(items);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_data, parent, false);
        return new SearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SearchViewHolder holder, int position) {
        ModelSearchData item = modelSearchDataList.get(position);

        Glide.with(holder.itemView.getContext())
                .load(item.getAvatarUrl())
                .into(holder.imageUser);

        holder.tvUsername.setText(item.getLogin());
        holder.layout.setOnClickListener(view -> {
            Intent intent = new Intent(context, DetailUserActivity.class);
            intent.putExtra("USERNAME", modelSearchDataList.get(position).getLogin());
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return modelSearchDataList.size();
    }

    public static class SearchViewHolder extends RecyclerView.ViewHolder {

        TextView tvUsername;
        ImageView imageUser;
        LinearLayout layout;

        public SearchViewHolder(View itemView) {
            super(itemView);
            layout = itemView.findViewById(R.id.layout);
            tvUsername = itemView.findViewById(R.id.tvUsername);
            imageUser = itemView.findViewById(R.id.imageUser);
        }
    }
}
