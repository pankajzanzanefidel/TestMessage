package com.example.testmessage.testmessageapp.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.testmessage.testmessageapp.R;
import com.example.testmessage.testmessageapp.activities.CLickListner;
import com.example.testmessage.testmessageapp.database.dataenetities.DbModelContact;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ContactViewholder> {

    private List<DbModelContact> dbModelContacts = null;
    public CLickListner clicklistener = null;


    public void setClickListener(CLickListner clicklistener) {
        this.clicklistener = clicklistener;
    }

    public CustomAdapter(List<DbModelContact> dbModelContacts) {
        this.dbModelContacts = dbModelContacts;
    }

    @NonNull
    @Override
    public ContactViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ContactViewholder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_contact, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewholder holder, int position) {
        holder.textView.setText(dbModelContacts.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return dbModelContacts.size();
    }

    public  class ContactViewholder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView textView = null;

        public ContactViewholder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            textView = (TextView) itemView.findViewById(R.id.textName);
        }

        @Override
        public void onClick(View v) {
            if (clicklistener != null) {
                clicklistener.itemClicked(v, getAdapterPosition());
            }
        }
    }

}
