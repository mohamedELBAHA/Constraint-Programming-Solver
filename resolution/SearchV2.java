package resolution;

import java.util.ArrayList;

import definition.Constraint;
import definition.Csp;
import definition.Domain;
import definition.Variable;

public class SearchV2 {

    private static int nbSol;
    private static int nbNoeuds;

    //---------------------------------------------------------------------------------------------------
    // Algo de recherche
    //---------------------------------------------------------------------------------------------------

    public static void backtrack1(Csp csp) {
        boolean ok = true;
        for(Constraint con : csp.getConstraints()){
            if(con.allInstantiated() == true){
                ok = con.isSatisfied() && ok;
            }
        }
        if (ok == true) {
            if (csp.allInstanciated()== true){
                if(csp.hasSolution() == true){
                    for (Variable var : csp.getVars()){
                        System.out.println(var.getName() +" = " + var.getValue() + " ");
                    }
                    System.out.println("");
                    nbSol++;
                }        
            }
            else {
                Variable y = csp.nextVarToInstantiate();
                Domain dom_y = y.getDomain().clone();   
                for (int val : dom_y) {
                    y.instantiate(val);
                    backtrack1(csp);
                    nbNoeuds ++;
                    y.setDomain(dom_y.clone());
                }
            }
        }
    }


    public static void backtrack2(Csp csp) {
        boolean ok = true;
        for(Constraint con : csp.getConstraints()){
            ok = con.isNecessary() && ok;
        }

        if (ok==true) {
            if (csp.allInstanciated() == true){
                if(csp.hasSolution() == true){
                    for(Variable var : csp.getVars()){
                        System.out.println(var.getName()+" = " + var.getValue()+ " ");
                    }
                    System.out.println();
                    nbSol++;
                }
                
            }
            else {
                Variable y = csp.nextVarToInstantiate();
                Domain dom_y = y.getDomain().clone();

                for (int val : dom_y) {
                    y.instantiate(val);
                    backtrack2(csp);
                    nbNoeuds++;
                    y.setDomain(dom_y.clone());
                }
            }
        }
    }


   public static void backtrack3(Csp csp) {
        boolean ok = true;
        
        for (Constraint con : csp.getConstraints()){
            ok = con.isNecessary() && ok;
        }

        if (ok == true) {
            if (csp.allInstanciated()){
                if (csp.hasSolution()){
                    for(Variable var : csp.getVars()){
                        System.out.println(var.getName()+" = "+var.getValue());
                    }
                    System.out.println(" ");
                    nbSol ++;
                }          
            }
        else {
                Variable y = csp.nextVarToInstantiate();
                Domain dom_y = y.getDomain().clone();
                propagation(csp);       
                for (int val : dom_y) {
                    y.instantiate(val);
                    ArrayList<Domain> doms = new ArrayList<Domain>();
                    for (Variable var : csp.getVars()){
                        doms.add(var.getDomain().clone());
                    }
                    propagation(csp);
                    backtrack3(csp);
                    
                    for(int i=0; i< csp.getVars().size(); i++){
                        csp.getVars().get(i).setDomain(doms.get(i).clone());
                    }
                    nbNoeuds++;
                    y.setDomain(dom_y.clone());
                }   
            } 
            System.out.println(" Nombre de solutions trouvées : " + nbSol);
            System.out.println(" Nombre de noeuds explorées : " + nbNoeuds);
            System.out.println(" ");
        }

   
        
    
    }


    public static void propagation(Csp csp){
        boolean ok = true;
        while(ok){
            ok = false;
            for(Constraint con : csp.getConstraints()){
                ok = ok || con.filter();                        
            }
        }
    }


    //-----------------------------------------------------------------------------------------
    
    public static void main(String[] args) {
        // Parametrage et initialisation
        nbSol = 0;
        nbNoeuds = 0;
        //solCount = 1;
        // choix d'algorithme 
        //SearchV1.generateAndTest(SearchV1.TP2());
        //backtrack3(Probleme.TP1());
        //backtrack2(SearchV1.TP2());
        
    }

}
