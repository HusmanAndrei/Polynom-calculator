package Polinom;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Polinom {
    private List<Monom> monoms;

    Polinom(List<Monom> monoms) {
        this.monoms = monoms;
    }
    Polinom(Monom m){
        monoms = new ArrayList<Monom>();
        monoms.add(m);
    }
    public void addMonom(Monom m){
        monoms.add(m);
    }

    public static Polinom fromLine(String line) {
        List<Monom> m = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (char c : line.toCharArray()) {
            if (c == '-') {
                sb.append("+");
            }
            sb.append(c);
        }
        String[] monomes = sb.toString().split(Pattern.quote("+"));
        for (String monome : monomes) {
            if (!monome.equals("")) {
                m.add(Monom.fromLine(monome));
            }
        }
        return new Polinom(m);
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        sortMonomsDescending();
        for (Monom m : monoms) {
            if (m.getCoeff() > 0) {
                s.append("+");
            }
            s.append(m.toString());

        }
        return s.toString();
    }

    public static List<Monom> getMonomOfDegree(List<Monom> monoms, int degree){
        return monoms.stream().filter(m -> m.getDegree() == degree).collect(Collectors.toList());
    }

    private void filterZero(){
        this.monoms = monoms.stream().filter(m -> m.getCoeff()!= 0).collect(Collectors.toList());
    }
    public static int getHighestDegree(List<Monom> monoms){
        if(monoms.size() == 1){
            return monoms.get(0).getDegree();
        }
        return monoms.stream().sorted( (m1, m2) -> {
                    if(m1.getDegree() > m2.getDegree())
                        return -1;
                    return 1;

                }).findFirst().get().getDegree();

    }

    public static Monom addMonoms(List<Monom> monoms){
        Monom current = monoms.get(0);
        if(monoms.size()==1)
            return current;
        for(int i = 1; i < monoms.size(); i++){
            current = current.add(monoms.get(i));
        }
        return current;
    }

    public Polinom add(Polinom polinom) {

        Set<Monom> monomSet = new HashSet<>();
        for (Monom m : monoms) {
            boolean found = false;
            for (Monom other : polinom.monoms) {
                if (m.getDegree() == other.getDegree()) {
                    monomSet.add(m.add(other));
                    found = true;
                }
            }
            if (!found) {
                monomSet.add(m);
            }
        }
        for (Monom m : polinom.monoms) {
            boolean found = false;
            for (Monom other : monoms) {
                if (m.getDegree() == other.getDegree()) {
                    found = true;
                }
            }
            if (!found) {
                monomSet.add(m);
            }
        }
        Polinom finalP = new Polinom(new ArrayList<>(monomSet));
        finalP.filterZero();
        return finalP;
    }
    public Polinom subtract(Polinom polinom) {

        Set<Monom> monomSet = new HashSet<>();
        for (Monom m : monoms) {
            boolean found = false;
            for (Monom other : polinom.monoms) {
                if (m.getDegree() == other.getDegree()) {
                    monomSet.add(m.subtract(other));
                    found = true;
                }
            }
            if (!found) {
                monomSet.add(m);
            }
        }
        for (Monom m : polinom.monoms) {
            boolean found = false;
            for (Monom other : monoms) {
                if (m.getDegree() == other.getDegree()) {
                    found = true;
                }
            }
            if (!found) {
                monomSet.add(new Monom(-m.getCoeff(), m.getDegree()));
            }
        }

        Polinom finalP = new Polinom(new ArrayList<>(monomSet));
        finalP.filterZero();
        return finalP;
    }
    public Polinom multiply(Polinom polinom){
        List<Monom> monomList = new ArrayList<>();
        for(Monom m : monoms){
            for (Monom other : polinom.monoms){
                monomList.add(m.multiply(other));
            }
        }
        monomList = monomList.stream().sorted((m1, m2) -> {
            if (m1.getDegree() > m2.getDegree()) return 1;
            return -1;
        }).collect(Collectors.toList());

        List<Monom> finalList = new ArrayList<>();
        int high = Polinom.getHighestDegree(monomList);
        for(int d = 0 ; d <=high; d++){
            List<Monom> degreeM = Polinom.getMonomOfDegree(monomList, d);
            if(degreeM.size() != 0) {
                finalList.add(Polinom.addMonoms(degreeM));
            }
        }
        Polinom finalP = new Polinom(finalList);
        finalP.filterZero();
        return finalP;

    }
    public Polinom differentiate(){
        List<Monom> newMonoms = new ArrayList<>();
        for(Monom m : monoms){
            Monom diff = m.differentiate();
            newMonoms.add(diff);
        }
        Polinom pdiff =  new Polinom(newMonoms);
        pdiff.filterZero();
        return pdiff;

    }
    public Polinom integrate(){
        List<Monom> newMonoms = new ArrayList<>();
        for(Monom m : monoms){
            Monom integrate = m.integrate();
            newMonoms.add(integrate);
        }
        Polinom pintegr = new Polinom(newMonoms);
        pintegr.filterZero();
        return pintegr;
    }

    public void sortMonomsAscending(){
        monoms = monoms.stream().sorted( (m1, m2) -> {
            if (m1.getDegree() > m2.getDegree())
                return 1;
            return -1;
        }).collect(Collectors.toList());
    }
    public void sortMonomsDescending(){
        monoms = monoms.stream().sorted( (m1, m2) -> {
            if (m1.getDegree() > m2.getDegree())
                return -1;
            return 1;
        }).collect(Collectors.toList());
    }


    public List<Polinom> divide(Polinom impartitor){
        List<Polinom> res = new ArrayList<>();
        Polinom deimpartit = this;
        deimpartit.sortMonomsDescending();
        impartitor.sortMonomsDescending();
        Polinom rest, inmultit, cat = new Polinom(new ArrayList<>());
        do{
            Monom leading = deimpartit.monoms.get(0).divide(impartitor.monoms.get(0));
            Polinom leadingPolinom = new Polinom(leading);
            inmultit = impartitor.multiply(leadingPolinom);
            rest = deimpartit.subtract(inmultit);
            cat.addMonom(leading);
            deimpartit = rest;
            deimpartit.sortMonomsDescending();
        }
        while(getHighestDegree(rest.monoms) >= getHighestDegree(impartitor.monoms));

        res.add(cat);
        res.add(rest);
        return res;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Polinom))
            return false;
        Polinom otherP = (Polinom)other;
        sortMonomsDescending();
        otherP.sortMonomsDescending();
        for(int i = 0 ; i < monoms.size(); i++){
            if(!(monoms.get(i).equals(otherP.monoms.get(i)))){
                return false;
            }
        }
        return true;

    }


}
