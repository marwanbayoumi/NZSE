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

import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

private List<Wohnungsobjekt> wohnungsobjekts;
private Context context;

    public MyAdapter(List<Wohnungsobjekt> wohnungsobjekts, Context context) {
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
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    final Wohnungsobjekt listItem = wohnungsobjekts.get(position);
    final JSONObject jsonObject = JsonHandler.JSONs.get(position);
    holder.textViewHeader.setText(listItem.getAddresse());
    holder.textViewDesc.setText(listItem.getDasAngebot());
    holder.textViewPreis.setText(String.valueOf(listItem.getPreis())+" €");
/*        holder.textViewHeader.setText("Addresse: "+listItem.getAddresse());
        holder.textViewDesc.setText("Angebot: "+listItem.getDasAngebot());
        holder.textViewPreis.setText("Preis: "+String.valueOf(listItem.getPreis())+" €");*/
//    holder.textViewHits.setText(listItem.getHits());
    holder.listItemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v)  {
            listItem.setHits(listItem.getHits()+1);
//            try {
//                JsonHandler.updateJSON(jsonObject,listItem.getHits());
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
            Toast.makeText(context, listItem.getAddresse(), Toast.LENGTH_LONG).show();
            Intent intent = new Intent(context, MarklerCardView.class);
            intent.putExtra("name",listItem.getAddresse());
            intent.putExtra("preis",Double.toString(listItem.getPreis()));
            intent.putExtra("angebot",listItem.getDasAngebot());
            intent.putExtra("anzahl",Integer.toString(listItem.getZimmer_anzahl()) );
            intent.putExtra("hits",Integer.toString(listItem.getHits()));
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
        //public TextView textViewHits;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewHeader = (TextView) itemView.findViewById(R.id.textViewHeader);
            textViewDesc = (TextView) itemView.findViewById(R.id.textViewDescription);
            textViewPreis = (TextView) itemView.findViewById(R.id.textViewPreis);
            listItemView = (CardView) itemView.findViewById(R.id.card_view);
            //textViewHits =  (TextView) itemView.findViewById(R.id.textViewHits);
        }
    }
}
