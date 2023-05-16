package com.kelompok4.ternakku;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kelompok4.ternakku.database.DatabaseTernaku;
import com.kelompok4.ternakku.database.TabelKandang;

import java.util.ArrayList;
import java.util.List;

public class KelolaKandangAdpater extends RecyclerView.Adapter<KelolaKandangAdpater.viewHolder> {
//    Membuat object
    private DatabaseTernaku database;
    private Context context;
    private List<TabelKandang> dataKandang = new ArrayList<>();

//    Membuat constructor untuk adapter

    public KelolaKandangAdpater(Context context, List<TabelKandang> dataKandang) {
        this.context = context;
        this.dataKandang = dataKandang;
    }

    @NonNull
    @Override
    public KelolaKandangAdpater.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_kandang_view,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KelolaKandangAdpater.viewHolder holder, int position) {
//        Menambahkan holder untuk merubah tampilan sesuai data
        holder.namaKandang.setText(dataKandang.get(position).getNamaKandang());
        holder.lokasiKandang.setText("Lokasi: "+ dataKandang.get(position).getLokasiKandang());
        database = DatabaseTernaku.getDbInstance(context);

//        Button Edit
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                passData(position,TambahKandangActivity.class);
            }
        });
//        Button Delete
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

//        Button Detail
        holder.detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                passData(position,DetailKandangActivity.class);
            }
        });

    }

    @Override
    public int getItemCount() {
        return dataKandang.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{
        private TextView namaKandang, lokasiKandang;
        private ImageView edit,delete,detail;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            namaKandang = itemView.findViewById(R.id.textViewNamaKandangAdapter);
            lokasiKandang = itemView.findViewById(R.id.textViewLokasiKandangAdapter);
            edit = itemView.findViewById(R.id.imageViewEditKandang);
            delete = itemView.findViewById(R.id.imageViewDeleteKandang);
            detail = itemView.findViewById(R.id.imageViewDetailKandang);
        }
    }
    public void passData (int position, Class loc){
        Intent detail = new Intent(context,loc);
        detail.putExtra("id_kandang",""+dataKandang.get(position).getId());
        detail.putExtra("nama_kandang", dataKandang.get(position).getNamaKandang());
        detail.putExtra("lokasi_kandang", dataKandang.get(position).getLokasiKandang());
        detail.putExtra("luas_kandang", ""+ dataKandang.get(position).getLuasKandang());
        context.startActivity(detail);
    }
}
