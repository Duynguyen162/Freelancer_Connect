package com.example.freelancer_connect.provider;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.freelancer_connect.R;

import java.util.ArrayList;
import java.util.List;

public class ProviderAdapter extends RecyclerView.Adapter<ProviderAdapter.ProviderViewHolder> implements Filterable{
    private Context mContext;
    private List<Provider> mListProvider;
    private List<Provider> mListProviderOld;

    public ProviderAdapter(Context mContext, List<Provider> mListProvider) {
        this.mContext = mContext;
        this.mListProvider = mListProvider;
        this.mListProviderOld = mListProvider;
    }


    @NonNull
    @Override
    public ProviderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_provider, parent, false);
        return new ProviderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProviderViewHolder holder, int position) {
        final Provider provider = mListProvider.get(position);
        if (provider == null) {
            return;
        }
        holder.providerImage.setImageResource(provider.getImage());
        holder.providerTitle.setText(provider.getTilte());
        holder.providerPrice.setText(provider.getPrice());
        holder.providerNumJobDone.setText(provider.getNumJobDone());
        holder.providerNumStar.setText(provider.getNumStar());
        holder.providerCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickGoToDeTail(provider);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mListProvider != null) {
            return mListProvider.size();
        }
        return 0;
    }

    private void onClickGoToDeTail(Provider provider) {
        Intent intent = new Intent(mContext, ProviderDetailInfo.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("object_provider", provider);
        intent.putExtras(bundle);
        mContext.startActivity(intent);
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String strSearch = constraint.toString();
                if (strSearch.isEmpty()) {
                    mListProvider = mListProviderOld;
                } else {
                    List<Provider> list= new ArrayList<>();
                    for (Provider provider : mListProviderOld) {
                        if (provider.getTilte().toLowerCase().contains(strSearch.toLowerCase())) {
                            list.add(provider);
                        }
                    }
                    mListProvider = list;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = mListProvider;

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                mListProvider = (List<Provider>) results.values;
                notifyDataSetChanged();
            }
        };
    }


    public class ProviderViewHolder extends RecyclerView.ViewHolder {
        private CardView providerCardView;
        private ImageView providerImage;
        private TextView providerTitle;
        private TextView providerPrice;
        private TextView providerNumJobDone;
        private TextView providerNumStar;
        public ProviderViewHolder(@NonNull View itemView) {
            super(itemView);
            providerCardView = itemView.findViewById(R.id.item_provider_cardview);
            providerImage = itemView.findViewById(R.id.user_image_view);
            providerTitle = itemView.findViewById(R.id.text_view_provider_title);
            providerPrice = itemView.findViewById(R.id.text_view_provider_price);
            providerNumJobDone = itemView.findViewById(R.id.text_view_provider_num_job_done);
            providerNumStar = itemView.findViewById(R.id.text_view_provider_num_star);
        }
    }
}
