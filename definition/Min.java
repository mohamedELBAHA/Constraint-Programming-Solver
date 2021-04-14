package definition;

import java.util.ArrayList;
import java.util.List;

public class Min implements Constraint {
    private Variable m ;
    private Variable x ;
    private Variable y ;
    private String name;

    // constructeur
    public Min(Variable m, Variable x, Variable y, String name){
        this.m = m;
        this.x = x;
        this.y = y;
        this.name = name;
    }

    @Override
    public List<Variable> getVars() {
        ArrayList<Variable> vars = new ArrayList<>();
        vars.add(m);
        vars.add(x);
        vars.add(y);
        return vars;
    }

    @Override
    public boolean isSatisfied() {
        if (m.isInstantiated() == true &&
            x.isInstantiated() == true &&
            y.isInstantiated() == true){

            if( m.getValue() == x.getValue() || 
                m.getValue() == y.getValue() &&
                m.getValue() <= x.getValue() &&
                m.getValue() <= y.getValue()){
                    return true;
                }
        }
        return false;
    }

    @Override
    public boolean isNecessary() {
        if(this.m.getInf() <= this.x.getSup() &&
           this.m.getInf() <= this.y.getSup()){
               return true;
           }
        return false;
    }

    @Override
    public boolean filter() {
        boolean ok = false; 
        if(y.getSup() < m.getSup()){
            for(int val : m.getDomain()){
                if(val > y.getSup() ){
                    m.getDomain().remove(val);
                    ok = true;
                }
            }
        }
        if(x.getSup() < m.getSup()){
            for(int val : m.getDomain()){
                if(val > x.getSup() ){
                    m.getDomain().remove(val);
                    ok = true;
                }
            }
        }
        if(m.getInf() > x.getInf()){
            for(int val : x.getDomain()){
                if (val < m.getInf()){
                    x.getDomain().remove(val);
                    ok = true;
                }
            }
        }
        if(m.getInf() > y.getInf()){
            for(int val : y.getDomain()){
                if (val < m.getInf()){
                    y.getDomain().remove(val);
                    ok = true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean allInstantiated() {
        for (Variable var: this.getVars()){
            if (var.isInstantiated()==false){
                return false;
            }
        }
        return true;
    }

    @Override
    public String getName() {
        return this.name;
    }



    
}