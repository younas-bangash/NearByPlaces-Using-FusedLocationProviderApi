package example.nearmeplaces;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Bangash on 3/5/2016.
 */
public class PlaceItemAdapter extends RecyclerView.Adapter<PlaceItemAdapter.DataObjectHolder> {
    private static String LOG_TAG = "PlaceItemAdapter";
    private ArrayList<String> mDataset;
    private String mCurrentAddress;

    public static class DataObjectHolder extends RecyclerView.ViewHolder {
        TextView label;
        public DataObjectHolder(View itemView) {
            super(itemView);
            label = (TextView) itemView.findViewById(R.id.textView);
           }

    }

    public PlaceItemAdapter(ArrayList<String> myDataset) {
        mDataset = myDataset;
    }

    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item, parent, false);
        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(DataObjectHolder holder, int position) {
        holder.label.setText(mDataset.get(position));
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

}