package com.example.datedemo;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.datedemo.databinding.LayoutDogitemBinding;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class DogAdapter extends RecyclerView.Adapter<DogAdapter.DogViewHolder> {
    List<Dog> AdapterDogList;
    OnItemClickListener onItemClickListener;

    public DogAdapter(List<Dog> adapterDogList, OnItemClickListener onItemClickListener) {
        AdapterDogList = adapterDogList;
        this.onItemClickListener = onItemClickListener;
    }

    public DogAdapter(List<Dog> adapterDogList) {
        AdapterDogList = adapterDogList;
    }

    @NonNull
    @Override
    public DogViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //return null;
        //Inflate the binding
        //using binding create holder object
        //return holder object

        LayoutDogitemBinding binding = LayoutDogitemBinding.inflate(
                                LayoutInflater.from(parent.getContext()),
                                parent,false);
        DogViewHolder holder = new DogViewHolder(binding.getRoot(),binding);
        return holder;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull DogViewHolder holder, int position) {
        holder.binding.txtViewId
                .setText(Integer.toString(AdapterDogList.get(position).getId()));
        holder.binding.txtViewName.setText(AdapterDogList.get(position).getDogName());
        holder.binding.txtViewBreed.setText(AdapterDogList.get(position).getDogBreed());
        holder.binding.imgViewDogPic
                .setImageResource(AdapterDogList.get(position).getDogPicDrawable());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MM yyyy");
        String dobStr = formatter.format(AdapterDogList.get(position).getDogDob());
        holder.binding.txtViewDOB.setText(dobStr);
    }

    @Override
    public int getItemCount() {
        return AdapterDogList.size();
    }

    public class DogViewHolder extends RecyclerView.ViewHolder {
        LayoutDogitemBinding binding;

        public DogViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public DogViewHolder(@NonNull View itemView, LayoutDogitemBinding binding) {
            super(itemView);
            this.binding = binding;

            //click listener needs to be set up
            this.binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onItemClick(getAdapterPosition());
                }
            });
        }
    }

    public interface OnItemClickListener{
        public void onItemClick(int i);
    }
}
