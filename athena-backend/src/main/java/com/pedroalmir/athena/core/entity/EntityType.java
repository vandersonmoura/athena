package com.pedroalmir.athena.core.entity;

/**
 * The defined types for all properties within {@linkplain Entity} objects.
 */
public enum EntityType {
    CANDIDATE_SOLUTION,
    PREVIOUS_SOLUTION,
    FITNESS,
    PREVIOUS_FITNESS,
    STRATEGY_PARAMETERS;

    /**
     * {@linkplain Particle} specific constants.
     */
    public enum Particle {
        BEST_POSITION,
        BEST_FITNESS,
        VELOCITY;

        public enum Count {
            PBEST_STAGNATION_COUNTER;
        }
    }

}
