package com.example.hmv3;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.hmv3.tasks.TaskListContent;

import java.util.Calendar;
import java.util.Random;

public class AddContact extends AppCompatActivity {


    // data for the sound
    public static final String SOUND_ID = "sound id";
    public static final int BUTTON_REQUEST = 1;
    private int current_sound = 0;
    Calendar c;
    DatePickerDialog dpd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);




    }

    // declare all the variable that will retreive the data input by the user.

    public void addClick(View view) {
        // Question 13
        // Declare all tha variable
        EditText taskTitleEditTxt = findViewById(R.id.taskTitle);
        EditText taskSurnameEditTxt = findViewById(R.id.taskSurname);
        final EditText taskDescriptionTxt = findViewById(R.id.taskDescription); // the birthday one
        EditText taskPhoneTxt = findViewById(R.id.phoneNumber);
        Spinner taskSpinner = findViewById(R.id.drawableSpinner); // le déroulé

        Button taskAddButton = findViewById(R.id.addButton); // le bouton add
        TypedArray typedArray = getResources().obtainTypedArray(R.array.avatars);

        /*
        c = Calendar.getInstance();
        int day = c.get(Calendar.DAY_OF_MONTH );
        int month = c.get(Calendar.MONTH);
        int year = c.get(Calendar.YEAR);
        dpd = new DatePickerDialog(AddContact.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int myear, int mmonth, int mdayOfMonth) {
                taskDescriptionTxt.setText(mdayOfMonth + "/" + (mmonth+1 ) + "/" + myear );
            }
        }, day,month,year );
        dpd.show();*/

        // random image generator
        final  int random = new Random().nextInt(typedArray.length());


        String taskTitle = taskTitleEditTxt.getText().toString();
        String taskSurname = taskSurnameEditTxt.getText().toString();
        String taskDescription = taskDescriptionTxt.getText().toString();
        String taskPhone = taskPhoneTxt.getText().toString();
        String selectedSound = taskSpinner.getSelectedItem().toString(); // on stocke la var du son choisis par le user.

        if (taskTitle.isEmpty() && taskSurname.isEmpty() && taskDescription.isEmpty() && taskPhone.isEmpty()){
            TaskListContent.addItem(new TaskListContent.Task("task" + TaskListContent.ITEMS.size() + 1,
                    getString(R.string.default_name),
                    getString(R.string.default_surname),
                    getString(R.string.default_birthday),
                    getString(R.string.defautl_phone),
                    getString(R.string.defautl_1),
                    getString(R.string.default_2)/* aza*/,
                    random
                    ));

        }else {
            if (taskTitle.isEmpty())
                taskTitle = getString(R.string.default_name);
            if (taskDescription.isEmpty())
                taskDescription = getString(R.string.default_surname);
            // Add a task to the task list content
            TaskListContent.addItem(new TaskListContent.Task("Task." + TaskListContent.ITEMS.size() + 1,
                    taskTitle,
                    taskSurname,
                    taskDescription,
                    taskPhone,
                    selectedSound,
                    getString(R.string.default_2),random
            ));

        }


        // condition for validate or invalide the input data by the user for birthday and phone number
        /*if (taskDescriptionTxt.length() >=8){
            Toast.makeText(this, getString(R.string.birthday_invalide), Toast.LENGTH_SHORT).show();
        }*/


        // Notify that the data changed
        /*((TaskFragment) getSupportFragmentManager().findFragmentById(R.id.taskFragment)).notifyDataChange();*/


        // Data for the sound that we will give back to the main activity.
        // selectedSound

        /*switch (taskSpinner.getSelectedItem()){
            case R.id.sound1: selected_sound = 0;break;
            case R.id.sound2: selected_sound = 1;break;
            case R.id.sound3: selected_sound = 2;break;
            case R.id.sound4: selected_sound = 3;break;
        }*/










        // Clear the edit txt
        taskTitleEditTxt.setText("");
        taskDescriptionTxt.setText("");
        taskPhoneTxt.setText("");
        taskSurnameEditTxt.setText("");

        // Hide the keyboard
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(),0);

        finish();





    }


}
