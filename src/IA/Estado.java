/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IA;

/**
 *
 * @author dario
 */
public class Estado {
    int x;
    int y;
    char oper;
    public Estado predecesor;
    public Estado(int x, int y, char oper,Estado predecesor) {
        this.x=x; 
        this.y=y; 
        this.oper=oper; 
        this.predecesor=predecesor;
}
    public boolean equals(Object x) { 
        Estado e=(Estado)x; 
        return this.x==e.x && this.y==e.y; 
    }
    public int hashCode() { 
        int hash = 3; 
        hash = 89 * hash + this.x;
        hash = 89 * hash + this.y; 
        return hash; 
    }

    public String toString() { 
        return "("+x+","+y+")"; 
    }


}
