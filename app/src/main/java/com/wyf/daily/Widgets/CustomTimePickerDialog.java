package com.wyf.daily.Widgets;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.TimePicker;

import com.wyf.daily.R;

/**
 * 自定义的TimePickerDialog
 * 对于Android 5.1有几个不兼容的方法待解决
 *
 * @author wifi9984
 * @date 2017/8/31
 */

public class CustomTimePickerDialog implements View.OnClickListener,TimePicker.OnTimeChangedListener {
    private View view;
    private Dialog dialog;
    private TimePicker picker_time;

    public CustomTimePickerDialog(Context context) {
        view = LayoutInflater.from(context).inflate(R.layout.custom_time_picker_dialog,null);
        dialog = new Dialog(context,R.style.Theme_AppCompat_Light_Dialog_Alert_Self);
        picker_time = view.findViewById(R.id.custom_time_picker_tp);
        view.findViewById(R.id.custom_time_picker_btn_confirm).setOnClickListener(this);
    }

    public void setTime(int hour,int min,OnTimeSetListener listener) {
        picker_time.setHour(hour);
        picker_time.setMinute(min);
        picker_time.setOnTimeChangedListener(this);
        mTimeSetListener = listener;
    }

    public void show() {
        dialog.getWindow().setContentView(view);
        dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        dialog.show();
    }

    public void dismiss() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    @Override
    public void onClick(View v) {
        dismiss();
        if (mTimeSetListener != null) {
            picker_time.clearFocus();
            mTimeSetListener.onTimeSet(picker_time.getHour(),picker_time.getMinute());
        }
    }

    @Override
    public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
        picker_time.setHour(hourOfDay);
        picker_time.setMinute(minute);
        picker_time.setOnTimeChangedListener(this);
    }

    private OnTimeSetListener mTimeSetListener;
    public interface OnTimeSetListener {
        void onTimeSet(int hour,int min);
    }
}
