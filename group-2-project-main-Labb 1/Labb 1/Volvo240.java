import java.awt.*;
/**
 * Represents a car of type Volvo240
 * @author Cecilia Nyberg, Maximilian Forsell, Lucas Edeslätt
 */
public class Volvo240 extends Car{


    /**
     * The trim factor of the Volvo
     */
    private final static double trimFactor = 1.25;

    /**
     * Creates a Volvo with specific color, engine power, model name and number of doors
     */
    public Volvo240(){
        super(Color.black, 100, "Volvo240",4);
    }

    /**
     * Calculates speed factor for this Volvo
     * @return speed factor
     */
    @Override
    public double speedFactor(){
        return this.getEnginePower() * 0.01 * trimFactor;
    }
}
