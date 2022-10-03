package hcil.hzie.mindchart;
import android.graphics.Bitmap;
import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

// 사용자 데이터를 저장할 singleton(singleton: 객체가 오직 1개만 생성되는 패턴)
// ViewModel에서 상속받지 않은 ViewModel 패턴
object UserData {
    private const val TAG = "UserData";
    //
    //observable properties
    //

    // signed in status
    private val _isSignedIn = MutableLiveData<Boolean>(false);
    var isSignedIn: LiveData<Boolean> = _isSignedIn;

    fun setSignedIn(newVal: Boolean){
        // use postvalue() to make the assignation on the main (UI)
        _isSignedIn.postValue(newVal);
    }

    // chart data
    private val _charts = MutableLiveData<MutableList<Chart>>(mutableListOf());

    // Notify Observer when item is added to List of LiveData
    // reference link: https://stackoverflow.com/questions/47941537/notify-observer-when-item-is-added-to-list-of-livedata
    private fun <T> MutableLiveData<T>.notifyObserver(){
        this.postValue(this.value);
    }

    fun notifyObserver(){
        this._charts.notifyObserver();
    }

    fun charts() : LiveData<MutableList<Chart>> = _charts;

    fun addChart(n : Chart){
        val charts = _charts.value;
        if(charts != null){
            charts.add(n);
            _charts.notifyObserver();
        }
        else{
            Log.e(TAG, "addNote: note collection is null");
        }
    }

    fun deleteChart(at: Int) : Chart?{    // null을 리턴할 수도 있음
        val note = _charts.value?.removeAt(at);
        _charts.notifyObserver();
        return note;
    }

    fun resetCharts(){
        this._charts.value?.clear(); // used when signing out
        _charts.notifyObserver();
    }

    // note class
    data class Chart(val id: String, val date: String, val value: Int){
        override fun toString(): String = date;
    }

}