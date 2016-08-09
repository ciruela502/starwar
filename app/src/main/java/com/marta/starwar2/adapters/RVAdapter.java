package com.marta.starwar2.adapters;

/**
 * Created by marta on 08.07.2016.
 */

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.marta.starwar2.R;
import com.marta.starwar2.callbacks.MyListener;
import com.swapi.models.People;

import java.util.List;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.ViewHolder> {

    private List<People> peoples;
    private MyListener myListener;

    public RVAdapter(List<People> peoples) {
        this.peoples = peoples;
    }

    public void setMyListener(MyListener myListener) {
        this.myListener = myListener;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.listview_item, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.personName.setText(peoples.get(i).name);
        viewHolder.personAge.setText(peoples.get(i).birthYear);
        viewHolder.bind(peoples.get(i));
    }

    @Override
    public int getItemCount() {
        return peoples.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        CardView cv;
        TextView personName;
        TextView personAge;
        People model;

        public ViewHolder(final View itemView) {
            super(itemView);

            cv = (CardView) itemView.findViewById(R.id.cv);
            personName = (TextView) itemView.findViewById(R.id.person_name);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    myListener.onItemSelect(model, personName);
                }
            });

            personAge = (TextView) itemView.findViewById(R.id.person_age);
        }

        public void bind(People model) {

            this.model = model;
            this.personName.setText(model.name);
            this.personName.setTransitionName(model.name);
            this.personAge.setText(model.birthYear);

        }
    }
}

