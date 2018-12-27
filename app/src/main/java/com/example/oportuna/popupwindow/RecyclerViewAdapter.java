package com.example.oportuna.popupwindow;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> implements Filterable

{

    Context mContext;
    List<Contact> mData;
    List<Contact> mDataFull;


    static final String PREFERENCIAS_COMPARTIDAD= "preferenciaCompartida";

    static final String SH_NProyecto ="shNproyecto";

    static final String SH_TProyecto="shTproyecto";
    static final String SH_PProyecto="shPproyecto";

    static final String SH_FProyecto ="shFproyecto";



    public RecyclerViewAdapter(Context mContext, List<Contact> mData) {
        this.mContext = mContext;
        this.mData = mData;
        mDataFull=new ArrayList<>(mData); //--
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View v ;
       v = LayoutInflater.from(mContext).inflate(R.layout.row,parent,false);
       MyViewHolder vHolder = new MyViewHolder(v);
       return  vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
    holder.tv_name.setText(mData.get(position).getName());
    holder.tv_phone.setText(mData.get(position).getPhone());
    holder.img.setImageResource(mData.get(position).getPhoto());
        holder.tv_numPuntos.setText(mData.get(position).getNumPuntos());
        holder.tv_descrip.setText(mData.get(position).getDescrip());



//        holder.tv_cardview.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(mContext, "Hola Cojudo" +mData.get(position).getName(), Toast.LENGTH_SHORT).show();
//



//                Intent intent = new Intent(mContext, InformeActivity.class);
//
//
//                SharedPreferences configuracion = mContext.getSharedPreferences(PREFERENCIAS_COMPARTIDAD,0);
//                //modificar el archivo utiliso un editor
//                SharedPreferences.Editor editor = configuracion.edit();
//
//
//
//
//                //ingreso la informacion
//                editor.putString(SH_NProyecto,mData.get(position).getName());
//                editor.putString(SH_TProyecto,mData.get(position).getPhone());
//
//                editor.putString(SH_PProyecto,mData.get(position).getNumPuntos());
//
//
//
//                editor.commit();
//
//                mContext.startActivity(intent);
//            }
//        });
//        String url = "https://www.azulejospena.es/wp-content/uploads/2018/08/Maxi_Boreal_cotton-.jpg";
//        Picasso.with(mContext).load(url).into(holder.imgFondo);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public Filter getFilter() {
        return exampleFilter;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_name;
        private TextView tv_phone;
        private ImageView img;
        private TextView tv_numPuntos;
        private TextView tv_descrip;
        private CardView tv_cardview;

        public MyViewHolder(View itemView) {
            super(itemView);

            tv_name= itemView.findViewById(R.id.name_contact);
            tv_phone= itemView.findViewById(R.id.phone_contac);
            tv_numPuntos= itemView.findViewById(R.id.txtNumPuntos);
            tv_descrip= itemView.findViewById(R.id.txtdescrip);
            img= itemView.findViewById(R.id.img_contact);
            tv_cardview = itemView.findViewById(R.id.carview);



        }
    }

    private Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Contact> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(mDataFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (Contact item : mDataFull) {
                    if (item.getName().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            mData.clear();
            mData.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };


}
