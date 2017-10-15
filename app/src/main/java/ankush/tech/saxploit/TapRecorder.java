package ankush.tech.saxploit;

import android.os.Environment;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ankush on 10/14/17.
 */

public class TapRecorder {
    private File file;
    private FileOutputStream fOut;
    private OutputStreamWriter writer;
    private String label = "stop";
    private float X;
    private float Y;
    private int eventMask=-1;
    private int action;
    private float pressure;
    private float orientation;
    private long eventTime;

   // private SensorHub sense;
    public void init() {

        String filename = new SimpleDateFormat("yyyyMMddHHmm'_recording.csv'").format(new Date());
        try {
            file = new File(Environment.getExternalStorageDirectory(), "recordings/" + filename);
            fOut = new FileOutputStream(file);
            writer = new OutputStreamWriter(fOut);
            writer.append("timestamp, X, Y, eventMask, lastAccelerometerValues[0], lastAccelerometerValues[1], lastAccelerometerValues[2], lastGyroscopeValues[0], lastGyroscopeValues[1], lastGyroscopeValues[2], lastMagnetometerValues[0], lastMagnetometerValues[1], lastMagnetometerValues[2], label\n");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public final View.OnTouchListener mTouchListener = new View.OnTouchListener() {

        public boolean onTouch(View view, MotionEvent motionEvent) {
            X=motionEvent.getX();
            Y=motionEvent.getY();
            eventMask=motionEvent.getActionMasked();
            eventTime=motionEvent.getEventTime();
            action=motionEvent.getAction();
            pressure=motionEvent.getPressure();
            orientation=motionEvent.getOrientation();
            Log.d("X: ", motionEvent.toString());
            Log.d("Y: ", Float.toString(Y));
            Log.d("eventMask: ", Float.toString(eventMask));
            Log.d("action: ", Float.toString(action));
            Log.d("pressure: ", Float.toString(pressure));
            Log.d("orientation: ",Float.toString(orientation));
            Log.d("eventTime: ",Long.toString(eventTime));
            return false;
        }
    };
    }
