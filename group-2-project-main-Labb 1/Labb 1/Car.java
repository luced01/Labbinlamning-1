
import java.awt.*;
/**
 * Represents a car.
 * A car can be of different types
 * @author Cecilia Nyberg, Maximilian Forsell, Lucas Edeslätt
 */
public abstract class Car implements Movable{

    /**
     * Engine power of this car (as long as the engine stays the same?)
     */
    protected double enginePower;

    /**
     * Current speed of this car
     */
    protected double currentSpeed;

    /**
     * Color of this car
     */
    protected Color color;

    /**
     * Model name of this car, this can't possibly change
     */
    protected final String modelName;

    /**
     * Number of doors on this car, as long as the car isn't heavily modified
     */
    protected final int nrDoors;

    /**
     * Directions for these cars
     */
    protected enum Directions {
        NORTH, EAST, SOUTH, WEST
    }

    /**
     * The direction this car is facing
     */
    Directions facingDirection;

    /**
     * The current x- and y-coordinates for this car
     */
    protected Point coordinates = new Point(0,0);

    /**
     * Class constructor, sets color, engine power, model name and number och doors
     * checks if engine power is positive, else initiates it to zero
     * @param color the color of this car
     * @param enginePower the engine power of this car
     * @param modelName the model name of this car
     * @param nrDoors the number of doors on this car
     */
    public Car(Color color, int enginePower, String modelName, int nrDoors){
        try {
            testOutsideInterval(enginePower,0,Double.POSITIVE_INFINITY);
            this.enginePower = enginePower;
        } catch (RuntimeException e){
            this.enginePower= 0;
            System.out.println(e.getMessage() + " EnginePower has been initiated to zero");
        }
        this.color = color;
        this.modelName = modelName;
        this.nrDoors = nrDoors;
        this.facingDirection = Directions.NORTH;
        stopEngine();
    }

    /**
     * Gets number of doors on this car
     * @return numbers of doors
     */
    public int getNrDoors(){
        return nrDoors;
    }

    /**
     * Gets engine power of this car
     * @return engine power
     */
    public double getEnginePower(){
        return enginePower;
    }

    /**
     * Gets current speed of this car
     * @return current speed
     */
    public double getCurrentSpeed(){
        return currentSpeed;
    }

    /**
     * Gets color of this car
     * @return color of this car
     */
    public Color getColor(){
        return color;
    }

    /**
     * Sets color of this car
     * @param clr the color to be set
     */
    public void setColor(Color clr){
        color = clr;
    }

    /**
     * Starts engine, initiates current speed to 0.1
     */
    public void startEngine(){
        currentSpeed = 0.1;
    }

    /**
     *Stops engine, sets current speed to 0
     */
    public void stopEngine(){
        currentSpeed = 0;
    }

    /**
     * Calculates the speed factor for this car
     * @returns speed factor
     */
    public abstract double speedFactor();

    /**
     * Increases current speed, no higher than enginePower
     * @param amount the amount to increase with
     */
    public void incrementSpeed(double amount){
        currentSpeed = Math.min(getCurrentSpeed() + speedFactor() * amount,enginePower);
    }

    /**
     * Decreases current speed, no lower than 0
     * @param amount the amount to decrease with
     */
    public void decrementSpeed(double amount){
        currentSpeed = Math.max(getCurrentSpeed() - speedFactor() * amount,0);
    }

    /**
     * Moves this car straight forward in it's facing direction
     */
    public void move(){
        switch (facingDirection){
            case NORTH -> coordinates.y += currentSpeed;
            case WEST -> coordinates.x += currentSpeed;
            case SOUTH -> coordinates.y -= currentSpeed;
            case EAST -> coordinates.x -= currentSpeed;
        }
    }

    /**
     * Turns this car 90 degrees left
     */
    public void turnLeft(){
        switch (facingDirection){
            case NORTH -> this.facingDirection=Directions.WEST;
            case WEST -> this.facingDirection = Directions.SOUTH;
            case SOUTH -> this.facingDirection = Directions.EAST;
            case EAST -> this.facingDirection = Directions.NORTH;
        }
    }

    /**
     * Turns this car 90 degrees right
     */
    public void turnRight() {
        switch (facingDirection){
            case NORTH -> facingDirection = Directions.EAST;
            case EAST -> facingDirection = Directions.SOUTH;
            case SOUTH -> facingDirection = Directions.WEST;
            case WEST -> facingDirection = Directions.NORTH;
        }
    }

    /**
     * Calls incrementSpeed, catches RuntimeException if input exception occurred
     * @param amount the amount to increase speed with
     */
    public void gas(double amount){
        try {
            testOutsideInterval(amount, 0, 1);
            incrementSpeed(amount);
        } catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Calls decrementSpeed, catches RuntimeException if value is greater than 0 and less than 1
     * @param amount the amount decrease speed with
     */
    public void brake(double amount){
        try {
            testOutsideInterval(amount,0,1);
            decrementSpeed(amount);
        } catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Tests whether an amount is in the range of an accepted interval
     * @param amount the number to be tested
     * @param lowerLimit the lower limit to be compared to
     * @param upperLimit the upper limit to be compared to
     * @throws RuntimeException if an input exception occurred
     */
    protected static void testOutsideInterval(double amount,double lowerLimit, double upperLimit){
        if (lowerLimit > amount || amount > upperLimit){
            throw new RuntimeException(amount + " is outside the accepted interval.");
        }

    }

}
