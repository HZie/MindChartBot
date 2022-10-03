package hcil.hzie.mindchart;

import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import hcil.hzie.mindchart.databinding.ActivityMainBinding
import hcil.hzie.mindchart.databinding.ContentMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var activityMain: ActivityMainBinding;
    private lateinit var contentMain: ContentMainBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        activityMain = ActivityMainBinding.inflate(layoutInflater);
        contentMain = ContentMainBinding.inflate(layoutInflater);
        setContentView(R.layout.activity_main);
        setSupportActionBar(activityMain.toolbar);

        // prepare list and recycler view
        setupRecyclerView(contentMain.itemList);
    }

    // recycler view is the list of cells
    private fun setupRecyclerView(recyclerView: RecyclerView){
        // update individual cell when data is modified
        UserData.charts().observe(this, Observer<MutableList<UserData.Chart>> {
            charts -> Log.d(TAG, "Chart observer received ${charts.size} charts");
            // create RecyclerViewAdapter that manages the individual cells
            recyclerView.adapter = ChartRecyclerViewAdpater(charts);
        });
    }

    companion object {
        private const val TAG = "MainActivity";
    }
}