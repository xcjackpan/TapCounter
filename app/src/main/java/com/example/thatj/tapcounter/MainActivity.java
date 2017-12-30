package com.example.thatj.tapcounter;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity{

    ViewGroup relativeLayout;
    TextView counterText;
    Button oopsButton;
    Button zeroButton;
    Button countByButton;
    ToggleButton negativeToggle;
    boolean negAllow = false;
    public int countByNumber = 1;

    int counter = 0;
    static final String COUNTER_NUMBER = "counterNumber";
    static final String COUNTBY_NUMBER = "countByNumber";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        relativeLayout = (ViewGroup) findViewById(R.id.relativeLayout);
        counterText = (TextView) findViewById(R.id.counterText);
        oopsButton = (Button) findViewById(R.id.backButton);
        negativeToggle = (ToggleButton) findViewById(R.id.negativeToggle);
        zeroButton = (Button) findViewById(R.id.zeroButton);
        countByButton = (Button) findViewById(R.id.countBy);

        final int toggleOnColor = Color.rgb(0,100,0);
        final ColorStateList toggleOffColor = oopsButton.getTextColors(); //obtaining default color

        if (savedInstanceState != null) { //if a saved state exists
            counter = savedInstanceState.getInt(COUNTER_NUMBER);
            countByNumber = savedInstanceState.getInt(COUNTBY_NUMBER);
            counterText.setText(String.valueOf(counter));
            countByButton.setText("COUNT BY " + String.valueOf(countByNumber));
        } else {
            countByButton.setText("COUNT BY " + String.valueOf(countByNumber));
        }


        relativeLayout.setOnClickListener(
                new RelativeLayout.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (counter < 10000) {
                            counter = counter + countByNumber;
                            counterText.setText(String.valueOf(counter));
                        } else {
                            counter = 0;
                            counterText.setText(String.valueOf(counter));
                        }
                    }
                }
        );

        oopsButton.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        if (negAllow) { //if it's checked
                            counter = counter - countByNumber;
                        }

                        if (counter > 0 && !negAllow) { //if it's not checked
                            counter = counter - countByNumber;
                            if (counter < 0) {
                                counter = 0;
                            }
                        }

                        counterText.setText(String.valueOf(counter));
                    }
                }
        );

        zeroButton.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick (View v) {
                        counter = 0;
                        counterText.setText(String.valueOf(counter));
                    }
                }
        );

        negativeToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged (CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    negativeToggle.setTextColor(toggleOnColor);
                    negAllow = true;
                } else {
                    negativeToggle.setTextColor(toggleOffColor);
                    negAllow = false;
                    if (counter < 0) {
                        counter = 0;
                        counterText.setText(String.valueOf(counter));
                    }

                }
            }
        });

        countByButton.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick (View v) {

                        if (countByNumber >= 9) {
                            countByNumber = 1;
                        } else {
                            countByNumber++;
                        }
                        countByButton.setText("COUNT BY " + String.valueOf(countByNumber));
                    }
                }
        );
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) { //screen orientation changes will revert
        outState.putInt(COUNTER_NUMBER, counter);
        outState.putInt(COUNTBY_NUMBER, countByNumber);//passing in counter value
        super.onSaveInstanceState(outState);
    }
}
