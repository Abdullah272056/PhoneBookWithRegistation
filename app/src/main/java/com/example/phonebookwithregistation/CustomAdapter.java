package com.example.phonebookwithregistation;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> implements Filterable {
    Context context;
    private List<Notes> allNotes;
    List<Notes> copyAllNotes;
    private DataBaseHelper databaseHelper;

    public CustomAdapter(Context context, List<Notes>allNotes) {
        this.context = context;
        this.allNotes = allNotes;
      databaseHelper=new DataBaseHelper(context);
     this.context=context;

        copyAllNotes = new ArrayList<>(allNotes);//for searchView//dataList's copy
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(context);
       View view= layoutInflater.inflate(R.layout.sample_layout,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        holder.name.setText(allNotes.get(position).getName());
        holder.location.setText(allNotes.get(position).getLocation());
        holder.phoneNumber.setText(allNotes.get(position).getPhoneNumber());

        holder.callImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber=allNotes.get(position).phoneNumber;
                Toast.makeText(context, phoneNumber ,Toast.LENGTH_SHORT).show();

                String s="tel:"+phoneNumber;
                Intent intent=new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse(s));
                //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);

            }
        });
        holder.messageImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(Intent.ACTION_VIEW);
                String number = allNotes.get(position).getPhoneNumber();
                intent.setData(Uri.parse("sms:"+number));
                String myMessage="Hello! How are You ?";
                intent.putExtra("Sms Body",myMessage);
                //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);

            }
        });

//////////////////

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
//                androidx.appcompat.app.AlertDialog.Builder builder  = new androidx.appcompat.app.AlertDialog.Builder(context);
//                View view1 = LayoutInflater.from(context).inflate(R.layout.recycler_item_operation,null);
//                builder.setView(view1);
//                builder.show();

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Select Action");
                builder.setPositiveButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        customDialog(position);
                    }
                });

                builder.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        int status = databaseHelper.deleteData(allNotes.get(position).getId());
                        if (status == 1){
                            allNotes.remove(allNotes.get(position));
                            notifyDataSetChanged();
                        }else {
                        }
                    }
                });
                builder.show();
                return true;
            }
        });
        ///////////////





    }
    @Override
    public int getItemCount() {
        return allNotes.size();
    }

    @Override
    public Filter getFilter() {

        return filterData;
    }

    Filter filterData =new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<Notes> filterList=new ArrayList<>();//for filter data keeping
            if (charSequence==null||charSequence.length()==0){
                filterList.addAll(copyAllNotes);
            }
            else{
                String value=charSequence.toString().toLowerCase().trim();
                for (Notes notes:copyAllNotes){
                    if (notes.getName().toLowerCase().trim().contains(value)){
                        filterList.add(notes);
                    }

                }
            }
            FilterResults filterResults = new FilterResults();
            filterResults.values = filterList;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            allNotes.clear();
            allNotes.addAll((List)results.values);
            notifyDataSetChanged();
        }
    };


    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name,location,phoneNumber;
        ImageView callImage,messageImage;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name= itemView.findViewById(R.id.nameTextViewId);
            location= itemView.findViewById(R.id.locationTextViewId);
            phoneNumber= itemView.findViewById(R.id.phoneNumberTextViewId);

            callImage= itemView.findViewById(R.id.callImageViewId);
            messageImage= itemView.findViewById(R.id.messageImageViewId);

        }
    }


    private void customDialog(final int position) {

        androidx.appcompat.app.AlertDialog.Builder builder  = new androidx.appcompat.app.AlertDialog.Builder(context);
        View view = LayoutInflater.from(context).inflate(R.layout.custom_dialog,null);
        builder.setView(view);
        final androidx.appcompat.app.AlertDialog alertDialog = builder.create();

        final EditText name       = view.findViewById(R.id.nameEditTextId);
        final EditText location = view.findViewById(R.id.locationEditTextId);
        final EditText number = view.findViewById(R.id.phoneEditTextId);

        name.setText(allNotes.get(position).getName());
        location.setText(allNotes.get(position).getLocation());
        number.setText(allNotes.get(position).getPhoneNumber());
        Button saveButton    = view.findViewById(R.id.saveButtonId);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nameValue       = name.getText().toString();
                String locationValue = location.getText().toString();
                String phoneNumberValue = number.getText().toString();

                if (nameValue.isEmpty()){
                    name.setError("Enter name");
                    return;
                }else if (locationValue.isEmpty()){
                    location.setError("Enter location");
                    return;
                }else if (phoneNumberValue.isEmpty()){
                    number.setError("Enter number");
                    return;
                }


                long status = databaseHelper.updateData(new Notes(allNotes.get(position).getId(),nameValue,locationValue,phoneNumberValue));
                if (status == 1){
                    alertDialog.dismiss();
                    allNotes.clear();
                    allNotes.addAll(databaseHelper.getAllNotes());
                    notifyDataSetChanged();
                    Toast.makeText(context, "Successfully Updated", Toast.LENGTH_SHORT).show();
                }else {
                    alertDialog.dismiss();
                    Toast.makeText(context, "Failed to Update", Toast.LENGTH_SHORT).show();
                }
            }
        });

        alertDialog.show();

    }


}
