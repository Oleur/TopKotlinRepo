package com.js.topkotlinrepo.ui.repogit

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.js.topkotlinrepo.R
import com.js.topkotlinrepo.ui.common.inflate

class DateArrayAdapter(context: Context, private val resource: Int, private val repoDates: List<DateRange>)
    : ArrayAdapter<DateRange>(context, resource, repoDates) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return (convertView ?: parent.inflate(resource)).apply {
            val dataRange = repoDates[position]
            (this as TextView).text = parent.context.getString(R.string.all_date_range,
                dataRange.startDate.format(), dataRange.endDate.format())
        }
    }
}
