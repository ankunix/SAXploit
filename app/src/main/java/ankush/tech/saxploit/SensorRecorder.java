//package ankush.tech.saxploit;
//
//import java.io.IOException;
//
///**
// * Created by ankush on 10/14/17.
// */
//
//public class SensorRecorder {
//    private long timestamp;
//    private float[] lastGyroscopeValues = {0, 0, 0};
//    private float[] lastAccelerometerValues = {0, 0, 0};
//    private float[] lastMagnetometerValues = {0, 0, 0};
//
//
//    public void snapshot() {
//        try {
//            writer.append(timestamp +  ", " + X + ", " + Y + ", " + eventMask + ", " + lastAccelerometerValues[0] + ", " + lastAccelerometerValues[1] + ", " + lastAccelerometerValues[2] + ", " + lastGyroscopeValues[0] + ", " + lastGyroscopeValues[1] + ", " + lastGyroscopeValues[2] + ", " + lastMagnetometerValues[0] + ", " + lastMagnetometerValues[1] + ", " + lastMagnetometerValues[2] + ", " + label + "\n");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//}
