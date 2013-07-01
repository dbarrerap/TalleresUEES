package org.dbarrera.examen;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

/**
 * Created by david on 6/25/13.
 */
public class NewWorkshop extends Activity implements View.OnClickListener {

    EditText works_name, works_code, works_credits, works_teacher, works_unit, works_schedule, works_hours;
    DatePicker works_date;
    Button works_save;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_workshop);

        if (savedInstanceState == null){
            setupWidgets();
        }
    }

    private void setupWidgets() {
        works_name = (EditText)findViewById(R.id.et_w_name);
        works_code = (EditText)findViewById(R.id.et_w_code);
        works_credits = (EditText)findViewById(R.id.et_w_credits);
        works_teacher = (EditText)findViewById(R.id.et_w_teacher);
        works_unit = (EditText)findViewById(R.id.et_w_unit);
        works_schedule = (EditText)findViewById(R.id.et_w_schedule);
        works_hours = (EditText)findViewById(R.id.et_w_hours);

        works_date = (DatePicker)findViewById(R.id.datePicker);

        works_save = (Button)findViewById(R.id.button_nw_save);
        works_save.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.button_nw_save){
            String workshop_date = String.valueOf(works_date.getYear()) + "-" + checkDigit(works_date.getMonth() + 1) + "-" + checkDigit(works_date.getDayOfMonth());
            wsClient wsc = new wsClient(this);
            wsc.setWorkParam(works_name.getText().toString(), works_code.getText().toString(), works_credits.getText().toString(), works_teacher.getText().toString(), works_unit.getText().toString(), workshop_date, works_schedule.getText().toString(), works_hours.getText().toString());
            wsc.run();
            finish();
        }
    }

    private String checkDigit(int number){
        return number<=9?"0"+number:String.valueOf(number);
    }
}