package definition;

import java.util.List;

public class Csp {

    List<Variable> vars; // l'ensemble des variables du CSP. Note: les domaines sont connus au travers des variables
    List<Constraint> cons; // l'ensemble des contraintes du CSP
    
    // constructeur 
    public Csp (List<Variable> vars, List<Constraint> cons){
        this.vars = vars;
        this.cons = cons;
    } 

    public List<Variable> getVars() {
        return vars;
    }
    
    public List<Constraint> getConstraints() {
	return this.cons;
    }

    // retourne la premiere variable non instanciee du csp
    public Variable nextVarToInstantiate() {
        for (Variable var : this.vars){
            if(var.isInstantiated() == false){
                return var;
            }
        }
        return null;
    }

    // retourne vrai ssi toutes les variables sont instanciées
    public boolean allInstanciated() {
        for (Variable var : this.getVars()){
            if (var.isInstantiated() == false){
                return false;
            }
        }
         return true ;
    }

    // retourne vrai ssi le CSP possède (au moins) une solution : 
    // l'ensemble des contraintes du CSP est vérifié
    // ATTENTION : ce n'est pas la seule condition
    /*
    public boolean hasSolution() {
        boolean gotSol = true;
        for (Constraint con: this.cons){
            System.out.println(con);
            if(con.isSatisfied() != true){
                gotSol = false;
            }
        }
         return gotSol;
    }
    */
    public boolean hasSolution() {
        for (Constraint con: this.cons){
            if(con.isSatisfied() != true){
                return false;
            }
        }
         return true;
    }


 
}
