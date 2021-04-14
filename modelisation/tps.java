package modelisation;
import java.util.ArrayList;

import definition.*;


public class tps{

    public static Csp TP2(){

        // Définition des variables et domaines
        System.out.println(" ");
        System.out.println("LES VARIABLES DU CSP : ");
        System.out.println(" ");
        Variable x1 = new Variable(new DomainBitSet(2,6), "x1");
        System.out.println("    x1 ∈ " + x1.getDomain() );
        Variable x2 = new Variable(new DomainBitSet(4,7), "x2");
        System.out.println("    x2 ∈ " + x2.getDomain());
        Variable x3 = new Variable(new DomainBitSet(6,9), "x3");
        System.out.println("    x3 ∈ " + x3.getDomain());

        ArrayList<Variable> vars = new ArrayList<Variable>();
        vars.add(x1);
        vars.add(x2);
        vars.add(x3);

        // Définition des contraintes
        System.out.println(" ");
        System.out.println("LES CONTRAINTES DU CSP : ");
        System.out.println(" ");
        LessThanOrEqual cons1 = new LessThanOrEqual(x3, x2, " x3 <= x2");
        System.out.println("    s.c  : " + cons1.getName()); 
        LessThanOrEqual cons2 = new LessThanOrEqual(x2, x1, " x2 <= x1 ");
        System.out.println("    s.c  : " + cons2.getName());
        
        System.out.println(" ");
        ArrayList<Constraint> cons = new ArrayList<Constraint>();
        cons.add(cons1);
        cons.add(cons2);

        System.out.println("RÉSOLUTION DU CSP : ");
        System.out.println("");

        return new Csp(vars,cons); 
    
    }

    public static Csp TP1(){
        // Définition des variables et domaines
        System.out.println(" ");
        System.out.println("LES VARIABLES DU CSP : ");
        System.out.println(" ");
        Variable x1 = new Variable(new DomainBitSet(0,2), "x1");
        System.out.println("    x1 ∈ " + x1.getDomain() );
        Variable x2 = new Variable(new DomainBitSet(0,2), "x2");
        System.out.println("    x2 ∈ " + x2.getDomain());
        Variable x3 = new Variable(new DomainBitSet(0,2), "x3");
        System.out.println("    x3 ∈ " + x3.getDomain());
    
        ArrayList<Variable> vars = new ArrayList<Variable>();
        vars.add(x1);
        vars.add(x2);
        vars.add(x3);
    
        // Définition des contraintes
        System.out.println(" ");
        System.out.println("LES CONTRAINTES DU CSP : ");
        System.out.println(" ");
        LessThanStrict cons1 = new LessThanStrict(x1, x2, " x1 < x2 ");
        System.out.println("    s.c  : x1 < x2 "); 
        NotEqual cons2 = new NotEqual(x1, x3, "x1 =/= x3");
        System.out.println("    s.c  : x1 != x3");
        
        System.out.println(" ");
        ArrayList<Constraint> cons = new ArrayList<Constraint>();
        cons.add(cons1);
        cons.add(cons2);
    
    
        System.out.println("RÉSOLUTION DU CSP : ");
        System.out.println("");
        return new Csp(vars,cons); 
    
    }
}