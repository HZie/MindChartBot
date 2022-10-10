package hcil.hzie.mindchart;
// tutorial with https://aws.amazon.com/ko/getting-started/hands-on/build-android-app-amplify/

import android.content.Intent
import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton
import hcil.hzie.mindchart.databinding.ActivityMainBinding
import hcil.hzie.mindchart.databinding.ContentMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var activityMain: ActivityMainBinding;
    private lateinit var contentMain: ContentMainBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        activityMain = ActivityMainBinding.inflate(layoutInflater);
        contentMain = ContentMainBinding.inflate(layoutInflater);
        setContentView(activityMain.root);
        setSupportActionBar(activityMain.toolbar);

        // prepare list and recycler view
        setupRecyclerView(contentMain.itemList);

        // authorization setting
        setupAuthButton(UserData);

        UserData.isSignedIn.observe(this, Observer<Boolean> { isSignedUp ->
            // update UI
            Log.i(TAG, "isSignedIn changed: $isSignedUp");

            if(isSignedUp){
                activityMain.fabAuth.setImageResource(R.drawable.ic_baseline_lock_open);
            }
            else{
                activityMain.fabAuth.setImageResource(R.drawable.ic_baseline_lock);
            }
        })
    }

    // recycler view is the list of cells
    private fun setupRecyclerView(recyclerView: RecyclerView){
        // update individual cell when data is modified
        UserData.charts().observe(this, Observer<MutableList<UserData.Chart>> {
            charts -> Log.d(TAG, "Chart observer received ${charts.size} charts");
            // create RecyclerViewAdapter that manages the individual cells
            recyclerView.layoutManager = LinearLayoutManager(this);
            recyclerView.adapter = ChartRecyclerViewAdapter(charts);

        });
    }

    companion object {
        private const val TAG = "MainActivity";
    }

    private fun setupAuthButton(userData: UserData){
        Log.d("authbutton","setup");
        // register a click listener
        activityMain.fabAuth.setOnClickListener{ view ->
            val authButton = view as FloatingActionButton;

            if(userData.isSignedIn.value!!){
                authButton.setImageResource(R.drawable.ic_baseline_lock_open);
                Backend.signOut();
            }
            else{
                authButton.setImageResource(R.drawable.ic_baseline_lock_open);
                Backend.signIn(this);
            }
        }
    }

    // receive the web redirect after authentication
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Backend.handleWebUISignInResponse(requestCode, resultCode, data)
    }
}