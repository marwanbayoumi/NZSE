package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapterKunde extends RecyclerView.Adapter<MyAdapterKunde.ViewHolder> {

    private List<Wohnungsobjekt> wohnungsobjekts;
    private Context context;

    public MyAdapterKunde(List<Wohnungsobjekt> wohnungsobjekts, Context context) {
        this.wohnungsobjekts = wohnungsobjekts;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_list_item,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapterKunde.ViewHolder holder, int position) {
        final Wohnungsobjekt listItem = wohnungsobjekts.get(position);

        holder.textViewHeader.setText(listItem.getAddresse());
        holder.textViewDesc.setText(listItem.getDasAngebot());
        holder.textViewPreis.setText(String.valueOf(listItem.getPreis())+" €");
        /*holder.textViewHeader.setText("Addresse: "+listItem.getAddresse());
        holder.textViewDesc.setText("Angebot "+listItem.getDasAngebot());
        holder.textViewPreis.setText("Preis: "+ String.valueOf(listItem.getPreis())+" €");*/
        holder.listItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, listItem.getAddresse(), Toast.LENGTH_LONG).show();
                Intent intent = new Intent(context, KundeCardView.class);
                intent.putExtra("name",listItem.getAddresse());
                intent.putExtra("preis",Double.toString(listItem.getPreis()));
                intent.putExtra("angebot",listItem.getDasAngebot());
                intent.putExtra("anzahl",Integer.toString(listItem.getZimmer_anzahl()) );
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return wohnungsobjekts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView textViewHeader;
        public TextView textViewDesc;
        public TextView textViewPreis;
        public CardView listItemView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewHeader = (TextView) itemView.findViewById(R.id.textViewHeader);
            textViewDesc = (TextView) itemView.findViewById(R.id.textViewDescription);
            textViewPreis = (TextView) itemView.findViewById(R.id.textViewPreis);
            listItemView = (CardView) itemView.findViewById(R.id.card_view);

        }
    }
}
