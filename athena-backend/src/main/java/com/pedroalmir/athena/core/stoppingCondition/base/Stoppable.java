/**           __  __
 *    _____ _/ /_/ /_    Computational Intelligence Library (CIlib)
 *   / ___/ / / / __ \   (c) CIRG @ UP
 *  / /__/ / / / /_/ /   http://cilib.net
 *  \___/_/_/_/_.___/
 */
package com.pedroalmir.athena.core.stoppingCondition.base;

import com.pedroalmir.athena.core.algorithm.Algorithm;

/**
 *
 */
public interface Stoppable {

    /**
     * Add a stopping condition
     * @param condition The stopping condition to add.
     */
    void addStoppingCondition(StoppingCondition<Algorithm> condition);

    /**
     * Remove the specified stopping condition.
     * @param condition The stopping condition to remove.
     */
    void removeStoppingCondition(StoppingCondition<Algorithm> condition);

}
