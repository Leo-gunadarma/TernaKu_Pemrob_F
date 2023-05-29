package com.kelompok4.ternakku;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
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
    public void onBindViewHolder(@NonNull KelolaKandangAdpater.viewHolder holder, @SuppressLint("RecyclerView")int position) {
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
                int kandangId =dataKandang.get(position).getId();
                int validasiKandang = database.daoHewan().getHewanCountInKandang(kandangId);
                if(validasiKandang == 0){
                    AlertDialog.Builder deleteMsg = new AlertDialog.Builder(context);
                    deleteMsg.setTitle("Apakah anda yakin ingin menghapus data?");
                    deleteMsg.setMessage("Anda akan menghapus data \""+dataKandang.get(position).getNamaKandang()+ "\" \n"+
                            "Tekan tombol ok jika anda yakin");
                    deleteMsg.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            database.daoHewan().deleteKandang(dataKandang.get(position));
                            dataKandang.remove(position);
                            notifyItemRemoved(position);
                            notifyItemRangeRemoved(position, dataKandang.size());
                            Toast.makeText(context, "Data Berhasil Dihapus", Toast.LENGTH_SHORT).show();
                        }
                    });

                    deleteMsg.setNeutralButton("Tidak", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
                    deleteMsg.show();
                }else{
                    AlertDialog.Builder deleteMsg = new AlertDialog.Builder(context);
                    deleteMsg.setIcon(R.drawable.ic_warning);
                    deleteMsg.setTitle("Tidak Dapat Menghapus Data");
                    deleteMsg.setMessage("Anda tidak dapat menghapus data, dikarenakan data digunakan pada kelola hewan");
                    deleteMsg.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
                    deleteMsg.show();
                }
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
