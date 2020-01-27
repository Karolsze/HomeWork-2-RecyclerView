package com.example.hmv3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.Intent;
import android.content.res.Configuration;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.hmv3.tasks.TaskListContent;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements TaskFragment.OnListFragmentInteractionListener,
        DeleteDialog.OnDeleteDialogInteractionListener, TaskInfoFragment.OnFragmentInteractionListener {

    public static final String taskExtra = "taskExtra";
    private int currentItemPosition = -1;
    //public MediaPlayer backgroundPlayer;
    public MediaPlayer buttonPlayer;
    static public Uri[] sounds;

    public static final String SOUND_ID = "sound id";
    public static final int BUTTON_REQUEST = 1;
    private int current_sound = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final FloatingActionButton fbutton = (FloatingActionButton)findViewById(R.id.addButton);
        fbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent launch = new Intent(getApplicationContext(), AddContact.class);
                launch.putExtra(taskExtra, taskExtra );
                startActivity(launch);

                sounds = new Uri[4];
                //Use parse method of the Uri class to obtain the Uri of a resource
                //specified by a string
                sounds[0] = Uri.parse("android.resource://" + getPackageName() + "/" +
                        R.raw.ring01);
                sounds[1] = Uri.parse("android.resource://" + getPackageName() + "/" +
                        R.raw.ring02);
                sounds[2] = Uri.parse("android.resource://" + getPackageName() + "/" +
                        R.raw.ring03);
                sounds[3] = Uri.parse("android.resource://" + getPackageName() + "/" +
                        R.raw.ring04);
                buttonPlayer = new MediaPlayer();
                buttonPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                buttonPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        mp.start();
                    }
                });

            }

        });


        // putting the sound code here



    }

    /*public void addClickFloating(View view) {
        Intent intent = new Intent(this,AddContact.class);
        intent.putExtra(taskExtra, );
        startActivity(intent);

    }*/

    private void displayTaskFragment(TaskListContent.Task task){
        //find the display fragment
        TaskInfoFragment taskInfoFragment = ((TaskInfoFragment) getSupportFragmentManager().findFragmentById(R.id.displayFragment));
        if (taskInfoFragment != null){
            // Display the task if display Fragment exist
            taskInfoFragment.displayTask(task);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Notify that the data changed
        ((TaskFragment) getSupportFragmentManager().findFragmentById(R.id.taskFragment)).notifyDataChange();
    }

    private void startSecondActivity(TaskListContent.Task task, int position){
        Intent intent = new Intent(this,TaskInfoActivity.class);
        intent.putExtra(taskExtra, task);
        startActivity(intent);
    }

    @Override
    public void onListFragmentClickInteraction(TaskListContent.Task task, int position) {
        Toast.makeText(this, getString(R.string.item_selected_msg), Toast.LENGTH_SHORT).show();
        // Check the current orientation
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            displayTaskFragment(task);
        }else {
            startSecondActivity(task, position);
        }

       // startSecondActivity(task, position);

    }



    @Override
    public void onListFragmentLongClickInteraction(com.example.hmv3.tasks.TaskListContent.Task task,int position) {
        // put the sound code here.
        Toast.makeText(this, getString(R.string.item_selected_sound), Toast.LENGTH_SHORT).show();
        // retreive the info from the spinner
        //start the code for the sound here
        String sound_str = task.birthday;
        switch (sound_str){
            case "Sound 1":
                current_sound = 0;
                break;
            case "Sound 2":
                current_sound = 1;
                break;
            case "Sound 3":
                current_sound = 2;
                break;
            case "Sound 4":
                current_sound = 3;
                break;
            default:
                current_sound = 0;
                break;
        }
        buttonPlayer.reset();
        try {
            buttonPlayer.setDataSource(getApplicationContext(),sounds[current_sound]);
        } catch (IOException e ){
            e.printStackTrace();
        }
        buttonPlayer.prepareAsync();

    }

    @Override
    public void onDeleteButtonClickInteraction(int position) {
        // lancer la boite de dialogue delete
        Toast.makeText(this,getString(R.string.long_click_msg) + currentItemPosition, Toast.LENGTH_SHORT).show();
        showDeleteDialog();
        currentItemPosition = position;


    }

    private void showDeleteDialog(){
        DeleteDialog.newInstance().show(getSupportFragmentManager(),getString(R.string.delete_item));
    }


    // Method that decide what to do when the user click on yes or no
    // below, the yes one
    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        if (currentItemPosition != -1 && currentItemPosition < TaskListContent.ITEMS.size()){
            TaskListContent.removeItem(currentItemPosition);
            ((TaskFragment) getSupportFragmentManager().findFragmentById(R.id.taskFragment)).notifyDataChange();
        }

    }
    // here the no one
    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {
        View v = findViewById(R.id.addButton);
        if (v!= null){
            Snackbar.make(v,getString(R.string.delete_cancel_msg), Snackbar.LENGTH_LONG)
                    .setAction(getString(R.string.retry_msg), new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            showDeleteDialog();
                        }
                    }).show();

        }

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    // the delete button
    // the sound





}
