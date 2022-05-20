package com.example.android.trackmysleepquality.sleeptracker

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.android.trackmysleepquality.R
import com.example.android.trackmysleepquality.TextItemViewHolder
import com.example.android.trackmysleepquality.database.SleepNight

class SleepNightAdapter : RecyclerView.Adapter<TextItemViewHolder>() {
    var data =  listOf<SleepNight>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount() = data.size
    override fun onBindViewHolder(holder: TextItemViewHolder, position: Int) {//The onBindViewHolder()function is called by RecyclerView to display the data for one list item at the specified position. So the onBindViewHolder() method takes two arguments: a view holder, and a position of the data to bind. For this app, the holder is the TextItemViewHolder, and the position is the position in the list.
        val item = data[position]
        holder.textView.text = item.sleepQuality.toString()//The ViewHolder you just created has a property called textView. Inside onBindViewHolder(), set the text of the textView to the sleep-quality number. This code displays only a list of numbers, but this simple example lets you see how the adapter gets the data into the view holder and onto the screen.
        if (item.sleepQuality <= 1) {
            holder.textView.setTextColor(Color.RED) // red
        }else {
            // reset
            holder.textView.setTextColor(Color.BLACK) // black
        }


    }
    /*
    En SleepNightAdapter, reemplaza e implementa onCreateViewHolder(), que se llama cuando RecyclerView necesita un contenedor de vista.
    Esta función toma dos parámetros y devuelve un ViewHolder.  El parámetro principal, que es el grupo de vista que contiene el titular de la vista, siempre es RecyclerView.
     El parámetro viewType se usa cuando hay varias vistas en el mismo RecyclerView.  Por ejemplo, si coloca una lista de vistas de texto,
     una imagen y un video en el mismo RecyclerView, la función onCreateViewHolder() necesitaría saber qué tipo de vista usar.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
/*
En onCreateViewHolder(), crea la vista pidiéndole al layoutinflater que la infle.
 Pase el diseño XML para la vista y el grupo de vista principal para la vista.  El tercer argumento, booleano, es addedToRoot.
 Este argumento debe ser falso, porque RecyclerView agrega este elemento a la jerarquía de vistas cuando sea el momento.
 */
        val view = layoutInflater
            .inflate(R.layout.text_item_view, parent, false) as TextView
        return TextItemViewHolder(view)

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){//creacion del viewholder que tendra mas diseño
        /*
        Necesita una referencia a las vistas que actualizará este ViewHolder.  Cada vez que vincula este ViewHolder, debe acceder a la imagen y a ambas vistas de texto.
          (Usted convierte este código para usar el enlace de datos más adelante).
         */
        val sleepLength: TextView = itemView.findViewById(R.id.sleep_length)
        val quality: TextView = itemView.findViewById(R.id.quality_string)
        val qualityImage: ImageView = itemView.findViewById(R.id.quality_image)
    }


}