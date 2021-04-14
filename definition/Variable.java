
package definition ;
public class Variable {
    
    private Domain dom; // le domaine associe
    private String name ; // le nom de la variable

    public Variable( Domain dom, String name){
        this.dom = dom;
        this.name = name;
    }

    public Domain getDomain() {
        return this.dom;
    }
    
    public String getName(){
        return this.name;
    }

    public void setDomain(Domain dom){
        this.dom = dom;
    }
    // retourne vrai ssi la variable est instanciee
    public boolean isInstantiated() {
        if (this.getDomain().size()==1){
            return true;
        }
        return false;
    }

    // retourne vrai ssi le domaine de la variable contient la valeur v
    public boolean canBeInstantiatedTo(int v) {
        return this.getDomain().contains(v);
    }

    // retourne le nombre de valeurs dans le domaine de la variable
    public int getDomainSize() {
        return this.getDomain().size();
    }

    // supprime la valeur v du domaine de la variable
    public void remValue(int v) {
        this.getDomain().remove(v);
    }

    // supprime toutes les valeurs entre min (inclus) et max (inclus)
    public void remValues(int min, int max) {
        this.getDomain().remove(min, max);
    }

    // vide le domaine : supprime toutes ses valeurs
    public void remAllValues() {
        this.getDomain().removeAll();
    }

    // instantie la variable a la valeur v
    public void instantiate(int v) {
        this.getDomain().instantiate(v);
    }

    // retourne la plus petite valeur du domaine
    public int getInf() {
        int inf = this.getDomain().firstValue();
        for (int val : this.getDomain()){
            if( val < inf){
                inf = val;
            }
        }
        return inf;
    }

    // retourne la plus grande valeur du domaine
    public int getSup() {
        int sup = this.getDomain().firstValue();
        for (int val : this.getDomain()){
            if (val > sup) {
                sup = val ;
            }
        }
        return sup;
    }

    // retourne la valeur affectee a la variable ssi la variable est effectivement instanciee, sinon -1
    public int getValue() {
        if (this.getDomain().size()==1){
            return this.getDomain().firstValue();
        }
        return -1;
    }
    // retourne vrai si le domaine est vide
    public boolean isEmpty() {
        if (this.getDomain().size() == 0){
            return true;
        }
        return false;
    }

}