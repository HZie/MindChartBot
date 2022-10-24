package hcil.hzie.mindchart;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder> {
    private int SENT_MSG = 101;
    private int RECEIVED_MSG = 102;
    @NonNull
    @Override
    public ChatAdapter.ViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent,
            int viewType
    ){
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(getViewSrc(viewType), parent, false);
        return new ViewHolder(view, viewType);
    }

    @Override
    public void onBindViewHolder(
            @NonNull ChatAdapter.ViewHolder holder,
            int position
    ){
        holder.bind(dataSet.get(position));
    }

    @Override
    public int getItemCount(){
        return dataSet.size();
    }

    // view holder
    public class ViewHolder extends RecyclerView.ViewHolder{
        private int viewType;
        public ViewHolder(@NonNull View itemView, int viewType){
            super(itemView);
            this.viewType = viewType;
        }

        public void bind(ChatData item){
            // todo: show data
            if(viewType==SENT_MSG){
                bindSentMsg(item);
            }
            else if(viewType==RECEIVED_MSG){
                bindReceivedMsg(item);
            }
        }

        private void bindSentMsg(ChatData item){
            MaterialTextView sentMsg = itemView.findViewById(R.id.sent_msg);
            MaterialTextView sentTime = itemView.findViewById(R.id.sent_time);
            sentMsg.setText(item.getMessage());
            sentTime.setText(item.getTimestamp());
        }

        private void bindReceivedMsg(ChatData item){
            MaterialTextView receivedMsg = itemView.findViewById(R.id.received_msg);
            MaterialTextView receivedTime = itemView.findViewById(R.id.received_time);
            MaterialTextView receivedName = itemView.findViewById(R.id.received_name);

            receivedMsg.setText(item.getMessage());
            receivedTime.setText(item.getTimestamp());
            receivedName.setText(item.getName());
        }
    }

    private ArrayList<ChatData> dataSet = new ArrayList<>();
    public void submitData(ArrayList<ChatData> newData){
        dataSet = newData;
        notifyDataSetChanged();
    }

    // view type
    private int getViewSrc(int viewType){
        // todo: connect xml
        if(viewType == RECEIVED_MSG){
            return R.layout.chat_received_message;
        }
        else{
            return R.layout.chat_sent_message;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if(dataSet.get(position).getName().equals("user")){
            return SENT_MSG;
        }
        return RECEIVED_MSG;
    }
}
