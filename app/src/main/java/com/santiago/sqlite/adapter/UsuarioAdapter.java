package com.santiago.sqlite.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.santiago.sqlite.R;
import com.santiago.sqlite.model.Usuario;

import java.util.ArrayList;

public class UsuarioAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private ArrayList<Usuario> list;

    public UsuarioAdapter(Context context, int layout, ArrayList<Usuario> list) {
        this.context = context;
        this.layout = layout;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView==null){
            convertView= LayoutInflater.from(context).inflate(layout,parent,false);
            holder = new ViewHolder();
            holder.id=convertView.findViewById(R.id.documento);
            holder.nombre=convertView.findViewById(R.id.nombre);
            holder.telefono=convertView.findViewById(R.id.telefono);
            convertView.setTag(holder);
        }else{
            holder= (ViewHolder) convertView.getTag();
        }
        Usuario usuario= (Usuario) getItem(position);
        holder.id.setText(usuario.getId()+"");
        holder.nombre.setText(usuario.getNombre());
        holder.telefono.setText(usuario.getTelefono());
        return convertView;
    }

    static class ViewHolder{
      private TextView id,nombre,telefono;

    }
}
