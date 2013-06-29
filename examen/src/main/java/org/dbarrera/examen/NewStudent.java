package org.dbarrera.examen;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

/**
 * Created by david on 6/25/13.
 */
public class NewStudent extends Activity implements View.OnClickListener {

    EditText stud_name, stud_id, stud_address, stud_telephone, stud_email, stud_carrera, stud_year, stud_workshop;
    Button stud_save;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_student);

        setupWidgets();
    }

    private void setupWidgets() {
        stud_name = (EditText)findViewById(R.id.et_s_name);
        stud_id = (EditText)findViewById(R.id.et_s_id);
        stud_address = (EditText)findViewById(R.id.et_s_addr);
        stud_telephone = (EditText)findViewById(R.id.et_s_telephone);
        stud_email = (EditText)findViewById(R.id.et_s_email);
        stud_carrera = (EditText)findViewById(R.id.et_s_carrera);
        stud_year = (EditText)findViewById(R.id.et_s_year);
        stud_workshop = (EditText)findViewById(R.id.et_s_workshop);
        stud_save = (Button)findViewById(R.id.button_s_save);
        stud_save.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.button_s_save){
            studentDetails stud = new studentDetails();
            stud.setName(stud_name.getText().toString());
            stud.setMatricula(stud_id.getText().toString());
            stud.setDireccion(stud_address.getText().toString());
            stud.setTelefono(stud_telephone.getText().toString());
            stud.setEmail(stud_email.getText().toString());
            stud.setCarrera(stud_carrera.getText().toString());
            stud.setYear(stud_year.getText().toString());
            stud.setCurso(stud_workshop.getText().toString());

            insertData(stud);
        }
    }

    private void insertData(studentDetails stud) {
        wsClient wsc = new wsClient();
        wsc.setStudParam(stud.getName(), stud.getMatricula(), stud.getDireccion(), stud.getTelefono(), stud.getEmail(), stud.getCarrera(), stud.getYear(), stud.getCurso());
        wsc.run(this);
    }
}