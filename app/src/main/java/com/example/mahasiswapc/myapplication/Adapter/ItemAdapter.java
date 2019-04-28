package com.example.mahasiswapc.myapplication.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.mahasiswapc.myapplication.R;
import com.example.mahasiswapc.myapplication.model.getall.DataItem;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.Holder> {
    private Context context;
    private List<DataItem> list;
    private OnAdapterClickListener listener;

    public ItemAdapter(Context context, List<DataItem> list, OnAdapterClickListener listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ItemAdapter.Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.layout_item, viewGroup, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemAdapter.Holder holder, int i) {
        holder.bind(i, listener);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        private TextView tvName, tvDescription,tvId;
        private Button btnEdit, btnDelete;
        public Holder(@NonNull View itemView) {
            super(itemView);
            tvId = itemView.findViewById(R.id.tv_id);
            tvName = itemView.findViewById(R.id.tv_name);
            tvDescription = itemView.findViewById(R.id.tv_description);
            btnDelete = itemView.findViewById(R.id.btn_delete);
            btnEdit = itemView.findViewById(R.id.btn_edit);
        }

        public void bind(final int i, final OnAdapterClickListener listener) {
            tvId.setText(String.valueOf(list.get(i).getId()));
            tvName.setText(list.get(i).getName());
            tvDescription.setText(list.get(i).getDescription());
            btnEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClicked(String.valueOf(list.get(i).getId()), list.get(i).getName(), list.get(i).getDescription(), "edit");
                }
            });
            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClicked(String.valueOf(list.get(i).getId()), list.get(i).getName(), list.get(i).getDescription(), "delete");
                }
            });
        }
    }
    public interface OnAdapterClickListener {
        void onClicked(String id, String name, String description, String key);
    }
}
