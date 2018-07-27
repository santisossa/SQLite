package com.santiago.sqlite.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.santiago.sqlite.R;
import com.santiago.sqlite.model.Mascota;

import java.util.ArrayList;

public class MascotaAdapter extends BaseAdapter{
    private Context context;
    private int layout;
    private ArrayList<Mascota> mascotas;

    public MascotaAdapter(Context context, int layout, ArrayList<Mascota> mascotas) {
        this.context = context;
        this.layout = layout;
        this.mascotas = mascotas;
    }

    @Override
    public int getCount() {
        return mascotas.size();
    }

    @Override
    public Object getItem(int position) {
        return mascotas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        if (convertView==null){
            convertView= LayoutInflater.from(context).inflate(layout,null);
            holder=new ViewHolder();
            holder.id=convertView.findViewById(R.id.id);
            holder.nombre=convertView.findViewById(R.id.nomMascota);
            holder.raza=convertView.findViewById(R.id.raza);
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }

            Mascota mascota= (Mascota) getItem(position);
            holder.id.setText(mascota.getId().toString());
            holder.nombre.setText(mascota.getNombre());
            holder.raza.setText(mascota.getRaza());



        return convertView;
    }

    static class ViewHolder{
        private TextView id,nombre,raza;


    }
}
