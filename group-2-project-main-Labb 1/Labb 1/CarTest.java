import org.junit.*;

import javax.management.ConstructorParameters;

import static org.junit.Assert.* ;
import java.awt.*;

public class CarTest {
    Car p = new Saab95();
    Saab95 s = new Saab95();
    @Test
    public void testNrDoors() {
        Car first = new Saab95();
        assertTrue(s.getNrDoors()==2);
    }
    @Test
    public void testColor() {
        Saab95 p = new Saab95();
        p.setColor(Color.red);
        assertTrue(p.getColor() == Color.red) ;
    }
    @Test
    public void testTurnLeft(){
        Volvo240 v = new Volvo240();
        v.turnLeft();
        assertTrue(v.facingDirection == Car.Directions.WEST) ;
    }
    @Test
    public void testTurnRight(){
        Volvo240 v = new Volvo240();
        v.turnRight();
        assertTrue(v.facingDirection == Car.Directions.EAST) ;
    }
    @Test
    public void testOutsideInterval(){
        Saab95 p = new Saab95();
        double amount = 4.0;
        boolean temp = false;
        try {
            Car.testOutsideInterval(amount, 2.0, 3.0);
        }
        catch (RuntimeException e){
            temp = true;
            assertEquals(amount + " is outside the accepted interval.", e.getMessage());
        }


    }
    @Test
    public void testOutsideIntervalWhenInside(){
        Saab95 p = new Saab95();
        boolean temp = true;
        try {
            Car.testOutsideInterval(1, 0, 2);
        }catch(RuntimeException e){
            temp = false;
        }
        assertTrue(temp);


    }
    @Test
    public void testGas(){
        double temp = p.getCurrentSpeed();
        System.out.println(temp);
        p.gas(0.5);
        System.out.println(p.getCurrentSpeed());
        assertTrue(p.currentSpeed>temp||p.enginePower==p.currentSpeed);
    }
    @Test
    public void testBrake(){
        Saab95 s = new Saab95();
        double temp = s.getCurrentSpeed();
        s.brake(0.5);
        assertTrue(temp == 0 && s.getCurrentSpeed() == 0 || s.getCurrentSpeed() < temp);

    }
    @Test
    public void testMoveRight(){
        Volvo240 v = new Volvo240();
        int tempy = v.coordinates.y;
        v.move();
        assertTrue(v.coordinates.y == tempy+v.getCurrentSpeed());
        v.turnRight();
        int tempx = v.coordinates.x;
        v.move();
        assertTrue(v.coordinates.x == tempx+v.getCurrentSpeed());
        tempy = v.coordinates.y;
        v.turnRight();
        v.move();
        assertTrue(v.coordinates.y == tempy-v.getCurrentSpeed());
        tempx = v.coordinates.x;
        v.turnRight();
        v.move();
        assertTrue(v.coordinates.x == tempx-v.getCurrentSpeed());
    }
    @Test
    public void testMoveLeft(){
        Volvo240 v = new Volvo240();
        int tempy = v.coordinates.y;
        v.move();
        assertTrue(v.coordinates.y == tempy+v.getCurrentSpeed());
        v.turnLeft();
        int tempx = v.coordinates.x;
        v.move();
        assertTrue(v.coordinates.x == tempx-v.getCurrentSpeed());
        tempy = v.coordinates.y;
        v.turnLeft();
        v.move();
        assertTrue(v.coordinates.y == tempy-v.getCurrentSpeed());
        tempx = v.coordinates.x;
        v.turnLeft();
        v.move();
        assertTrue(v.coordinates.x == tempx+v.getCurrentSpeed());
    }

    @Test
    public void testSpeedUnderMax(){
        Volvo240 v = new Volvo240();
        for (int i = 0; i <= 100; i++) {
            v.gas(1);
        }
        assertTrue(v.getCurrentSpeed()== v.getEnginePower());

    }

    @Test
    public void testStartEngine(){
        Volvo240 v = new Volvo240();
        v.startEngine();
        assertTrue(v.getCurrentSpeed() == 0.1);
    }
    @Test
    public void testStopEngine(){
        Volvo240 v = new Volvo240();
        v.stopEngine();
        assertTrue(v.getCurrentSpeed() == 0);
    }


    @Test
    public void testSetTurboOn(){
        s.setTurboOn();
        assertTrue(s.getTurboStatus()==true);
    }

    @Test
    public void testSetTurboOf(){
        s.setTurboOff();
        assertFalse(s.getTurboStatus()==true);
    }

    @Test
    public void testInitialSpeed(){
        Car c = new Volvo240();
        assertTrue(c.getCurrentSpeed()==0);
    }

    @Test
    public void testGasOutSideIntervalException(){
        try {
            s.gas(2);
        }catch (RuntimeException e){
            assertEquals(2 + " is outside the accepted interval.", e.getMessage());
        }
    }
    @Test
    public void testGasOutSideInterval(){
        double temp = s.getCurrentSpeed();
        try {
            s.gas(2);
        }catch (RuntimeException e){}
        assertTrue(temp==s.getCurrentSpeed());
    }
    @Test
    public void testBreakOutSideInterval(){
        double temp = s.getCurrentSpeed();
        try {
            s.brake(2);
        }catch (RuntimeException e){}
        assertTrue(temp==s.getCurrentSpeed());
    }


}



