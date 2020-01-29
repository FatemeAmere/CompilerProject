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
    private int source;
    private int Destination;

    public Vector(MyCharacter value, int source, int Destination) {
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

    public int getSource() {
        return source;
    }

    public void setSource(int source) {
        this.source = source;
    }

    public int getDestination() {
        return Destination;
    }

    public void setDestination(int Destination) {
        this.Destination = Destination;
    }

    @Override
    public String toString() {
        return "Vector{" + "value=" + value + "  /////   " + source + " ------> " + Destination + '}';
    }

  
    
    
    
    
}
