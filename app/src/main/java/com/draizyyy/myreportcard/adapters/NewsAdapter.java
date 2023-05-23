package com.draizyyy.myreportcard.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.draizyyy.myreportcard.R;
import com.draizyyy.myreportcard.pojos.News;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.MyViewHolder> {

    private final List<News> list;

    public NewsAdapter(List<News> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.new_s,
                parent,
                false
        );
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsAdapter.MyViewHolder holder, int position) {
        final News news = list.get(position);
        holder.authorName.setText(news.authorName);
        holder.date.setText(news.date);
        holder.text.setText(news.newsText);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        private final TextView authorName;
        private final TextView date;
        private final TextView text;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            authorName = itemView.findViewById(R.id.author_name);
            date = itemView.findViewById(R.id.date_of_post);
            text = itemView.findViewById(R.id.text_of_post);
        }
    }
}
