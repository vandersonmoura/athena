package com.pedroalmir.athena.core.algorithm;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Lists;
import com.pedroalmir.athena.core.configuration.ConfigurableAlgorithm;
import com.pedroalmir.athena.core.problem.Problem;
import com.pedroalmir.athena.core.put.Input;
import com.pedroalmir.athena.core.put.Output;
import com.pedroalmir.athena.core.put.Setting;
import com.pedroalmir.athena.core.report.ExecutionLog;
import com.pedroalmir.athena.core.solution.Solution;
import com.pedroalmir.athena.core.stoppingCondition.base.Stoppable;
import com.pedroalmir.athena.core.stoppingCondition.base.StoppingCondition;

/**
 * All algorithms in Athena should be subclasses of {@link Algorithm}. This class
 * handles stopping criteria, events, threading and measurements.
 * <p>
 * Subclasses of {@link Algorithm} must provide an implementation for
 * {@link Algorithm#performIteration()}. If a subclass overrides
 * {@link #algorithmInitialisation()} then it must call {@code super.initialise()}.
 */
public abstract class AbstractAlgorithm implements Algorithm, Stoppable, ConfigurableAlgorithm {

    /**
     * Stopping Conditions
     * List of stopping conditions to apply in this algorithm.
     */
    private final List<StoppingCondition<Algorithm>> stoppingConditions;
    /**
     * Algorithm listeners
     * List of listeners.
     */
    private final List<AlgorithmListener> algorithmListeners;
    /**
     * This field represents the union of stopping conditions.
     * 
     * For example:
     * Maximum of iteration OR Minimum value of error
     */
    private Predicate<Algorithm> stoppingCondition = Predicates.alwaysFalse();
    /**
     * Iteration counter
     */
    private int iteration;
    /**
     * This field is <code>true</code> if algorithm is running.
     * 
     * Obs.: Volatile modifier ensures that the attribute will always be read and written 
     * directly from main memory, avoiding that it has different values ​​in two threads. 
     * 
     * Furthermore, it is guaranteed that the order of operations on these attributes will 
     * not be changed. And it only applies to instance variables.
     */
    private volatile boolean running;
    /**
     * This field is <code>true</code> if algorithm is was initialized.
     */
    private boolean initialised;
    /**
     * Problem definition
     */
    protected Problem optimizationProblem;
    /**
     * This field represents the list of inputs.
     * It must be initialized before the execution of the algorithm. 
     */
    protected List<Input> inputs;
    /**
     * This field represents the list of outputs.
     * 
     * It must be initialized before the execution of the algorithm and
     * it'll be populated after the end of algorithm execution.
     */
    protected List<Output> outputs;
    /**
     * This field represents the list of settings that will be
     * used in this algorithm execution.
     * 
     * It'll be populated in the algorithm initialization.
     */
    protected List<Setting> settings;
    /**
     * This field represents the list of solutions.
     * It'll be populated after the end of algorithm execution.
     */
    protected List<Solution> solutions;
    /**
     * Execution Log
     */
    protected ExecutionLog executionLog;

    /**
     * Default constructor for {@linkplain Algorithm} classes. Sets up the correct state
     * for the instance and initializes the needed containers needed for the different
     * {@linkplain AlgorithmEvent}s that are generated.
     * 
     * @param inputs
     * 			List of inputs
     * @param outputs
     * 			List of outputs
     */
    protected AbstractAlgorithm(List<Input> inputs, List<Output> outputs, List<Setting> settings) {
        stoppingConditions = new ArrayList<StoppingCondition<Algorithm>>();
        algorithmListeners = new ArrayList<AlgorithmListener>();
        
        /* puts */
        this.inputs = inputs;
        this.outputs = outputs;
        
        /* settings */
        this.settings = settings;
        
        /* solutions */
        this.solutions = new LinkedList<Solution>();
        		
        running = false;
        initialised = false;
        executionLog = new ExecutionLog();
    }
    
    public abstract AbstractAlgorithm getClone();

    /**
     * Copy constructor. Create a deep copy of the provided instance and return it.
     * @param copy The instance to copy.
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	protected AbstractAlgorithm(AbstractAlgorithm copy) {
        stoppingConditions = Lists.newArrayList();
        algorithmListeners = Lists.newArrayList();
        
        /* puts */
        this.inputs = Lists.newArrayList();
        this.outputs = Lists.newArrayList();
        
        /* settings */
        this.settings = copy.settings;
        
        /* solutions */
        this.solutions = Lists.newArrayList();

        for (AlgorithmListener listen : copy.algorithmListeners) {
            algorithmListeners.add(listen.getClone());
        }

        if (copy.optimizationProblem != null) {
            optimizationProblem = copy.optimizationProblem;
        }

        for (StoppingCondition sc : copy.stoppingConditions) {
            addStoppingCondition(sc);
        }

        running = false;
        initialised = false;
        iteration = copy.iteration;
    }

    /**
     * Initializes the algorithm. Must be called before {@link #run()} is called.
     */
    public final void performInitialisation() {
        iteration = 0;
        running = true;
        initialised = true;
        executionLog = new ExecutionLog();

        algorithmInitialisation();
    }

    /**
     * {@inheritDoc}
     */
    public final void performIteration() {
        algorithmIteration();
        iteration++;
    }

    /**
     * The actual operations that the current {@linkplain Algorithm} performs within a single
     * iteration.
     */
    protected abstract void algorithmIteration();

    /**
     * Initialize the algorithm.
     * Subclasses can override the behavior for this method.
     */
    public abstract void algorithmInitialisation();

    /**
     * Executes the algorithm without cleaning up afterwards.
     * Useful for running algorithms within algorithms.
     */
    public void runAlgorithm() {
        Preconditions.checkState(!stoppingConditions.isEmpty(), "No stopping conditions specified");
        Preconditions.checkState(initialised, "Algorithm not initialised");

        while (running && (!isFinished())) {
            performIteration();
            fireIterationCompleted();
        }
    }

    /**
     * Executes the algorithm.
     * @exception InitialisationException algorithm was not properly initialized.
     */
    public void run() {
    	long begin = System.currentTimeMillis();
        if (!initialised) {
            performInitialisation();
        }

        fireAlgorithmStarted();
        runAlgorithm();
        fireAlgorithmFinished();
        
        this.executionLog.setIterations(this.iteration);
        this.executionLog.setExecutionTime(System.currentTimeMillis() - begin);
        cleanUp();
    }

    public void cleanUp() {
    	/* Subclasses can override the behavior for this method */
    }

    /**
     * Adds a stopping condition.
     * @param stoppingCondition A {@link net.sourceforge.cilib.stoppingcondition.StoppingCondition}
     *        to be added.
     */
    public final void addStoppingCondition(StoppingCondition<Algorithm> stoppingCondition) {
        stoppingConditions.add(stoppingCondition);
        this.stoppingCondition = Predicates.or(this.stoppingCondition, stoppingCondition);
    }

    /**
     * Removes a stopping condition.
     * @param stoppingCondition The {@link net.sourceforge.cilib.stoppingcondition.StoppingCondition}
     *        to be removed.
     */
    public final void removeStoppingCondition(StoppingCondition<Algorithm> stoppingCondition) {
        stoppingConditions.remove(stoppingCondition);

        this.stoppingCondition = Predicates.alwaysFalse();
        for (StoppingCondition<Algorithm> condition : stoppingConditions) {
            this.stoppingCondition = Predicates.or(this.stoppingCondition, condition);
        }
    }

    /**
     * Adds an algorithm event listener. Event listeners are notified at various stages during the
     * execution of an algorithm.
     * @param listener An {@link AlgorithmListener} to be added.
     */
    public final void addAlgorithmListener(AlgorithmListener listener) {
        algorithmListeners.add(listener);
    }

    /**
     * Removes an algorithm event listener.
     * @param listener The {@link AlgorithmListener} to be removed.
     */
    public final void removeAlgorithmListener(AlgorithmListener listener) {
        algorithmListeners.remove(listener);
    }

    /**
     * {@inheritDoc}
     */
    public final int getIterations() {
        return iteration;
    }

    /**
     * Returns the percentage the algorithm is from completed (as a fraction). The percentage
     * complete is calculated based on the stopping condition that is closest to finished.
     * @return The percentage complete as a fraction.
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public final double getPercentageComplete() {
        double percentageComplete = 0;
        for (StoppingCondition condition : stoppingConditions) {
            double percentage = condition.getPercentageCompleted(this);
            if (percentage > percentageComplete) {
                percentageComplete = percentage;
            }
        }
        return percentageComplete;
    }

    /**
     * Returns true if the algorithm has finished executing.
     * @return true if the algorithm is finished
     */
    public final boolean isFinished() {
        return stoppingCondition.apply(this);
    }

    /**
     * Terminates the algorithm.
     */
    public final void terminate() {
        running = false;
    }

    /**
     * Get the current list of {@linkplain StoppingCondition} instances that are
     * associated with the current {@linkplain Algorithm}.
     * @return The list of {@linkplain StoppingCondition} instances associated with
     *         the current {@linkplain Algorithm}.
     */
    public List<StoppingCondition<Algorithm>> getStoppingConditions() {
        return this.stoppingConditions;
    }

    /**
     * Fire the {@linkplain AlgorithmEvent} to indicate that the {@linkplain Algorithm}
     * has started execution.
     */
    private void fireAlgorithmStarted() {
        for (AlgorithmListener listener : algorithmListeners) {
            listener.algorithmStarted(new AlgorithmEvent(this));
        }
    }

    /**
     * Fire the {@linkplain AlgorithmEvent} to indicate that the {@linkplain Algorithm}
     * has finished execution.
     */
    private void fireAlgorithmFinished() {
        for (AlgorithmListener listener : algorithmListeners) {
            listener.algorithmFinished(new AlgorithmEvent(this));
        }
    }

    /**
     * Fire the {@linkplain AlgorithmEvent} to indicate that the {@linkplain Algorithm}
     * has completed an iteration.
     */
    private void fireIterationCompleted() {
        for (AlgorithmListener listener : algorithmListeners) {
            listener.iterationCompleted(new AlgorithmEvent(this));
        }
    }

    /**
     * {@inheritDoc}
     */
    public void setOptimizationProblem(Problem problem) {
        this.optimizationProblem = problem;
    }

    /**
     * {@inheritDoc}
     */
    public Problem getOptimizationProblem() {
        return this.optimizationProblem;
    }

    /**
     * {@inheritDoc}
     */
    public abstract Solution getBestSolution();

    /**
     * {@inheritDoc}
     */
    public abstract <T extends Solution> Iterable<T> getSolutions();

	/**
	 * @return the executionLog
	 */
	public ExecutionLog getExecutionLog() {
		return executionLog;
	}

	/**
	 * @param executionLog the executionLog to set
	 */
	public void setExecutionLog(ExecutionLog executionLog) {
		this.executionLog = executionLog;
	}

}
