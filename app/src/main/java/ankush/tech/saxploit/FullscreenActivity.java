package ankush.tech.saxploit;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class FullscreenActivity extends AppCompatActivity {

    private static final boolean AUTO_HIDE = true;

    /**
     * If {@link #AUTO_HIDE} is set, the number of milliseconds to wait after
     * user interaction before hiding the system UI.
     */
    private static final int AUTO_HIDE_DELAY_MILLIS = 1000;

    /**
     * Some older devices needs a small delay between UI widget updates
     * and a change of the status and navigation bar.
     */
    private static final int UI_ANIMATION_DELAY = 100;
    private final Handler mHideHandler = new Handler();
    private View mContentView;
    private final Runnable mHidePart2Runnable = new Runnable() {
        @SuppressLint("InlinedApi")
        @Override
        public void run() {
            // Delayed removal of status and navigation bar

            // Note that some of these constants are new as of API 16 (Jelly Bean)
            // Note that some of these constants are new as of API 16 (Jelly Bean)
            // and API 19 (KitKat). It is safe to use them, as they are inlined
            // at compile-time and do nothing on earlier devices.
            mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        }
    };
    private boolean mVisible;
    private final Runnable mHideRunnable = new Runnable() {
        @Override
        public void run() {
            hide();
        }
    };
    private float X;
    private float Y;
    private int eventMask = -1;
    private int action;
    private float pressure;
    private float orientation;
    private long eventTime;
    /**
     * Touch listener to use for in-layout UI controls to delay hiding the
     * system UI. This is to prevent the jarring behavior of controls going away
     * while interacting with activity UI.
     */

    private final View.OnTouchListener mTouchListener = new View.OnTouchListener() {

        public boolean onTouch(View view, MotionEvent motionEvent) {
            Button button = (Button) findViewById(view.getId());
            int buttonId = view.getId() - R.id.button1;
            Log.d("S", Integer.toString(buttonId + 1));
            X = motionEvent.getX();
            Y = motionEvent.getY();
            eventMask = motionEvent.getActionMasked();
            eventTime = motionEvent.getEventTime();
            action = motionEvent.getAction();
            pressure = motionEvent.getPressure();
            orientation = motionEvent.getOrientation();
            Log.d("X: ", Float.toString(X));
            Log.d("Y: ", Float.toString(Y));
            Log.d("eventMask: ", Float.toString(eventMask));
            Log.d("action: ", Float.toString(action));
            Log.d("pressure: ", Float.toString(pressure));
            Log.d("orientation: ", Float.toString(orientation));
            Log.d("eventTime: ", Long.toString(eventTime));
            return false;
        }
    };
    private LocationManager locationManager;
    private LocationListener locationListener;
    private SensorManager mSensorManager;
    private SensorEventListener mSensorListener;
    private Boolean recording = false;
    private String label = "stop";

    private final View.OnClickListener mClickListener = new View.OnClickListener() {
        int[] numClicks = new int[144];

        public void onClick(View view) {
            Button button = (Button) findViewById(view.getId());
            int buttonId = view.getId() - R.id.button1;
            label = Integer.toString(buttonId + 1);
            numClicks[buttonId]++;
            //  Log.d("Label",label);
            switch (numClicks[buttonId]) {
                case 1:
                    button.setBackgroundColor(Color.RED);
                    break;
                case 2:
                    button.setBackgroundColor(Color.GREEN);
                    break;
                case 3:
                    button.setBackgroundColor(Color.BLUE);
                    break;
                case 4:
                    button.setBackgroundColor(Color.CYAN);
                    break;
                case 5:
                    button.setBackgroundColor(Color.MAGENTA);
                    break;
                case 6:
                    button.setBackgroundColor(Color.YELLOW);
                    break;
                case 7:
                    button.setBackgroundColor(Color.BLACK);
                    break;
                case 8:
                    button.setBackgroundColor(Color.GRAY);
                    break;
                case 9:
                    button.setBackgroundColor(Color.DKGRAY);
                    break;
                case 10:
                    button.setVisibility(View.INVISIBLE);
            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen);
        mVisible = true;
        mContentView = findViewById(R.id.fullscreen_content);

        // Set up the user interaction to manually show or hide the system UI.
        mContentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (false) {
                    //toggle();
                }
            }
        });


        // Upon interacting with UI controls, delay any scheduled hide()
        // operations to prevent the jarring behavior of controls going away
        // while interacting with the UI.
        findViewById(R.id.button1).setOnClickListener(mClickListener);
        findViewById(R.id.button2).setOnClickListener(mClickListener);
        findViewById(R.id.button3).setOnClickListener(mClickListener);
        findViewById(R.id.button4).setOnClickListener(mClickListener);
        findViewById(R.id.button5).setOnClickListener(mClickListener);
        findViewById(R.id.button6).setOnClickListener(mClickListener);
        findViewById(R.id.button7).setOnClickListener(mClickListener);
        findViewById(R.id.button8).setOnClickListener(mClickListener);
        findViewById(R.id.button9).setOnClickListener(mClickListener);
        findViewById(R.id.button10).setOnClickListener(mClickListener);
        findViewById(R.id.button11).setOnClickListener(mClickListener);
        findViewById(R.id.button12).setOnClickListener(mClickListener);
        findViewById(R.id.button13).setOnClickListener(mClickListener);
        findViewById(R.id.button14).setOnClickListener(mClickListener);
        findViewById(R.id.button15).setOnClickListener(mClickListener);
        findViewById(R.id.button16).setOnClickListener(mClickListener);
        findViewById(R.id.button17).setOnClickListener(mClickListener);
        findViewById(R.id.button18).setOnClickListener(mClickListener);
        findViewById(R.id.button19).setOnClickListener(mClickListener);
        findViewById(R.id.button20).setOnClickListener(mClickListener);
        findViewById(R.id.button21).setOnClickListener(mClickListener);
        findViewById(R.id.button22).setOnClickListener(mClickListener);
        findViewById(R.id.button23).setOnClickListener(mClickListener);
        findViewById(R.id.button24).setOnClickListener(mClickListener);
        findViewById(R.id.button25).setOnClickListener(mClickListener);
        findViewById(R.id.button26).setOnClickListener(mClickListener);
        findViewById(R.id.button27).setOnClickListener(mClickListener);
        findViewById(R.id.button28).setOnClickListener(mClickListener);
        findViewById(R.id.button29).setOnClickListener(mClickListener);
        findViewById(R.id.button30).setOnClickListener(mClickListener);
        findViewById(R.id.button31).setOnClickListener(mClickListener);
        findViewById(R.id.button32).setOnClickListener(mClickListener);
        findViewById(R.id.button33).setOnClickListener(mClickListener);
        findViewById(R.id.button34).setOnClickListener(mClickListener);
        findViewById(R.id.button35).setOnClickListener(mClickListener);
        findViewById(R.id.button36).setOnClickListener(mClickListener);
        findViewById(R.id.button37).setOnClickListener(mClickListener);
        findViewById(R.id.button38).setOnClickListener(mClickListener);
        findViewById(R.id.button39).setOnClickListener(mClickListener);
        findViewById(R.id.button40).setOnClickListener(mClickListener);
        findViewById(R.id.button41).setOnClickListener(mClickListener);
        findViewById(R.id.button42).setOnClickListener(mClickListener);
        findViewById(R.id.button43).setOnClickListener(mClickListener);
        findViewById(R.id.button44).setOnClickListener(mClickListener);
        findViewById(R.id.button45).setOnClickListener(mClickListener);
        findViewById(R.id.button46).setOnClickListener(mClickListener);
        findViewById(R.id.button47).setOnClickListener(mClickListener);
        findViewById(R.id.button48).setOnClickListener(mClickListener);
        findViewById(R.id.button49).setOnClickListener(mClickListener);
        findViewById(R.id.button50).setOnClickListener(mClickListener);
        findViewById(R.id.button51).setOnClickListener(mClickListener);
        findViewById(R.id.button52).setOnClickListener(mClickListener);
        findViewById(R.id.button53).setOnClickListener(mClickListener);
        findViewById(R.id.button54).setOnClickListener(mClickListener);
        findViewById(R.id.button55).setOnClickListener(mClickListener);
        findViewById(R.id.button56).setOnClickListener(mClickListener);
        findViewById(R.id.button57).setOnClickListener(mClickListener);
        findViewById(R.id.button58).setOnClickListener(mClickListener);
        findViewById(R.id.button59).setOnClickListener(mClickListener);
        findViewById(R.id.button60).setOnClickListener(mClickListener);
        findViewById(R.id.button61).setOnClickListener(mClickListener);
        findViewById(R.id.button62).setOnClickListener(mClickListener);
        findViewById(R.id.button63).setOnClickListener(mClickListener);
        findViewById(R.id.button64).setOnClickListener(mClickListener);
        findViewById(R.id.button65).setOnClickListener(mClickListener);
        findViewById(R.id.button66).setOnClickListener(mClickListener);
        findViewById(R.id.button67).setOnClickListener(mClickListener);
        findViewById(R.id.button68).setOnClickListener(mClickListener);
        findViewById(R.id.button69).setOnClickListener(mClickListener);
        findViewById(R.id.button70).setOnClickListener(mClickListener);
        findViewById(R.id.button71).setOnClickListener(mClickListener);
        findViewById(R.id.button72).setOnClickListener(mClickListener);
        findViewById(R.id.button73).setOnClickListener(mClickListener);
        findViewById(R.id.button74).setOnClickListener(mClickListener);
        findViewById(R.id.button75).setOnClickListener(mClickListener);
        findViewById(R.id.button76).setOnClickListener(mClickListener);
        findViewById(R.id.button77).setOnClickListener(mClickListener);
        findViewById(R.id.button78).setOnClickListener(mClickListener);
        findViewById(R.id.button79).setOnClickListener(mClickListener);
        findViewById(R.id.button80).setOnClickListener(mClickListener);
        findViewById(R.id.button81).setOnClickListener(mClickListener);
        findViewById(R.id.button82).setOnClickListener(mClickListener);
        findViewById(R.id.button83).setOnClickListener(mClickListener);
        findViewById(R.id.button84).setOnClickListener(mClickListener);
        findViewById(R.id.button85).setOnClickListener(mClickListener);
        findViewById(R.id.button86).setOnClickListener(mClickListener);
        findViewById(R.id.button87).setOnClickListener(mClickListener);
        findViewById(R.id.button88).setOnClickListener(mClickListener);
        findViewById(R.id.button89).setOnClickListener(mClickListener);
        findViewById(R.id.button90).setOnClickListener(mClickListener);
        findViewById(R.id.button91).setOnClickListener(mClickListener);
        findViewById(R.id.button92).setOnClickListener(mClickListener);
        findViewById(R.id.button93).setOnClickListener(mClickListener);
        findViewById(R.id.button94).setOnClickListener(mClickListener);
        findViewById(R.id.button95).setOnClickListener(mClickListener);
        findViewById(R.id.button96).setOnClickListener(mClickListener);
        findViewById(R.id.button97).setOnClickListener(mClickListener);
        findViewById(R.id.button98).setOnClickListener(mClickListener);
        findViewById(R.id.button99).setOnClickListener(mClickListener);
        findViewById(R.id.button100).setOnClickListener(mClickListener);
        findViewById(R.id.button101).setOnClickListener(mClickListener);
        findViewById(R.id.button102).setOnClickListener(mClickListener);
        findViewById(R.id.button103).setOnClickListener(mClickListener);
        findViewById(R.id.button104).setOnClickListener(mClickListener);
        findViewById(R.id.button105).setOnClickListener(mClickListener);
        findViewById(R.id.button106).setOnClickListener(mClickListener);
        findViewById(R.id.button107).setOnClickListener(mClickListener);
        findViewById(R.id.button108).setOnClickListener(mClickListener);
        findViewById(R.id.button109).setOnClickListener(mClickListener);
        findViewById(R.id.button110).setOnClickListener(mClickListener);
        findViewById(R.id.button111).setOnClickListener(mClickListener);
        findViewById(R.id.button112).setOnClickListener(mClickListener);
        findViewById(R.id.button113).setOnClickListener(mClickListener);
        findViewById(R.id.button114).setOnClickListener(mClickListener);
        findViewById(R.id.button115).setOnClickListener(mClickListener);
        findViewById(R.id.button116).setOnClickListener(mClickListener);
        findViewById(R.id.button117).setOnClickListener(mClickListener);
        findViewById(R.id.button118).setOnClickListener(mClickListener);
        findViewById(R.id.button119).setOnClickListener(mClickListener);
        findViewById(R.id.button120).setOnClickListener(mClickListener);
        findViewById(R.id.button121).setOnClickListener(mClickListener);
        findViewById(R.id.button122).setOnClickListener(mClickListener);
        findViewById(R.id.button123).setOnClickListener(mClickListener);
        findViewById(R.id.button124).setOnClickListener(mClickListener);
        findViewById(R.id.button125).setOnClickListener(mClickListener);
        findViewById(R.id.button126).setOnClickListener(mClickListener);
        findViewById(R.id.button127).setOnClickListener(mClickListener);
        findViewById(R.id.button128).setOnClickListener(mClickListener);
        findViewById(R.id.button129).setOnClickListener(mClickListener);
        findViewById(R.id.button130).setOnClickListener(mClickListener);
        findViewById(R.id.button131).setOnClickListener(mClickListener);
        findViewById(R.id.button132).setOnClickListener(mClickListener);
        findViewById(R.id.button133).setOnClickListener(mClickListener);
        findViewById(R.id.button134).setOnClickListener(mClickListener);
        findViewById(R.id.button135).setOnClickListener(mClickListener);
        findViewById(R.id.button136).setOnClickListener(mClickListener);
        findViewById(R.id.button137).setOnClickListener(mClickListener);
        findViewById(R.id.button138).setOnClickListener(mClickListener);
        findViewById(R.id.button139).setOnClickListener(mClickListener);
        findViewById(R.id.button140).setOnClickListener(mClickListener);
        findViewById(R.id.button141).setOnClickListener(mClickListener);
        findViewById(R.id.button142).setOnClickListener(mClickListener);
        findViewById(R.id.button143).setOnClickListener(mClickListener);
        findViewById(R.id.button144).setOnClickListener(mClickListener);
        findViewById(R.id.button1).setOnTouchListener(mTouchListener);
        findViewById(R.id.button2).setOnTouchListener(mTouchListener);
        findViewById(R.id.button3).setOnTouchListener(mTouchListener);
        findViewById(R.id.button4).setOnTouchListener(mTouchListener);
        findViewById(R.id.button5).setOnTouchListener(mTouchListener);
        findViewById(R.id.button6).setOnTouchListener(mTouchListener);
        findViewById(R.id.button7).setOnTouchListener(mTouchListener);
        findViewById(R.id.button8).setOnTouchListener(mTouchListener);
        findViewById(R.id.button9).setOnTouchListener(mTouchListener);
        findViewById(R.id.button10).setOnTouchListener(mTouchListener);
        findViewById(R.id.button11).setOnTouchListener(mTouchListener);
        findViewById(R.id.button12).setOnTouchListener(mTouchListener);
        findViewById(R.id.button13).setOnTouchListener(mTouchListener);
        findViewById(R.id.button14).setOnTouchListener(mTouchListener);
        findViewById(R.id.button15).setOnTouchListener(mTouchListener);
        findViewById(R.id.button16).setOnTouchListener(mTouchListener);
        findViewById(R.id.button17).setOnTouchListener(mTouchListener);
        findViewById(R.id.button18).setOnTouchListener(mTouchListener);
        findViewById(R.id.button19).setOnTouchListener(mTouchListener);
        findViewById(R.id.button20).setOnTouchListener(mTouchListener);
        findViewById(R.id.button21).setOnTouchListener(mTouchListener);
        findViewById(R.id.button22).setOnTouchListener(mTouchListener);
        findViewById(R.id.button23).setOnTouchListener(mTouchListener);
        findViewById(R.id.button24).setOnTouchListener(mTouchListener);
        findViewById(R.id.button25).setOnTouchListener(mTouchListener);
        findViewById(R.id.button26).setOnTouchListener(mTouchListener);
        findViewById(R.id.button27).setOnTouchListener(mTouchListener);
        findViewById(R.id.button28).setOnTouchListener(mTouchListener);
        findViewById(R.id.button29).setOnTouchListener(mTouchListener);
        findViewById(R.id.button30).setOnTouchListener(mTouchListener);
        findViewById(R.id.button31).setOnTouchListener(mTouchListener);
        findViewById(R.id.button32).setOnTouchListener(mTouchListener);
        findViewById(R.id.button33).setOnTouchListener(mTouchListener);
        findViewById(R.id.button34).setOnTouchListener(mTouchListener);
        findViewById(R.id.button35).setOnTouchListener(mTouchListener);
        findViewById(R.id.button36).setOnTouchListener(mTouchListener);
        findViewById(R.id.button37).setOnTouchListener(mTouchListener);
        findViewById(R.id.button38).setOnTouchListener(mTouchListener);
        findViewById(R.id.button39).setOnTouchListener(mTouchListener);
        findViewById(R.id.button40).setOnTouchListener(mTouchListener);
        findViewById(R.id.button41).setOnTouchListener(mTouchListener);
        findViewById(R.id.button42).setOnTouchListener(mTouchListener);
        findViewById(R.id.button43).setOnTouchListener(mTouchListener);
        findViewById(R.id.button44).setOnTouchListener(mTouchListener);
        findViewById(R.id.button45).setOnTouchListener(mTouchListener);
        findViewById(R.id.button46).setOnTouchListener(mTouchListener);
        findViewById(R.id.button47).setOnTouchListener(mTouchListener);
        findViewById(R.id.button48).setOnTouchListener(mTouchListener);
        findViewById(R.id.button49).setOnTouchListener(mTouchListener);
        findViewById(R.id.button50).setOnTouchListener(mTouchListener);
        findViewById(R.id.button51).setOnTouchListener(mTouchListener);
        findViewById(R.id.button52).setOnTouchListener(mTouchListener);
        findViewById(R.id.button53).setOnTouchListener(mTouchListener);
        findViewById(R.id.button54).setOnTouchListener(mTouchListener);
        findViewById(R.id.button55).setOnTouchListener(mTouchListener);
        findViewById(R.id.button56).setOnTouchListener(mTouchListener);
        findViewById(R.id.button57).setOnTouchListener(mTouchListener);
        findViewById(R.id.button58).setOnTouchListener(mTouchListener);
        findViewById(R.id.button59).setOnTouchListener(mTouchListener);
        findViewById(R.id.button60).setOnTouchListener(mTouchListener);
        findViewById(R.id.button61).setOnTouchListener(mTouchListener);
        findViewById(R.id.button62).setOnTouchListener(mTouchListener);
        findViewById(R.id.button63).setOnTouchListener(mTouchListener);
        findViewById(R.id.button64).setOnTouchListener(mTouchListener);
        findViewById(R.id.button65).setOnTouchListener(mTouchListener);
        findViewById(R.id.button66).setOnTouchListener(mTouchListener);
        findViewById(R.id.button67).setOnTouchListener(mTouchListener);
        findViewById(R.id.button68).setOnTouchListener(mTouchListener);
        findViewById(R.id.button69).setOnTouchListener(mTouchListener);
        findViewById(R.id.button70).setOnTouchListener(mTouchListener);
        findViewById(R.id.button71).setOnTouchListener(mTouchListener);
        findViewById(R.id.button72).setOnTouchListener(mTouchListener);
        findViewById(R.id.button73).setOnTouchListener(mTouchListener);
        findViewById(R.id.button74).setOnTouchListener(mTouchListener);
        findViewById(R.id.button75).setOnTouchListener(mTouchListener);
        findViewById(R.id.button76).setOnTouchListener(mTouchListener);
        findViewById(R.id.button77).setOnTouchListener(mTouchListener);
        findViewById(R.id.button78).setOnTouchListener(mTouchListener);
        findViewById(R.id.button79).setOnTouchListener(mTouchListener);
        findViewById(R.id.button80).setOnTouchListener(mTouchListener);
        findViewById(R.id.button81).setOnTouchListener(mTouchListener);
        findViewById(R.id.button82).setOnTouchListener(mTouchListener);
        findViewById(R.id.button83).setOnTouchListener(mTouchListener);
        findViewById(R.id.button84).setOnTouchListener(mTouchListener);
        findViewById(R.id.button85).setOnTouchListener(mTouchListener);
        findViewById(R.id.button86).setOnTouchListener(mTouchListener);
        findViewById(R.id.button87).setOnTouchListener(mTouchListener);
        findViewById(R.id.button88).setOnTouchListener(mTouchListener);
        findViewById(R.id.button89).setOnTouchListener(mTouchListener);
        findViewById(R.id.button90).setOnTouchListener(mTouchListener);
        findViewById(R.id.button91).setOnTouchListener(mTouchListener);
        findViewById(R.id.button92).setOnTouchListener(mTouchListener);
        findViewById(R.id.button93).setOnTouchListener(mTouchListener);
        findViewById(R.id.button94).setOnTouchListener(mTouchListener);
        findViewById(R.id.button95).setOnTouchListener(mTouchListener);
        findViewById(R.id.button96).setOnTouchListener(mTouchListener);
        findViewById(R.id.button97).setOnTouchListener(mTouchListener);
        findViewById(R.id.button98).setOnTouchListener(mTouchListener);
        findViewById(R.id.button99).setOnTouchListener(mTouchListener);
        findViewById(R.id.button100).setOnTouchListener(mTouchListener);
        findViewById(R.id.button101).setOnTouchListener(mTouchListener);
        findViewById(R.id.button102).setOnTouchListener(mTouchListener);
        findViewById(R.id.button103).setOnTouchListener(mTouchListener);
        findViewById(R.id.button104).setOnTouchListener(mTouchListener);
        findViewById(R.id.button105).setOnTouchListener(mTouchListener);
        findViewById(R.id.button106).setOnTouchListener(mTouchListener);
        findViewById(R.id.button107).setOnTouchListener(mTouchListener);
        findViewById(R.id.button108).setOnTouchListener(mTouchListener);
        findViewById(R.id.button109).setOnTouchListener(mTouchListener);
        findViewById(R.id.button110).setOnTouchListener(mTouchListener);
        findViewById(R.id.button111).setOnTouchListener(mTouchListener);
        findViewById(R.id.button112).setOnTouchListener(mTouchListener);
        findViewById(R.id.button113).setOnTouchListener(mTouchListener);
        findViewById(R.id.button114).setOnTouchListener(mTouchListener);
        findViewById(R.id.button115).setOnTouchListener(mTouchListener);
        findViewById(R.id.button116).setOnTouchListener(mTouchListener);
        findViewById(R.id.button117).setOnTouchListener(mTouchListener);
        findViewById(R.id.button118).setOnTouchListener(mTouchListener);
        findViewById(R.id.button119).setOnTouchListener(mTouchListener);
        findViewById(R.id.button120).setOnTouchListener(mTouchListener);
        findViewById(R.id.button121).setOnTouchListener(mTouchListener);
        findViewById(R.id.button122).setOnTouchListener(mTouchListener);
        findViewById(R.id.button123).setOnTouchListener(mTouchListener);
        findViewById(R.id.button124).setOnTouchListener(mTouchListener);
        findViewById(R.id.button125).setOnTouchListener(mTouchListener);
        findViewById(R.id.button126).setOnTouchListener(mTouchListener);
        findViewById(R.id.button127).setOnTouchListener(mTouchListener);
        findViewById(R.id.button128).setOnTouchListener(mTouchListener);
        findViewById(R.id.button129).setOnTouchListener(mTouchListener);
        findViewById(R.id.button130).setOnTouchListener(mTouchListener);
        findViewById(R.id.button131).setOnTouchListener(mTouchListener);
        findViewById(R.id.button132).setOnTouchListener(mTouchListener);
        findViewById(R.id.button133).setOnTouchListener(mTouchListener);
        findViewById(R.id.button134).setOnTouchListener(mTouchListener);
        findViewById(R.id.button135).setOnTouchListener(mTouchListener);
        findViewById(R.id.button136).setOnTouchListener(mTouchListener);
        findViewById(R.id.button137).setOnTouchListener(mTouchListener);
        findViewById(R.id.button138).setOnTouchListener(mTouchListener);
        findViewById(R.id.button139).setOnTouchListener(mTouchListener);
        findViewById(R.id.button140).setOnTouchListener(mTouchListener);
        findViewById(R.id.button141).setOnTouchListener(mTouchListener);
        findViewById(R.id.button142).setOnTouchListener(mTouchListener);
        findViewById(R.id.button143).setOnTouchListener(mTouchListener);
        findViewById(R.id.button144).setOnTouchListener(mTouchListener);
        findViewById(R.id.fullscreen_content).setOnTouchListener(mTouchListener);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        // Trigger the initial hide() shortly after the activity has been
        // created, to briefly hint to the user that UI controls
        // are available.
        //delayedHide(100);
    }

    private void toggle() {
        if (mVisible) {
            hide();
        } else {
            show();
        }
    }

    private void hide() {
        mVisible = false;

        // Schedule a runnable to remove the status and navigation bar after a delay
        mHideHandler.postDelayed(mHidePart2Runnable, UI_ANIMATION_DELAY);
    }

    @SuppressLint("InlinedApi")
    private void show() {
        // Show the system bar
        mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
        mVisible = true;

        // Schedule a runnable to display UI elements after a delay
        mHideHandler.removeCallbacks(mHidePart2Runnable);
    }


    public void registerListeners() {
        mSensorManager.registerListener(mSensorListener, mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_GAME);
        mSensorManager.registerListener(mSensorListener, mSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE), SensorManager.SENSOR_DELAY_GAME);
        mSensorManager.registerListener(mSensorListener, mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD), SensorManager.SENSOR_DELAY_GAME);
        recording = true;
    }

    public void unregisterListner() {
        mSensorManager.unregisterListener(mSensorListener);
        recording = false;
    }


}
