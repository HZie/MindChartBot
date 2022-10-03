package hcil.hzie.mindchart;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

// single row in the list of data
class ChartRecyclerViewAdpater(private val values: MutableList<UserData.Chart>?) : RecyclerView.Adapter<ChartRecyclerViewAdpater.ViewHolder>(){
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.data_chart, parent, false);
        return ViewHolder(view);
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values?.get(position);
        holder.dateView.text = item?.date;
        holder.valView.text = item?.value.toString();
    }

    override fun getItemCount() = values?.size ?:0;

    inner class ViewHolder(view:View) : RecyclerView.ViewHolder(view){
        val dateView: TextView = view.findViewById(R.id.date);
        val valView: TextView = view.findViewById(R.id.value);
    }
}

