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
import com.kelompok4.ternakku.database.TabelObat;

import java.util.ArrayList;
import java.util.List;

public class KelolaObatAdpter extends RecyclerView.Adapter<KelolaObatAdpter.viewHolder> {
    //    Membuat object
    private DatabaseTernaku database;
    private Context context;
    private List<TabelObat> dataObat = new ArrayList<>();

    public KelolaObatAdpter(Context context, List<TabelObat> dataObat) {
        this.context = context;
        this.dataObat = dataObat;
    }

    @NonNull
    @Override
    public KelolaObatAdpter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_obat_view,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KelolaObatAdpter.viewHolder holder, int position) {
        //        Menambahkan holder untuk merubah tampilan sesuai data
        holder.namaObat.setText(dataObat.get(position).getNamaObat());
        holder.jumlahObat.setText("Jumlah Obat: "+ dataObat.get(position).getJumlahObat());
        database = DatabaseTernaku.getDbInstance(context);

//        Button Edit
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                passData(position,TambahObatActivity.class);
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
                passData(position,DetailObatActivity.class);
            }
        });

    }

    @Override
    public int getItemCount() {
        return dataObat.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        private TextView namaObat, jumlahObat;
        private ImageView edit,delete,detail;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            namaObat = itemView.findViewById(R.id.textViewNamaObatAdapter);
            jumlahObat = itemView.findViewById(R.id.textViewJumlahObatAdapter);
            edit = itemView.findViewById(R.id.imageViewEditObat);
            delete = itemView.findViewById(R.id.imageViewDeleteObat);
            detail = itemView.findViewById(R.id.imageViewDetailObat);
        }
    }
    public void passData (int position, Class loc){
        Intent detail = new Intent(context,loc);
        detail.putExtra("id_obat", ""+dataObat.get(position).getId());
        detail.putExtra("nama_obat", dataObat.get(position).getNamaObat());
        detail.putExtra("jumlah_obat", ""+ dataObat.get(position).getJumlahObat());
        detail.putExtra("deskripsi_obat", dataObat.get(position).getDeskripsiObat());
        detail.putExtra("aturan_obat", dataObat.get(position).getPeraturan());
        context.startActivity(detail);
    }
}
