/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slr;

/**
 *
 * @author Polaris
 */
public class Vector {
    private MyCharacter value;
    private State source;
    private State Destination;

    public Vector(MyCharacter value, State source, State Destination) {
        this.value = value;
        this.source = source;
        this.Destination = Destination;
    }

    public MyCharacter getValue() {
        return value;
    }

    public void setValue(MyCharacter value) {
        this.value = value;
    }

    public State getSource() {
        return source;
    }

    public void setSource(State source) {
        this.source = source;
    }

    public State getDestination() {
        return Destination;
    }

    public void setDestination(State Destination) {
        this.Destination = Destination;
    }
    
    
    
    
}
