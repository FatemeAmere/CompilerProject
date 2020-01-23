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
    private Character value;
    private State source;
    private State Destination;

    public Vector(Character value, State source, State Destination) {
        this.value = value;
        this.source = source;
        this.Destination = Destination;
    }
    
    
}
