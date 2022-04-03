package com.amirlabs.moklet_meet.utils

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.view.View
import android.widget.DatePicker
import android.widget.TimePicker
import java.util.*

object DialogHelper {
    val c = Calendar.getInstance()
    val y = c.get(Calendar.YEAR)
    val m = c.get(Calendar.MONTH)
    val d = c.get(Calendar.DAY_OF_MONTH)
    val h = c.get(Calendar.HOUR_OF_DAY);
    val min = c.get(Calendar.MINUTE);

    fun datePickerDialog(ctx: Context, listener: (DatePicker, Int, Int, Int) -> Unit):DatePickerDialog {

        val dpd = DatePickerDialog(ctx, { view, year, month, dayOfMonth ->
            listener(view, year, month, dayOfMonth)
        }, y, m, d)
        dpd.datePicker.minDate = c.timeInMillis
        dpd.show()
        return dpd
    }
    fun timePickerDialog(ctx: Context, listener: (TimePicker, Int, Int) -> Unit):TimePickerDialog{
        val tpd = TimePickerDialog(ctx,{ view, hourOfDay, minute ->
            listener(view,hourOfDay,minute)
        },h, min,true)
        tpd.show()
        return tpd
    }
}