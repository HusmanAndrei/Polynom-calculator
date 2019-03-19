package Polinom;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class PolinomTest {
    private Polinom p1;
    private Polinom p2;

    @Before
    public void setP1() {
        List<Monom> monomials = new ArrayList<>(Arrays.asList(
                new Monom(6, 5),
                new Monom(4, 3),
                new Monom(1, 2),
                new Monom(2, 1)));
        p1 = new Polinom(monomials);
    }

    @Before
    public void setP2() {
        List<Monom> monomials = new ArrayList<>(Arrays.asList(new Monom(3, 4),
                new Monom(5, 3),
                new Monom(8, 2),
                new Monom(3, 0 )));
        p2 = new Polinom(monomials);
    }

    @Test
    public void add() {
        List<Monom> monomials = new ArrayList<>(Arrays.asList(new Monom(3, 0),
        new Monom(2, 1),
        new Monom(9, 2),
        new Monom(9, 3),
        new Monom(3, 4),
        new Monom(6, 5)));
        Polinom result = new Polinom(monomials);
        Assert.assertEquals(p1.add(p2), result);
    }

    @Test
    public void subtract() {
        List<Monom> monomials = new ArrayList<>(Arrays.asList(new Monom(-3, 0),
                new Monom(2, 1),
                new Monom(-7, 2),
                new Monom(-1, 3),
                new Monom(-3, 4),
                new Monom(6, 5)));
        Polinom result = new Polinom(monomials);
        Assert.assertEquals(p1.subtract(p2), result);
    }

    @Test
    public void multiply() {
        List<Monom> monomials = new ArrayList<>(Arrays.asList(new Monom(6, 1),
                new Monom(3, 2),
                new Monom(28, 3),
                new Monom(18, 4),
                new Monom(61, 5),
                new Monom(23, 6),
                new Monom(60, 7),
                new Monom(30, 8),
                new Monom(18, 9)));
        Polinom result = new Polinom(monomials);
        Assert.assertEquals(p1.multiply(p2), result);
    }

    @Test
    public void differentiate() {
        List<Monom> monomials = new ArrayList<>(Arrays.asList(new Monom(30, 4),
                new Monom(12, 2),
                new Monom(2, 1),
                new Monom(2, 0)));
        Polinom result = new Polinom(monomials);
        Assert.assertEquals(p1.differentiate(), result);
    }

    @Test
    public void integrate() {
        List<Monom> mono = new ArrayList<>(Arrays.asList(
                new Monom(1, 6),
                new Monom(1, 4),
                new Monom((float)0.33333334, 3),
                new Monom(1, 2)
        ));
        Polinom result = new Polinom(mono);
        //System.out.println(p1.toString());
        //System.out.println(p1.integrate().toString());
        Assert.assertEquals(result, p1.integrate());
    }

    @Test
    public void divide() {
        List<Monom> monomials = new ArrayList<>(Arrays.asList(new Monom(2, 1),
                new Monom((float)-3.3333333333333335, 0)));

        Polinom result = new Polinom(monomials);

        List<Monom> monomials2 = new ArrayList<>(Arrays.asList(new Monom((float)4.666666, 3),
                new Monom((float)27.66666666666666, 2),
                new Monom(-4, 1),
                new Monom(10, 0)));


        Polinom remainder = new Polinom(monomials2);

        Assert.assertEquals(p1.divide(p2).get(0), result);
        assertEquals(p1.divide(p2).get(1), remainder);
    }
}