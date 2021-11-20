package workTest;

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

public class DataMap {

    private Map<Float, Integer> dataMap;
    private final int MIN = 0;
    private final float SLIDER_MAX = 100f;
    private final int ARB_STEPS = 10;
    private int maxValue = 0;

    DataMap(int stepsCount) {
        dataMap = new TreeMap<>();
        maxValue = ARB_STEPS * stepsCount;
        float step = (float) SLIDER_MAX / stepsCount;
        int value = 0;
        // Creating the map with key Starting from 0.0 to 100.0
        for (float key = (float) MIN; key <= SLIDER_MAX; key += step, value += ARB_STEPS) {
            dataMap.put(key, value);
        }
        dataMap = Collections.unmodifiableMap(dataMap);
    }

    /* Finding the value given the key from the map*/
    public int getValue(float key) {
        int result;
        //Key is greater than 100.0 return the max value;
        if (key > SLIDER_MAX) {
            result = maxValue;
        } else if (key < MIN) {
            result = MIN;
        } else if (dataMap.containsKey(key)) {
            result = dataMap.get(key);
        } else {
            /*Key doesn't exists in the Map
             * Finding the closest KEY from the given key in the map */
            float closestDiff = Float.MAX_VALUE;
            float closestKey = 0f;
            for (Map.Entry<Float, Integer> entry : dataMap.entrySet()) {
                Float k = entry.getKey();
                float diff = k - key > 0 ? k - key : key - k;
                if (diff < closestDiff) {
                    closestDiff = diff;
                    closestKey = k;
                }
            }
            result = dataMap.get(closestKey);
        }

        return result;
    }

    public float getKey(int value) {
        float result = 0f;
        if (value > maxValue) {
            result = SLIDER_MAX;
        } else if (value < MIN) {
            result = (float) MIN;
        } else {
            if (dataMap.containsValue(value)) {
                for (Map.Entry<Float, Integer> entry : dataMap.entrySet()) {
                    float k = entry.getKey();
                    Integer v = entry.getValue();
                    if (value == v) result = k;
                }
            } else {
                float closestDiff = Float.MAX_VALUE;
                for (Map.Entry<Float, Integer> entry : dataMap.entrySet()) {
                    float k = entry.getKey();
                    int v = entry.getValue();
                    float diff = v - value > 0 ? v - value : value - v;
                    if (diff < closestDiff) {
                        closestDiff = diff;
                        result = k;
                    }
                }
            }
        }
        return result;
    }

    public void printMap() {
        for (Map.Entry<Float, Integer> entry : dataMap.entrySet()) {
            System.out.println(entry.getKey() + " => " + entry.getValue());
        }
    }

    public int maxValue() {
        return maxValue;
    }
}
