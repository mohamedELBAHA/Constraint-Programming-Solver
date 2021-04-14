package definition;


import java.util.ArrayList;
import java.util.List;

public class LessThanOrEqual implements Constraint {
    /* Constraint of order : a is strictily less or equal than b ( a <= b ) */
    private Variable var1;
    private Variable var2;
    private String name; 
    
    // constructeur
    public LessThanOrEqual (Variable var1, Variable var2, String name){
        this.var1 = var1;
        this.var2 = var2;
        this.name = name;
    }

    @Override
    public List<Variable> getVars() {
        List<Variable> vars = new ArrayList<Variable>();
        vars.add(this.var1);
        vars.add(this.var2);
        return vars;
    }
    
    public String getName(){
        return this.name;
    }
    
    @Override
    public boolean isSatisfied() {
        int val1 = this.var1.getValue();
        int val2 = this.var2.getValue();

        if(var1.isInstantiated() == true && 
           var2.isInstantiated() == true &&
           val1 <= val2){
            return true;
        }
        return false;
    }

    @Override
    public boolean isNecessary() {
        if(this.var1.getInf() <= this.var2.getSup()){
            return true;
        }
        return false;
    }

    @Override
    public boolean allInstantiated() {
        for ( Variable var : this.getVars()){
            if (var.isInstantiated() == false){
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean filter() {
        boolean isRemoved = false; 
        for (int val : var1.getDomain()){
            if(val > var2.getSup()){
                var1.getDomain().remove(val);
                isRemoved = true;
            }
        }
        for (int val : var2.getDomain()){
            if(val < var1.getInf()){
                var2.getDomain().remove(val);
                isRemoved = true;
            }
        }
        return isRemoved;
    }


    
}
