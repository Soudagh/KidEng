package com.example.myproject;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


public class GameTextFragment extends Fragment {

    private static final String ARG_TEXT = "text";

    TextView textView;
    public GameTextFragment() {

    }


    public static GameTextFragment newInstance(String text) {
        GameTextFragment fragment = new GameTextFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TEXT, text);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        Thread thread = new Thread() {
//            @Override
//          public void run() {
//              try {
//                  while(!thread.isInterrupted()) {
//                      Thread.sleep(1000);
//                      runOnUiThread(new Runnable() {
//                          @Override
//                                  public void run() {
//
//                          }
//                      });
//                  }
//              } catch (InterruptedException e) {
//
//              }
//          }
//        };
//
//        thread.start();

        if (getArguments() != null) {

        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.game_text_fragment, container, false);

        textView = (TextView) view.findViewById(R.id.seconds_start_tv);

        new CountDownTimer(6000, 1000) {
            @Override
            public void onTick(final long l) {
                ;
                textView.setText(" " + (int) (l * .001f));
            }

            @Override
            public void onFinish() {
                textView.setText(" GO!");
                Activity activity = getActivity();
                if (activity instanceof GameActivity) {
                    ((GameActivity)activity).onGameStart();
                }
            }
        }.start();
        return view;
    }
}