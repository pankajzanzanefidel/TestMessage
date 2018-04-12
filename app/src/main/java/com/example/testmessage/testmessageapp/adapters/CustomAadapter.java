package com.example.testmessage.testmessageapp.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.testmessage.testmessageapp.R;
import com.example.testmessage.testmessageapp.database.dataenetities.DbModelContact;

import java.util.List;

public class CustomAadapter extends RecyclerView.Adapter<CustomAadapter.ContactViewHolder> {

    List<DbModelContact> dbModelContacts = null;

    public CustomAadapter(List<DbModelContact> dbModelContacts) {
        this.dbModelContacts = dbModelContacts;

    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new ContactViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_contact, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        holder.txtname.setText(dbModelContacts.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return dbModelContacts.size();
    }

    public static class ContactViewHolder extends RecyclerView.ViewHolder {

        private TextView txtname = null;

        public ContactViewHolder(View itemView) {
            super(itemView);

            txtname = itemView.findViewById(R.id.lbl_name);
        }
    }
}
