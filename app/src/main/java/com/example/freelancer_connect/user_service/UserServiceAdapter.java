package com.example.freelancer_connect.user_service;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.freelancer_connect.R;
import com.example.freelancer_connect.provider.Provider;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


public class UserServiceAdapter extends RecyclerView.Adapter<UserServiceAdapter.UserServiceViewHolder>{
    private List<Provider> mList;

    public List<Provider> getmList() {
        return mList;
    }

    public UserServiceAdapter(List<Provider> list) {
        this.mList = list;
    }

    public static class UserServiceViewHolder extends RecyclerView.ViewHolder {
        public CircleImageView mySerViceImage;
        public TextView myServiceTitle;
        public TextView myServicePrice;
        public TextView myServiceStar;
        public Button btnDelete;
        public Button btnUpdate;


        public UserServiceViewHolder(@NonNull View itemView) {
            super(itemView);
            mySerViceImage = itemView.findViewById(R.id.my_service_image);
            myServiceTitle = itemView.findViewById(R.id.my_service_title);
            myServicePrice = itemView.findViewById(R.id.my_service_price);
            myServiceStar = itemView.findViewById(R.id.my_service_star_text);
            btnDelete = itemView.findViewById(R.id.my_service_button_delete);
            btnUpdate = itemView.findViewById(R.id.my_service_button_update);
        }
    }

    @NonNull
    @Override
    public UserServiceAdapter.UserServiceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_service_item, parent, false);
        return new UserServiceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserServiceAdapter.UserServiceViewHolder holder, int position) {
        Provider provider = new Provider(R.drawable.img_user, "Gia sư tiếng Anh", "500.000đ", "100", "4.5");
        holder.mySerViceImage.setImageResource(provider.getImage());
        holder.myServiceTitle.setText(provider.getTilte());
        holder.myServicePrice.setText(provider.getPrice());
        holder.myServiceStar.setText(provider.getNumStar());
    }

    @Override
    public int getItemCount() {
        if (mList != null) {
            return mList.size();
        }
        return 0;
    }
}
