package polynomial;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

/**
 * A class for representing Polynomials
 * 
 * Commented by 
 * @author J.Vedder S4397101
 * 
 * Code supplied by
 * @author Sjaak Smetsers
 * @date 15-03-2011
 */
public class Polynomial {

    /**
     * A polynomial is a sequence of terms here kept in an List
     */
    List<Term> terms;

    /**
     * A constructor for creating the zero Polynomial zero is presented as an
     * empty list of terms and not as a single term with 0 as a coefficient
     */
    public Polynomial() {
        terms = new ArrayList<>();
    }

    /**
     * The copy constructor making a deep copy
     *
     * @param p the copied polynomial
     *
     */
    public Polynomial( Polynomial p ) {
        terms = new ArrayList<>(p.terms.size());
        for (Term t : p.terms) {
            terms.add(new Term(t));
        }
    }

    /**
     * A Constructor creating a polynomial from the argument string.
     *
     * @param s a String representing a list of terms which is converted to a
     * scanner and passed to scanTerm for reading each individual term
     */
    public Polynomial( String s ) {
        terms = new ArrayList<>();
        Scanner scan = new Scanner(s);

        for (Term t = Term.scanTerm(scan); t != null; t = Term.scanTerm(scan)) {
            if ( t.getCoef() == 0 ) {
                throw new InputMismatchException ( "Coefficient is zero" );
            }
            if ( ! terms.isEmpty() && terms.get( terms.size()-1).getExp() >=  t.getExp() ) {
                throw new InputMismatchException ( "Wrong order of terms" );
            }
            terms.add(t);
        }
    }

    /**
     * A straightforward conversion of a Polynomial into a string based on the
     * toString for terms
     *
     * @return a readable string representation of this
     */
    @Override
    public String toString() {
        Iterator it = terms.iterator();
        if ( it.hasNext() ) {
            StringBuilder sb = new StringBuilder( it.next().toString() );
            while ( it.hasNext() ) {
                sb.append( " + " ).append( it.next() );
            }
            return sb.toString();
        } else {
            return "0";
        }
    }

    /**
     * Add one Polynomial to another.
     * Create two list Iterators and iterate over the elements in them as long 
     * as there are elements in both iterators.
     * For each element pair check if the exponents are the same if so add the
     * factors together. If the result is zero remove the term.
     * If term a is smaller then term b set the cursor one back and place term b
     * at the cursor position. (basically skip this term and do it next
     * iteration). else set the cursor for iterator b one back.
     * When trough list a or be see if there are any terms that could not be
     * added together and add those to the back of the list.
     * @param b the polynomial to add to this one
     */
    public void plus(Polynomial b) {
        ListIterator<Term> lita = terms.listIterator(),
                litb = b.terms.listIterator();
        while (lita.hasNext() && litb.hasNext()) {
            Term ta = lita.next(), tb = litb.next();
            if (ta.getExp() == tb.getExp()) {
                ta.plus(tb);
                if (ta.getCoef() == 0) {
                    lita.remove();
                }
            } else if (ta.getExp() > tb.getExp()) {
                lita.previous();
                lita.add(new Term(tb));
            } else {
                litb.previous();
            }
        }
        while (litb.hasNext()) {
            lita.add(new Term(litb.next()));
        }
    }

    /**
     * Multiply the Polynomial with a Term and return the Polynomial.
     * Loop over all the terms in this polynomial and multiply each with the
     * term to multiply the whole polynomial with. (this method is used for 
     * polynomial * polynomial multiplication).
     * @param t the term to multiply the polynomial with
     * @return the result of the multiplication of the polynomial with the term
     */
    private Polynomial timesTerm(Term t) {
        for (Term rt : terms) {
            rt.times(t);
        }
        return this;
    }

    /**
     * Static for timesTerm
     * @param p Polynomial to multiply term with
     * @param t Term to multiply Polynomial with
     * @return
     */
    private static Polynomial polyTimesTerm( Polynomial p, Term t ) {
        return (new Polynomial( p )).timesTerm( t );
    }

    /**
     * Does subtraction, subtracts this Polynomial with given polynomial.
     * Dose so by first negating the polynomial with a -1 term and then adding it.
     * @param b Polynomial to subtract this Polynomial with
     */
    public void minus(Polynomial b) {
        plus(polyTimesTerm(b, new Term(-1, 0)));
    }

    /**
     * 
     * Multiplies this Polynomial with given Polynomial.
     * First checks if the multiplier is zero and if so sets the whole
     * term to zero.
     * Makes a copy of original polynomial.
     * Loops over all terms in Polynomial does addition with a multiplication
     * of the copy of the original with the term. 
     * @param b Polynomial to multiply this Polynomial with
     */
    public void times(Polynomial b) {
        if (b.terms.isEmpty()) {
            terms.clear();
        } else {
//            if (this == b) {
//                b = new Polynomial(this);
//            }
            Polynomial a = new Polynomial(this);
            terms.clear();
            for (Term termb : b.terms) {
                plus( polyTimesTerm(a, termb) );
            }
        }
    }

    
    
    /**
     * For dividing two polynomials. After division the current polynomial
     * contains the quotient. The remainder is returned as a result.
     *
     * @param b the divisor
     * @return: the remainder
     */
    public Polynomial divide (Polynomial b) {
        if ( b.terms.isEmpty( )) {
            throw new ArithmeticException( "Division by zero" );
        } else if ( terms.isEmpty() ) {
            return new Polynomial(this);
        } else {
            if (this == b) {
                b = new Polynomial(this);
            }
            Term tb = b.terms.get( b.terms.size() - 1 );
            Polynomial a = new Polynomial(this);
            terms.clear();
            do  {
                Term ta = a.terms.get( a.terms.size() - 1 );
                if ( tb.getExp() > ta.getExp() ) {
                    break;
                }
                Term qu = new Term (ta);
                qu.divide(tb);
                terms.add(0, qu);
                a.minus( polyTimesTerm(b, qu)  );
            }
            while( ! a.terms.isEmpty() );
            return a;
        }
    }

    /**
     * Checks for equality between this and another object.
     * First checks if the object to compare with is a Polynomial.
     * Then looks for equality between the terms lists of both Polynomials
     * @param other_poly
     * @return
     */
    @Override
    public boolean equals(Object other_poly) {
        if (other_poly instanceof Polynomial ) {
            return terms.equals(((Polynomial) other_poly).terms);
         } else {
            return false;
       }
    }

}
