package com.example.notes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.databinding.ItemNoteBinding

 // construir un adaptador en kotlin
class noteAdapter(var notelist: MutableList<Note>, private val listener: MainActivity)
        :RecyclerView.Adapter<noteAdapter.ViewHolder> (){

     // clase que nos permite inflar la vista
    inner class ViewHolder(view: View ): RecyclerView.ViewHolder(view){

     // con esto se infla la clase adapter
        var binding = ItemNoteBinding.bind(view)
            fun setListener(note: Note){
            binding.root.setOnLongClickListener{
                listener.onLongClick(note)
                    true
            }

            }
    }

     override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
         //variable de solo lectura
          val view= LayoutInflater.from(parent.context).inflate(
             R.layout.item_note,parent,false
              )
         return ViewHolder(view)

     }
     // nos idnidca cuantoselementos hay en el listado
     override fun getItemCount(): Int {
        return notelist.size
     }

     override fun onBindViewHolder(holder: ViewHolder, position: Int) {

         //asignamos valor a la propiedad descriccion
         val note = notelist.get(position)
         // accedemos a la vista
         holder.binding.tvDescripcion.text= note.descripcion
         // menadmos el holder a la funcion de setlistener para eliminar
         holder.setListener(note)
     }
 // funcion para añadir un a nota
     fun add(note: Note) {
            notelist.add(note)
     // notificamos a la lista que se est aañadiendo un cmapo
     notifyDataSetChanged()
     }
     //funcion para eliminar una nota
     fun remove(note: Note) {
         notelist.remove(note)
         // notificamos a la lista que se est aañadiendo un cmapo
         notifyDataSetChanged()
     }
 }