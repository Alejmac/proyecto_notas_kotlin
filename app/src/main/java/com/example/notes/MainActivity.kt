    package com.example.notes

import android.app.Dialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.View.OnClickListener
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notes.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(),OnClickListener {

    private lateinit var binding:ActivityMainBinding
    // variable global del adaptador
    private lateinit var  noteadaptador :noteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)

        val data = mutableListOf(
            Note (1,"estudiar "),
            Note(2,"ir al mercado"))
        noteadaptador = noteAdapter(data,this)
        binding.recyclerView.apply{
            layoutManager= LinearLayoutManager(this@MainActivity)
            adapter=noteadaptador
        }

        //en cso de que no ste vacio & añadir en tiempo real
        binding.btnAdd.setOnClickListener {
            if(binding.etDescription.text.toString().isNotBlank()){
                val note = Note((noteadaptador.itemCount + 1).toLong(),binding.etDescription.text.toString().trim() )
               // agregamos nota en tiempo de ejecucion
                addNoteAuto(note)
                //limpiamos la area de etdesc
                binding.etDescription.text?.clear()
            }
        }
    }

    private fun addNoteAuto(note: Note) {
     noteadaptador.add(note)
    }

    override fun onClick(v: View?) {

    }
    private fun deleteNoteAuto(note: Note) {
        var builder=AlertDialog.Builder(this)
            .setTitle(getString(R.string.Dialog_title) ).setPositiveButton(getString(R.string.Dialog_ok),{dialoInterface, i->
                noteadaptador.remove(note)
            })
            .setNegativeButton("cancelar",null)

        builder.create().show()
    }
    fun onLongClick(note: Note) {

        deleteNoteAuto(note)
    }

    // menu de oocpiones
    override fun onCreateOptionsMenu(menu: Menu, ): Boolean {
        val inflater : MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_opciones,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.getItemId()){
           R.id.itemNuevo -> Toast.makeText(this,"nuevo",Toast.LENGTH_LONG).show();
            R.id.itemOpciones -> Toast.makeText(this,"opciones",Toast.LENGTH_LONG).show();
            R.id.itemAyuda -> Toast.makeText(this,"Necesitas ayuda?",Toast.LENGTH_LONG).show();
            R.id.itemSalir -> Toast.makeText(this,"Deseas salir?",Toast.LENGTH_LONG).show();
        }
        return super.onOptionsItemSelected(item);
    }

}