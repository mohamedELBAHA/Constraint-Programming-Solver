package modelisation;

import java.util.ArrayList;

import definition.*;
import resolution.SearchV1;
import resolution.SearchV2;

public class exam2020 {
    
    private static int nbVar; // Nombre de variable du csp pour la première partie 
    private static int nb; //  Nomre de variable du csp pour la deuxième partie
    //private static int nbSol;
    //private static int solCount;
    //private static int nbNoeuds;
    
    
    public static void resolutionPb(int valMax){
          
       
        // Définition des variables
        System.out.println(" ");
        System.out.println("LES VARIABLES DU CSP : ");
        System.out.println(" ");
        ArrayList <Variable> vars = new ArrayList<>();
        for(int i=0; i< nbVar; i++){
            vars.add(new Variable (new DomainBitSet(1,valMax), "x"+i));        
            System.out.println("x"+i + " ∈ " + vars.get(i).getDomain());
        }

        
        // Définition des contraintes dans l'ordre donné
        LessThanStrict x0_inferieur_x8 = new LessThanStrict(vars.get(0),vars.get(8), "x0 < x8");
        NotEqual x0_different_x3 = new NotEqual(vars.get(0), vars.get(3), "x0  =/= x3");
        LessThanOrEqual x7_inferieurEgale_x0 = new LessThanOrEqual(vars.get(7), vars.get(0), "x7 <= x0 ");
        LessThanStrict x1_inferieur_x4 = new LessThanStrict(vars.get(1), vars.get(4), "x1 < x4 " );
        NotEqual x2_different_x3 = new NotEqual(vars.get(2), vars.get(3), "x2 =/= x3");
        LessThanOrEqual x4_inferieurEgale_x2 = new LessThanOrEqual(vars.get(4), vars.get(2),"x4 <= x2 ");
        LessThanOrEqual x8_inferieurEgale_x3 = new LessThanOrEqual(vars.get(8), vars.get(3),"x8 <= x3 ");
        LessThanStrict x6_inferieur_x4 = new LessThanStrict(vars.get(6), vars.get(4), "x6 < x4  ");
        LessThanOrEqual x4_inferieurEgale_x7 = new LessThanOrEqual(vars.get(4), vars.get(7), "x4 <= x7");
        LessThanStrict x4_inferieur_x9 = new LessThanStrict(vars.get(4), vars.get(9),"x4 < x9 " );
        LessThanStrict x5_inferieur_x9 = new LessThanStrict(vars.get(5), vars.get(9), "x5 < x9 ");
        LessThanOrEqual x6_inferieurEgale_x8 = new LessThanOrEqual(vars.get(6), vars.get(8),"x6 <= x8 ");
        LessThanStrict x6_inferieur_x9 = new LessThanStrict(vars.get(6), vars.get(9), "x6 < x9");
        NotEqual x7_different_x9 = new NotEqual(vars.get(7), vars.get(9), "x7 =/= x9 ");
        LessThanStrict x9_inferieur_x8 = new LessThanStrict(vars.get(9), vars.get(8), "x9 < x8");

        ArrayList<Constraint> cons = new ArrayList<Constraint>();
        cons.add(x0_inferieur_x8);
        cons.add(x0_different_x3);
        cons.add(x7_inferieurEgale_x0);
        cons.add(x1_inferieur_x4);
        cons.add(x2_different_x3);
        cons.add(x4_inferieurEgale_x2);
        cons.add(x8_inferieurEgale_x3);
        cons.add(x6_inferieur_x4);
        cons.add(x4_inferieurEgale_x7);
        cons.add(x4_inferieur_x9);
        cons.add(x5_inferieur_x9);
        cons.add(x6_inferieurEgale_x8);
        cons.add(x6_inferieur_x9);
        cons.add(x7_different_x9);
        cons.add(x9_inferieur_x8);
        
        System.out.println(" ");
        System.out.println("LES CONTRAINTES DU CSP : ");
        System.out.println("s.c ");
        for(Constraint con : cons){
            System.out.println("  " + con.getName() );  
        }

        // Définition du csp  
        Csp csp = new Csp(vars, cons);
        System.out.println("");	
		System.out.println("RÉSOLUTION DU CSP : ");
        System.out.println("");
        
        // Choix de l'algorithme ( Generate&test, Backtrack1, Backtrack2, Bactrack3)   
        SearchV2.backtrack3(csp);                    
        //SearchV2.backtrack2(csp);
        //SearchV2.backtrack1(csp);
        //SearchV1.generateAndTest(csp);

    }

    public static void circuitSuperieursOuEgaux(int nb){
        // Définition des variables 
        ArrayList<Variable> vars = new ArrayList<>();
        for (int i = 0; i < nb ; i++){
            vars.add(new Variable(new DomainBitSet(1,10), "x"+i));
        }

        // Définition des contraintes.
        ArrayList<Constraint> cons = new ArrayList<>();
        for(int i=0; i < nb-1; i++){
            cons.add(new LessThanOrEqual(vars.get(i+1), vars.get(i), vars.get(i+1) +" inferieur ou égale " + vars.get(i) ));
        }
        cons.add(new LessThanOrEqual(vars.get(0), vars.get(nb-1), " "));

        // Définition du csp
        Csp csp = new Csp(vars, cons);
        System.out.println("");	
		System.out.println("RÉSOLUTION DU CSP : ");
        System.out.println("");


        // Choix d'algorithme
        long tic = System.nanoTime();  // set start time 
        SearchV2.backtrack3(csp); 
        long toc = System.nanoTime();  // set finish time 
        long elapsedTime = toc - tic;
        double elapsedTimeSeconds = (double) elapsedTime /1_000_000_000 ;

        // affichage du temps 
        System.out.println(elapsedTimeSeconds);
    

    }

    public static void circuitSuperieursEtDifferents(int nb){
        // Définition des variables 
        ArrayList<Variable> vars = new ArrayList<>();
        for (int i = 0; i < nb ; i++){
            vars.add(new Variable(new DomainBitSet(1,10), "x"+i));
        }

        // Définition des contraintes
        ArrayList<Constraint> cons = new ArrayList<>();
        for(int i=0; i < nb-1; i++){
            cons.add(new LessThanOrEqual(vars.get(i+1), vars.get(i), vars.get(i+1) +" inferieur ou égale " + vars.get(i) ));
            cons.add(new NotEqual(vars.get(i+1), vars.get(i), vars.get(i+1) +" différent " + vars.get(i) ));

        }
        cons.add(new LessThanOrEqual(vars.get(0), vars.get(nb-1), " "));
        cons.add(new NotEqual(vars.get(0), vars.get(nb-1), "  "));

        // Définition du csp
        Csp csp = new Csp(vars, cons);
        System.out.println("");	
		System.out.println("RÉSOLUTION DU CSP : ");
        System.out.println("");

        // Choix d'algorithme
        long tic = System.nanoTime(); // set start time 
        SearchV2.backtrack3(csp); 
        long toc = System.nanoTime(); // set finish time
        long elapsedTime = toc - tic;
        double elapsedTimeSeconds = (double) elapsedTime /1_000_000_000 ;
        
        // affichage du temps 
        System.out.println(elapsedTimeSeconds);

   
    }

    public static void circuitSuperieursStricts(int nb){
        // Définition des variables 
        ArrayList<Variable> vars = new ArrayList<>();
        for (int i = 0; i < nb ; i++){
            vars.add(new Variable(new DomainBitSet(1,10), "x"+i));
        }
        // Définition des contraintes
        ArrayList<Constraint> cons = new ArrayList<>();
        for(int i=0; i < nb-1; i++){
            cons.add(new LessThanStrict(vars.get(i+1), vars.get(i), vars.get(i+1) + "inferieur Strictement" + vars.get(i) ));
        }
        cons.add(new LessThanStrict(vars.get(0), vars.get(nb-1), vars.get(nb-1) + "inferieur Strictement" + vars.get(0)));

        // Définition du csp
        Csp csp = new Csp(vars, cons);
        System.out.println("");	
		System.out.println("RÉSOLUTION DU CSP : ");
        System.out.println("");

         // Choix d'algorithme
         long tic = System.nanoTime(); // set start time 
         SearchV2.backtrack3(csp); 
         long toc = System.nanoTime(); // set finish time
         long elapsedTime = toc - tic;
         double elapsedTimeSeconds = (double) elapsedTime /1_000_000_000 ;
         
         // affichage du temps 
         System.out.println(elapsedTimeSeconds);

        }



    public static void main(String[] args) {
        nbVar = 10;
        //----------------------------------------------------------------------------------------------------
        //                                   CALCUL DE SOLUTIONS 
        //----------------------------------------------------------------------------------------------------

        // Changer la valeur de valMax et executer le code. valMax ∈ {3,4,5,6,7,8}
        //resolutionPb(valMax);

        /*
         Q1 : valMax = 3 : [0 solutions] trouvées et [3 noeus] explorées.
         Q2 : valMax = 4 : [8 solutions] trouvées et [16 noeuds] explorées.    
         Q3 : valMax = 5 : [141 solutions] trouvées et [253 noeuds] explorées. 
         Q4 : valMax = 6 : [1200 solutions] trouvées et [2072 noeuds] explorées.        
         Q5 : valMax = 7 : [6804 solutions] trouvées et [11369 noeuds] explorées.
         Q6 : valMax = 8 : [ 29472 solutions] trouvées et [47614 noeuds] explorées. 
        */

        //------------------------------------------------------------------------------------------------------
        //                                   MONTÉE EN CHARGE 
        //------------------------------------------------------------------------------------------------------
        
        // PARTIE 1 : 

        // Q1 : regarder le code au dessus.
        // Q2 : decomenter les ligne suivantes en changeant la valeur de nb ∈ {10,100,1000}
        
        //circuitSuperieursOuEgaux(10);
        //circuitSuperieursOuEgaux(100);
        //circuitSuperieursOuEgaux(1000);

        /* RÉPONSES
        Pour nb = 10 : résolution en [0.1302139 secondes]
        Pour nb = 100 : résolution en [0.7227676 secondes]
        pour nb = 1000 : résolution en [11.5869795 secondes]
        pour nb = 10000 : résolution en [603.5371527 secondes] > 1 min /!\
        
        La plus grande valeure traitée est 1000. 
        */

        
        // PARTIE 2 :

        // Q1 : regarder le code au dessus.
        // Q2 : decomenter les lignes suivantes en changeant la valeur de nb ∈ {10,100,1000}

        //circuitSuperieursEtDifferents(10);
        //circuitSuperieursEtDifferents(100);
        //circuitSuperieursEtDifferents(1000);

        /* RÉPONSES
        Pour nb = 10 : résolution en [0.027777 secondes]
        Pour nb = 100 : résolution en [0.1331981 secondes] 
        Pour nb = 1000 : résolution en [3.3270916 secondes]
        Pour nb = 10000 : résolution en [327.5997731 secondes] > 1min /!\

        La plus grande valeure traitée est 1000. 
        */

        
        // PARTIE 3
        // Q1 : regarder le code au dessus.
        // Q2 : decomenter les lignes suivantes en changeant la valeur de nb ∈ {10,100,1000}
       
        //circuitSuperieursStricts(10);
        //circuitSuperieursStricts(100);
        //circuitSuperieursStricts(1000);
        //circuitSuperieursStricts(10000);

        /* RÉPONSES
        Pour nb = 10 : résolution en [0.0149278 secondes]
        Pour nb = 100 : résolution en [0.0271065 secondes] 
        Pour nb = 1000 : résolution en [0.1890407 secondes]
        Pour nb = 10000 : résolution en [1.6332275 secondes] 
        Pour nb = 100000 : résolution en [286.6978 secondes] > 1min /!\
        
        La plus grande valeure traitée est 10000. 
        
        */

        //------------------------------------------------------------------------------------------------------
        //                                   CRÉATION D'UNE CONTRAINTE 
        //------------------------------------------------------------------------------------------------------

        

        



    

    }




}
