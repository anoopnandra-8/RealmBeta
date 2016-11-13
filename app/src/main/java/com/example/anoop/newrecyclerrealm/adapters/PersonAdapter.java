package com.example.anoop.newrecyclerrealm.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.anoop.newrecyclerrealm.R;
import com.example.anoop.newrecyclerrealm.UpdateDeleteDb;
import com.example.anoop.newrecyclerrealm.model.Person;
import com.example.anoop.newrecyclerrealm.realm.RealmController;

import io.realm.Realm;

/**
 * Created by anoop on 08/11/2016.
 */

public class PersonAdapter extends RealmRecyclerViewAdapter<Person> {

    static Context context;
    private Realm realm;
    private LayoutInflater inflater;

    public PersonAdapter(Context context){
        this.context=context;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_content, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
       // realm = RealmController.getInstance().getRealm();
        final Person person = getItem(position);
        final RecyclerViewHolder viewHolder = (RecyclerViewHolder) holder;

        viewHolder.id.setText(String.valueOf(person.getId()));              //check this, convert to String?
        viewHolder.name.setText(person.getName());
        viewHolder.age.setText(String.valueOf(person.getAge()));            //check this, convert to String?
        viewHolder.profession.setText(person.getProfession());

        viewHolder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UpdateDeleteDb.class);
                intent.putExtra("id", viewHolder.id.getText());
                intent.putExtra("name", viewHolder.name.getText());
                intent.putExtra("age", viewHolder.age.getText().toString());
                intent.putExtra("profession", viewHolder.profession.getText());
                intent.putExtra("position", position);
                context.startActivity(intent);

                Log.i("DBID_CHECK ", viewHolder.age.getText().toString());
            }
        });
    }

    @Override
    public int getItemCount() {
        if(getRealmAdapter() != null) {
            return getRealmAdapter().getCount();

        }
        return 0;
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder{

        public TextView id;
        public TextView name;
        public TextView age;
        public TextView profession;
        public RelativeLayout layout;

        public RecyclerViewHolder(View itemView) {
            super(itemView);

            id = (TextView) itemView.findViewById(R.id.list_id);
            name = (TextView) itemView.findViewById(R.id.list_name);
            age = (TextView) itemView.findViewById(R.id.list_age);
            profession = (TextView) itemView.findViewById(R.id.list_profession);
            layout = (RelativeLayout) itemView.findViewById(R.id.list_layout);


            //This also works :D
            /*itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   Intent intent = new Intent(context, UpdateDeleteDb.class);
                    intent.putExtra("id", id.getText());
                    intent.putExtra("name", name.getText());
                    intent.putExtra("age", age.getText());
                    intent.putExtra("profession", profession.getText());
                    context.startActivity(intent);
                }
            });*/
        }
    }
}
