import java.awt.*;

/**
 * Represents a car of type Saab95
 *@author Cecilia Nyberg, Maximilian Forsell, Lucas Edeslätt
*/
public class Saab95 extends Car{

    /**
     * The status of the turbo for this Saab95
     */
    private boolean turboOn;

    /**
     * Creates a Saab with specific color, engine power, model name and number of doors
     */
    public Saab95(){
        super(Color.red, 125, "Saab95", 2);
	    turboOn = false;
    }
    /**
     * Sets turbo on
     */
    public void setTurboOn() {
        turboOn = true;
    }
    /**
     * Sets turbo off
     */
    public void setTurboOff(){
	    turboOn = false;
    }
    /**
     * Gets turbo status
     * @return turbo status true if turbo is on and false if turbo is off
     */
    public boolean getTurboStatus(){
        return turboOn;
    }

    /**
     * Calculates speed factor
     * @return speed factor
     */
    @Override
    public double speedFactor(){
        double turbo = 1;
        if(turboOn) turbo = 1.3;
        return enginePower * 0.01 * turbo;
    }

}
