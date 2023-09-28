package com.example.lab3.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab3.R;

import java.util.Arrays;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {
    private String[] contacts;
    public ContactAdapter(String[] contacts) {
        this.contacts = contacts;
    }
    @NonNull
    @Override
    public ContactAdapter.ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contact_card, parent, false);
        return new ContactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactAdapter.ContactViewHolder holder, int position) {
        String[] contact = contacts[position].split(",");
        String[] data = contact[0].split(" ");


        holder.personName.setText(data[1]);
        holder.email.setText(data[3]);
        holder.dob.setText(data[2]);

        String drawableName = data[4].trim();

        int imageId = holder.itemView.getContext().getResources().getIdentifier(drawableName, "drawable", holder.itemView.getContext().getPackageName());

        holder.image.setImageResource(imageId);
    }

    @Override
    public int getItemCount() {
        return contacts.length;
    }

    public class ContactViewHolder extends RecyclerView.ViewHolder {
        TextView personName, email, dob;
        ImageView image;
        public ContactViewHolder(@NonNull View itemView) {
            super(itemView);
            personName = itemView.findViewById(R.id.personName);
            email = itemView.findViewById(R.id.personEmail);
            dob = itemView.findViewById(R.id.personDob);
            image = itemView.findViewById(R.id.imageView);
        }
    }
}
