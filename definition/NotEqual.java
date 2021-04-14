package definition;

import java.util.ArrayList;
import java.util.List;

public class NotEqual implements Constraint {
    /*  Constraint not equal ( a != b ) */ 
    
    private Variable var1;
    private Variable var2;
    private String name;
    
    // Constructeur 
    public NotEqual(Variable var1, Variable var2, String name){
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

        if (var1.isInstantiated() == true &&
            var2.isInstantiated() == true && 
            val1 != val2){
            return true;
        }
        return false;
    }

    @Override
    public boolean isNecessary() {
        if(var1.getDomain().size() > 1 || var2.getDomain().size() > 1){
            return true;
        }
        else{
            if (var1.getDomain().firstValue() != var2.getDomain().firstValue()){
                return true; 
            }
        }
        return false;
    }

    @Override
    public boolean allInstantiated() {
        for ( Variable var : this.getVars()){
            if(var.isInstantiated() == false){
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean filter() {
        boolean isRemoved = false;
        if(var1.getDomain().size() == 1 && var2.getDomain().size() >= 1){
            for(int val2 : var2.getDomain()){
                if (val2 == var1.getDomain().firstValue()){
                    var2.getDomain().remove(val2);
                    isRemoved = true;
                }
            }
        }
        else{
            if(var1.getDomain().size() >= 1 && var2.getDomain().size() == 1){
                for(int val1 : var1.getDomain()){
                    if(val1 == var2.getDomain().firstValue()){
                        var1.getDomain().remove(val1);
                        isRemoved = true;
                    }
                }
            }
        }
        return isRemoved;
    }
    
}