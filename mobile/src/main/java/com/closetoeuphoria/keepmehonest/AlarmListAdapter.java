package com.closetoeuphoria.keepmehonest;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.List;

public class AlarmListAdapter extends RecyclerView.Adapter<AlarmListAdapter.AlarmViewHolder> {
//    private final static String TAG = "AlarmListAdapter";
    private List<AlarmModel> mAlarms;
    private Context mContext;

    // Provide a suitable constructor (depends on the kind of dataset)
    public AlarmListAdapter(Context context, List<AlarmModel> alarms) {
        mAlarms = alarms;
        mContext = context;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public AlarmViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        //TODO: This crashes when real data exists.
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.alarm_list_item, parent, false);
        // set the view's size, margins, padding and layout parameters
        return new AlarmViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(AlarmViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
//        holder.mTextView.setText(mDataset[position]);
        AlarmModel model = (AlarmModel) getItem(position);
        holder.vTime.setText(String.format("%02d : %02d", model.timeHour, model.timeMinute));
        holder.vName.setText(model.name);
        updateTextColor(holder.vSunday,    model.getRepeatingDay(AlarmModel.SUNDAY));
        updateTextColor(holder.vMonday,    model.getRepeatingDay(AlarmModel.MONDAY));
        updateTextColor(holder.vTuesday,   model.getRepeatingDay(AlarmModel.TUESDAY));
        updateTextColor(holder.vWednesday, model.getRepeatingDay(AlarmModel.WEDNESDAY));
        updateTextColor(holder.vThursday,  model.getRepeatingDay(AlarmModel.THURSDAY));
        updateTextColor(holder.vFriday,    model.getRepeatingDay(AlarmModel.FRIDAY));
        updateTextColor(holder.vSaturday,  model.getRepeatingDay(AlarmModel.SATURDAY));
        holder.vToggle.setChecked(model.isEnabled);
        holder.vToggle.setTag(model.id);
        holder.vToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                ((AlarmListActivity) mContext).setAlarmEnabled((Long) buttonView.getTag(), isChecked);
            }
        });
//        holder.setTag(model.id);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        if (mAlarms == null)
            return 0;
        else
            return mAlarms.size();
    }

    private void updateTextColor(TextView view, boolean isOn) {
        if (isOn) {
            view.setTextColor(Color.GREEN);
        } else {
            view.setTextColor(Color.BLACK);
        }
    }
    public Object getItem(int position) {
        if (mAlarms != null) {
            return mAlarms.get(position);
        }
        return null;
    }
    @Override
    public long getItemId(int position) {
        if (mAlarms != null) {
            return mAlarms.get(position).id;
        }
        return 0;
    }
    public void setAlarms(List<AlarmModel> alarms) {
        mAlarms = alarms;
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class AlarmViewHolder extends RecyclerView.ViewHolder {
        protected TextView vTime;
        protected TextView vName;
        protected TextView vSunday;
        protected TextView vMonday;
        protected TextView vTuesday;
        protected TextView vWednesday;
        protected TextView vThursday;
        protected TextView vFriday;
        protected TextView vSaturday;
        protected ToggleButton vToggle;
        public AlarmViewHolder(View v) {
            super(v);
            vTime      = (TextView)     v.findViewById(R.id.alarm_item_time);
            vName      = (TextView)     v.findViewById(R.id.alarm_item_name);
            vSunday    = (TextView)     v.findViewById(R.id.alarm_item_sunday);
            vMonday    = (TextView)     v.findViewById(R.id.alarm_item_monday);
            vTuesday   = (TextView)     v.findViewById(R.id.alarm_item_tuesday);
            vWednesday = (TextView)     v.findViewById(R.id.alarm_item_wednesday);
            vThursday  = (TextView)     v.findViewById(R.id.alarm_item_thursday);
            vFriday    = (TextView)     v.findViewById(R.id.alarm_item_friday);
            vSaturday  = (TextView)     v.findViewById(R.id.alarm_item_saturday);
            vToggle    = (ToggleButton) v.findViewById(R.id.alarm_item_toggle);
        }
    }
}