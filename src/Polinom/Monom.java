package Polinom;

import java.util.Objects;
import java.util.regex.Pattern;

public class Monom {
    private float coeff;
    private int degree;

    Monom(float coeff, int degree){
        this.coeff = coeff;
        this.degree = degree;
    }

    public static Monom fromLine(String line){
        int coeff, degree = 1;
        if(!line.contains("x")){
            return new Monom(Integer.parseInt(line), 0);
        }
        if(!line.contains("^")){
            coeff = Integer.parseInt( line.split("x")[0]);
            return new Monom(coeff, 1);
        }
        String[] ret = line.split(Pattern.quote("x"));
        if(ret[0].equals("")){
            coeff = 1;
        }
        else{
            coeff = Integer.parseInt(ret[0]);
        }
        if(ret[1].contains("^")) {
            ret = ret[1].split(Pattern.quote("^"));
            degree = Integer.parseInt(ret[1]);
        }
        return new Monom(coeff, degree);
    }

    public float getCoeff(){
        return coeff;
    }
    public int getDegree(){
        return degree;
    }
    @Override
    public String toString(){
        if(degree == 0){
            return coeff + "";
        }
        if(coeff == 1){
            return "x^" + degree;
        }
        return coeff + "x^" + degree;
    }

    public Monom add(Monom monom){
        return new Monom(this.coeff + monom.coeff, degree);
    }
    public Monom subtract(Monom m){
        return new Monom(this.coeff - m.coeff, degree);
    }
    public Monom multiply(Monom m){
        return new Monom(this.coeff* m.coeff, degree + m.degree);
    }
    public Monom differentiate(){
        if(degree == 0){
            return new Monom(0,0);
        }
        float newCoeff = coeff * degree;
        int newDegree = degree -1;
        return new Monom(newCoeff, newDegree);
    }
    public Monom integrate(){
        int newDegree = this.degree + 1;
        float newCoeff = ((float)coeff )* (1f/newDegree);
        return new Monom(newCoeff, newDegree);
    }

    @Override
    public boolean equals(Object other){
            if (this == other) return true;
            if (other == null || getClass() != other.getClass()) return false;

            return coeff == ((Monom)other).getCoeff() && degree == ((Monom)other).getDegree();
    }
    public Monom divide(Monom m){
        return new Monom(coeff/m.coeff,degree-m.degree);
    }



}
