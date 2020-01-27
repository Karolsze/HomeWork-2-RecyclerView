package com.example.hmv3;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hmv3.tasks.TaskListContent;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TaskInfoFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TaskInfoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TaskInfoFragment extends Fragment  {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public TaskInfoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TaskInfoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TaskInfoFragment newInstance(String param1, String param2) {
        TaskInfoFragment fragment = new TaskInfoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_task_info, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement InteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }



    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public void displayTask(TaskListContent.Task task){
        FragmentActivity activity = getActivity(); // get the holding activity
        // find the element used to display the data of the task

        TextView taskInfoName = activity.findViewById(R.id.taskInfoName);
        TextView taskInfoPhone = activity.findViewById(R.id.Phone);
        TextView taskInfoBirthday = activity.findViewById(R.id.Birthday);
        TextView taskInfoDescription = activity.findViewById(R.id.taskInfoDescription);
        ImageView taskInfoImage = activity.findViewById(R.id.taskInfoImage);


        // apply the data
        // task.details == surname
        // task.picpath == birthday
        // task.birthday == current sound

        taskInfoName.setText(task.title);// c'est bien le titre
        taskInfoPhone.setText("Phone number:  "+ task.phone); //le 06
        //taskInfoPhone.setText(task.phone); // C'est bien le 06
        taskInfoBirthday.setText("Birthday:  "+task.picPath);  //
        taskInfoDescription.setText("Current sound: " + task.birthday); // C'est le bon sound

        //taskInfoDescription.setText(task.birthday);// le current sound
        //taskInfoBirthday.setText(task.birthday); // C'est le sound, aucune idée comment il s'est retrouvé la
        //taskInfoBirthday.setText(task.current_sound);// a revoir, j'ai mal programmé mon task mais nik
        //taskInfoBirthday.setText(task.birthday); // C'est le sound, aucune idée comment il s'est retrouvé la
        //taskInfoDescription.setText(task.details);// C'est mon surname (nom de famille)


        TypedArray typedArray = getResources().obtainTypedArray(R.array.avatars);
        Drawable contactDrawable = typedArray.getDrawable(task.imageIndex);
        taskInfoImage.setImageDrawable(contactDrawable);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Intent intent = getActivity().getIntent();
        if (intent != null){
            TaskListContent.Task receivedTask = intent.getParcelableExtra(MainActivity.taskExtra);
            if (receivedTask != null) {
                displayTask(receivedTask);
            }
        }
    }




}
