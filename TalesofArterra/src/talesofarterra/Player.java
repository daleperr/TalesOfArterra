package talesofarterra;

import java.io.Serializable;
import java.util.Arrays;

/**
 *
 * @author Dale
 */
public class Player implements Serializable {

    //private member variables
    private char[] name;
    private int bank;

    //public getter and setter functions
    public Player() {
    }
    
    public char[] getName() {
        return name;
    }

    public void setName(char[] name) {
        this.name = name;
    }

    public int getBank() {
        return bank;
    }

    public void setBank(int bank) {
        this.bank = bank;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + Arrays.hashCode(this.name);
        hash = 41 * hash + this.bank;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Player other = (Player) obj;
        if (this.bank != other.bank) {
            return false;
        }
        if (!Arrays.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Player{" + "name=" + name + ", bank=" + bank + '}';
    }

}
