package com.example.android.trackmysleepquality.sleeptracker

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.trackmysleepquality.R
import com.example.android.trackmysleepquality.convertDurationToFormatted
import com.example.android.trackmysleepquality.convertNumericQualityToString
import com.example.android.trackmysleepquality.database.SleepNight
import com.example.android.trackmysleepquality.databinding.ListItemSleepNightBinding

class SleepNightAdapter : ListAdapter<SleepNight, SleepNightAdapter.ViewHolder>(SleepNightDiffCallback()) {// ListAdapter realiza un seguimiento de la lista por usted y notifica al adaptador cuando se actualiza la lista.


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {//The onBindViewHolder()function is called by RecyclerView to display the data for one list item at the specified position. So the onBindViewHolder() method takes two arguments: a view holder, and a position of the data to bind. For this app, the holder is the TextItemViewHolder, and the position is the position in the list.
        val item = getItem(position)//metodo de ListAdapter
        holder.bind(item)
    }

    private fun build(
        holder: ViewHolder,
        item: SleepNight
    ) {
        val res =
            holder.itemView.context.resources//Defina un valor que contenga una referencia a los recursos para esta vista.
        binding.sleepLength.text = convertDurationToFormatted(
            item.startTimeMilli,
            item.endTimeMilli,
            res
        )//configurando la duracion
        binding.qualityString.text =
            convertNumericQualityToString(item.sleepQuality, res)//configurando la calidad
        binding.qualityImage.setImageResource(
            when (item.sleepQuality) {//configurando el icono que corresponde a la calidad
                0 -> R.drawable.ic_sleep_0
                1 -> R.drawable.ic_sleep_1
                2 -> R.drawable.ic_sleep_2
                3 -> R.drawable.ic_sleep_3
                4 -> R.drawable.ic_sleep_4
                5 -> R.drawable.ic_sleep_5
                else -> R.drawable.ic_sleep_active
            }
        )
    }

    /*
    En SleepNightAdapter, reemplaza e implementa onCreateViewHolder(), que se llama cuando RecyclerView necesita un contenedor de vista.
    Esta función toma dos parámetros y devuelve un ViewHolder.  El parámetro principal, que es el grupo de vista que contiene el titular de la vista, siempre es RecyclerView.
     El parámetro viewType se usa cuando hay varias vistas en el mismo RecyclerView.  Por ejemplo, si coloca una lista de vistas de texto,
     una imagen y un video en el mismo RecyclerView, la función onCreateViewHolder() necesitaría saber qué tipo de vista usar.
     */
    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }


    class ViewHolder private constructor(binding: ListItemSleepNightBinding) : RecyclerView.ViewHolder(binding.root){ //creacion del viewholder que tendra mas diseño
        fun bind(item: SleepNight) {
            val res = itemView.context.resources
            binding.sleepLength.text = convertDurationToFormatted(
                item.startTimeMilli, item.endTimeMilli, res
            )
            binding.qualityString.text = convertNumericQualityToString(
                item.sleepQuality, res
            )
            binding.qualityImage.setImageResource(
                when (item.sleepQuality) {
                    0 -> R.drawable.ic_sleep_0
                    1 -> R.drawable.ic_sleep_1
                    2 -> R.drawable.ic_sleep_2
                    3 -> R.drawable.ic_sleep_3
                    4 -> R.drawable.ic_sleep_4
                    5 -> R.drawable.ic_sleep_5
                    else -> R.drawable.ic_sleep_active
                }
            )
        }
        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater =
                    LayoutInflater.from(parent.context)
                val binding =
                    ListItemSleepNightBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }

    }




}
class SleepNightDiffCallback : DiffUtil.ItemCallback<SleepNight>() {
    override fun areItemsTheSame(oldItem: SleepNight, newItem: SleepNight): Boolean {
        return oldItem.nightId == newItem.nightId//para ver si los dos elementos pasados de SleepNight, oldItem y newItem, son iguales.
    }

    override fun areContentsTheSame(oldItem: SleepNight, newItem: SleepNight): Boolean {
        /*
        Dentro de areContentsTheSame(), verifique si oldItem y newItem contienen los mismos datos;  es decir, si son iguales.
        Esta verificación de igualdad verificará todos los campos, porque SleepNight es una clase de datos.  Las clases de datos definen automáticamente
        iguales y algunos otros métodos para usted.  Si hay diferencias entre oldItem y newItem, este código le dice a DiffUtil que el artículo ha sido actualizado.
         */
        return oldItem == newItem
    }
}
