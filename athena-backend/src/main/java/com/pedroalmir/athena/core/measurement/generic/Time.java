package com.pedroalmir.athena.core.measurement.generic;

import com.pedroalmir.athena.core.algorithm.Algorithm;
import com.pedroalmir.athena.core.algorithm.AlgorithmEvent;
import com.pedroalmir.athena.core.algorithm.AlgorithmListener;
import com.pedroalmir.athena.core.measurement.Measurement;
import com.pedroalmir.athena.core.type.numeric.Int;

/**
 */
public class Time implements Measurement<Int>, AlgorithmListener {

    private boolean running = false;
    private long startTime;
    private long endTime;

    /**
     * Create a default instance of {@linkplain Time}.
     */
    public Time() {
        running = false;
        startTime = System.currentTimeMillis();
        endTime = startTime;
    }

    /**
     * Copy constructor. Create a copy of the given instance.
     * @param copy The instance to copy.
     */
    public Time(Time copy) {
        running = copy.running;
        startTime = copy.startTime;
        endTime = copy.endTime;
    }

    /**
     * {@inheritDoc}
     */
    public Time getClone() {
        return new Time(this);
    }

    /**
     * {@inheritDoc}
     */
    public Int getValue(Algorithm algorithm) {
        if (running) {
            return Int.valueOf(Long.valueOf(System.currentTimeMillis() - startTime).intValue());
        } else {
            return Int.valueOf(Long.valueOf(endTime - startTime).intValue());
        }
    }

    /**
     * {@inheritDoc}
     */
    public void algorithmStarted(AlgorithmEvent e) {
        running = true;
        startTime = System.currentTimeMillis();
    }

    /**
     * {@inheritDoc}
     */
    public void algorithmFinished(AlgorithmEvent e) {
        endTime = System.currentTimeMillis();
        running = false;
    }

    /**
     * {@inheritDoc}
     */
    public void iterationCompleted(AlgorithmEvent e) {
    }
}
