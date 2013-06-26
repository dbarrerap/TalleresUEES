package org.dbarrera.examen;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity implements View.OnClickListener {

    Button new_ws, show_ws, new_student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupWidgets();
    }

    private void setupWidgets() {
        new_ws = (Button)findViewById(R.id.new_workshop);
        new_ws.setOnClickListener(this);
        new_student = (Button)findViewById(R.id.new_student);
        new_student.setOnClickListener(this);
        show_ws = (Button)findViewById(R.id.browse_workshop);
        show_ws.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.new_workshop:
                Intent iNewWorkshop = new Intent(this, NewWorkshop.class);
                startActivity(iNewWorkshop);
                break;
            case R.id.browse_workshop:
                Intent iBrowseWorkshop = new Intent(this, BrowseWorkshop.class);
                startActivity(iBrowseWorkshop);
                break;
            case R.id.new_student:
                Intent iNewStudent = new Intent(this, NewStudent.class);
                startActivity(iNewStudent);
                break;
        }
    }


    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }*/
    
}
