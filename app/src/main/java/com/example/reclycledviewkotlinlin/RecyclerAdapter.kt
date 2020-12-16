package com.example.reclycledviewkotlinlin

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.reclycledviewkotlinlin.modelos.Perros
import kotlinx.android.synthetic.main.rows.view.*

class RecyclerAdapter(
    val context:Context,
    val listaPerros:List<Perros>):RecyclerView.Adapter<BaseViewHolder<*>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        // inflamos vista
        return AlumnosViewHolder(LayoutInflater.from(context).inflate(R.layout.rows,parent,false))

    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        //carga datos en lista
        if (holder is AlumnosViewHolder)
            holder.bind(listaPerros[position],position)
        else
            throw IllegalArgumentException("Error viewHolder erroneo")
    }

    override fun getItemCount(): Int =  listaPerros.size          //número de items

    inner class AlumnosViewHolder(itemView:View):BaseViewHolder<Perros>(itemView)// nos aseguramos de que cuando la clase padre muera, muera esta también
    {
        override fun bind(item: Perros, position: Int) {
            Glide.with(context).load(item.url).into(itemView.profile_image)

            itemView.textViewRaza.text = item.Raza;
            itemView.textViewEdadMax.text = item.EdadMax.toString() + " años";
            itemView.textViewTamano.text = item.Tamano.toString() + " cm";
            itemView.textViewLugar.text = item.Lugar;
            itemView.textViewTipo.text = item.Tipo;


        }
    }

}