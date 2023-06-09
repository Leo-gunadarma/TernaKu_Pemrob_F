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
import com.kelompok4.ternakku.database.TabelHewan;

import java.util.ArrayList;
import java.util.List;

public class KelolaHewanAdapter extends RecyclerView.Adapter<KelolaHewanAdapter.viewHolder> {
    private DatabaseTernaku database;
    private Context context;
    private List<TabelHewan> dataHewan= new ArrayList<>();

    public KelolaHewanAdapter(Context context, List<TabelHewan> dataHewan) {
        this.context = context;
        this.dataHewan = dataHewan;
    }

    @NonNull
    @Override
    public KelolaHewanAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_hewan_view,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KelolaHewanAdapter.viewHolder holder, @SuppressLint("RecyclerView")int position) {
        holder.namaHewan.setText(dataHewan.get(position).getNamaHewan());
        holder.jumlahHewan.setText("Jumlah Hewan: "+dataHewan.get(position).getJumlahHewan());
        database = DatabaseTernaku.getDbInstance(context);

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passData(position,TambahHewanActivity.class);
            }
        });

        holder.detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passData(position,DetailHewanActivity.class);
            }
        });

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder deleteMsg = new AlertDialog.Builder(context);
                deleteMsg.setTitle("Apakah anda yakin ingin menghapus data?");
                deleteMsg.setMessage("Anda akan menghapus data \""+dataHewan.get(position).getNamaHewan()+ "\" \n"+
                        "Tekan tombol ok jika anda yakin");
                deleteMsg.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        database.daoHewan().deleteHewan(dataHewan.get(position));
                        dataHewan.remove(position);
                        notifyItemRemoved(position);
                        notifyItemRangeRemoved(position, dataHewan.size());
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
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataHewan.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{
        private TextView namaHewan,jumlahHewan;
        private ImageView edit,delete,detail;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            namaHewan = itemView.findViewById(R.id.textViewNamaHewan);
            jumlahHewan = itemView.findViewById(R.id.textViewJumlahHewan);
            edit = itemView.findViewById(R.id.imageViewEditHewan);
            delete = itemView.findViewById(R.id.imageViewDeleteHewan);
            detail = itemView.findViewById(R.id.imageViewDetailHewan);
        }
    }
    public void passData (int position, Class loc){
        Intent detail = new Intent(context,loc);
        detail.putExtra("id_hewan",""+dataHewan.get(position).getId());
        detail.putExtra("nama_hewan", dataHewan.get(position).getNamaHewan());
        detail.putExtra("ras_hewan", dataHewan.get(position).getRasHewan());
        detail.putExtra("jenis_hewan", ""+ dataHewan.get(position).getJenisHewan());
        detail.putExtra("jumlah_hewan", ""+ dataHewan.get(position).getJumlahHewan());
        detail.putExtra("jadwal_makan", ""+ dataHewan.get(position).getJadwalMakan());
        detail.putExtra("kandang_id", ""+ dataHewan.get(position).getKandangID());
        detail.putExtra("obat_id", ""+ dataHewan.get(position).getObatID());
        context.startActivity(detail);
    }
}
