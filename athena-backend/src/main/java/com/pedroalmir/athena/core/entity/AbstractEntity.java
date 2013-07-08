package com.pedroalmir.athena.core.entity;

import com.pedroalmir.athena.core.algorithm.Algorithm;
import com.pedroalmir.athena.core.fitness.EntityBasedFitnessCalculator;
import com.pedroalmir.athena.core.fitness.FitnessCalculator;
import com.pedroalmir.athena.core.problem.Problem;
import com.pedroalmir.athena.core.solution.fitness.Fitness;
import com.pedroalmir.athena.core.type.Blackboard;
import com.pedroalmir.athena.core.type.base.Type;
import com.pedroalmir.athena.core.type.container.StructuredType;

/**
 * Abstract class definition for all concrete {@linkplain Entity} objects.
 * This class defines the {@linkplain Entity} main data structure for the
 * values stored within the {@linkplain Entity} itself.
 */
public abstract class AbstractEntity implements Entity {


    private long id;
    private final Blackboard<Enum<?>, Type> properties;
    private FitnessCalculator<Entity> fitnessCalculator;

    /**
     * Initialize the candidate solution of the {@linkplain Entity}.
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
	protected AbstractEntity() {
        this.id = EntityIdFactory.getNextId();
        this.properties = new Blackboard();
        this.fitnessCalculator = new EntityBasedFitnessCalculator();
    }

    /**
     * Copy constructor. Instantiate and copy the given instance.
     * @param copy The instance to copy.
     */
    protected AbstractEntity(AbstractEntity copy) {
        this.id = EntityIdFactory.getNextId();
        this.properties = copy.properties.getClone();
        this.fitnessCalculator = copy.fitnessCalculator.getClone();
    }

    /**
     * {@inheritDoc}
     *
     * It doesn't make sense to compare the meta data of the entity.
     * In other words, the properties of the entity may vary, but the entity
     * is still the same entity.
     *
     * @param object The object to compare equality.
     */
    
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if ((object == null) || (this.getClass() != object.getClass())) {
            return false;
        }

        AbstractEntity other = (AbstractEntity) object;
        return this.id == other.id;
    }

    /**
     * {@inheritDoc}
     */
    
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + (int)(id ^ (id >>> 32));
        return hash;
    }

    /**
     * Get the properties associate with the <code>Entity</code>.
     * @return The properties within a {@linkplain Blackboard}.
     */
    
    public final Blackboard<Enum<?>, Type> getProperties() {
        return properties;
    }

    /**
     * Get the value of the candidate solution maintained by this
     * {@linkplain Entity}.
     * @return The candidate solution as a {@linkplain Type}.
     */
    
    @SuppressWarnings("rawtypes")
	public StructuredType getCandidateSolution() {
        return (StructuredType) properties.get(EntityType.CANDIDATE_SOLUTION);
    }

    /**
     * Get the fitness of the candidate solution maintained by this
     * {@linkplain Entity}.
     * @return The {@linkplain Fitness} of the candidate solution.
     */
    
    public Fitness getFitness() {
        return (Fitness) properties.get(EntityType.FITNESS);
    }

    /**
     * Set the {@linkplain Type} maintained by this {@linkplain Entity}s
     * candidate solution
     * @param candidateSolution The {@linkplain Type} that will be the new value of the
     *        {@linkplain Entity} candidate solution.
     */
    
    @SuppressWarnings("rawtypes")
	public void setCandidateSolution(StructuredType candidateSolution) {
        properties.put(EntityType.CANDIDATE_SOLUTION, candidateSolution);
    }

    /**
     * {@inheritDoc}
     */
    
    public Fitness getBestFitness() {
        return getFitness();
    }

    /**
     * Get the current {@code FitnessCalculator} for the current {@code Entity}.
     * @return The {@code FitnessCalculator} associated with this {@code Entity}.
     */
    
    public FitnessCalculator<Entity> getFitnessCalculator() {
        return fitnessCalculator;
    }

    /**
     * Set the {@code FitnessCalculator} for the current {@code Entity}.
     * @param fitnessCalculator The value to set.
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public void setFitnessCalculator(FitnessCalculator fitnessCalculator) {
        this.fitnessCalculator = fitnessCalculator;
    }

    /**
     * {@inheritDoc}
     */
    
    public long getId() {
        return this.id;
    }

    
    public abstract AbstractEntity getClone();

    
    public void calculateFitness(Algorithm algorithm) {
        properties.put(EntityType.PREVIOUS_FITNESS, getFitness().getClone());
        properties.put(EntityType.FITNESS, fitnessCalculator.getFitness(algorithm, this));
    }

    
    public abstract void initialise(Problem problem);

    
    public int getDimension() {
        return getCandidateSolution().size();
    }

    
    public abstract void reinitialise();

    
    public int compareTo(Entity o) {
        return getFitness().compareTo(o.getFitness());
    }

}
