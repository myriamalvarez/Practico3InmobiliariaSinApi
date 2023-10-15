package com.example.practico3inmobiliariasinapi.ui.pagos;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.practico3inmobiliariasinapi.modelo.Inmueble;
import com.example.practico3inmobiliariasinapi.modelo.Pago;
import com.example.practico3inmobiliariasinapi.R;

import java.util.List;

public class InmuebleConPagosAdapter extends RecyclerView.Adapter<InmuebleConPagosAdapter.ViewHolder>{
    private Context context;
    private List<Pago> lista;
    private LayoutInflater inflater;



    public InmuebleConPagosAdapter(List<Pago> lista, Context context, LayoutInflater inflater)
    {
        this.context = context;
        this.lista = lista;
        this.inflater = inflater;
    }

    @NonNull

    public InmuebleConPagosAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = inflater.inflate(R.layout.item_pago, parent, false);
        return new ViewHolder(view);
    }


    public void onBindViewHolder(@NonNull InmuebleConPagosAdapter.ViewHolder holder, int position)
    {
        holder.codigoP.setText(String.valueOf(lista.get(position).getIdPago()));
        holder.numero.setText(String.valueOf(lista.get(position).getNumero()));
        holder.codigoC.setText(String.valueOf(lista.get(position).getContrato().getIdContrato()));
        holder.importe.setText(String.valueOf(lista.get(position).getImporte()));
        holder.fecha.setText(String.valueOf(lista.get(position).getFechaDePago()));
    }

    public int getItemCount()
    {
        return lista.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView codigoP, numero, codigoC, importe, fecha;

        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            codigoP = itemView.findViewById(R.id.tvCodigo);
            numero = itemView.findViewById(R.id.tvNumero);
            codigoC = itemView.findViewById(R.id.tvCodigoContrato);
            importe = itemView.findViewById(R.id.tvImporte);
            fecha = itemView.findViewById(R.id.tvFecha);

        }
    }
}
