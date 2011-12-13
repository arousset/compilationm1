

/**
 *
 * @author anais
 */
public class ElementListeChaine {

    private Symbole val;
    private ElementListeChaine prec;
    private ElementListeChaine next;

    public ElementListeChaine(Symbole val){
        this.val=val;
        this.prec=null;
        this.next=null;
    }

     public ElementListeChaine(Symbole val, ElementListeChaine p, ElementListeChaine n){
        this.val=val;
        this.prec=p;
        this.next=n;
    }

    public Symbole getVal(){
        return val;
    }


    public void setNext(ElementListeChaine next) {
        this.next = next;
    }

    public void setPrec(ElementListeChaine prec) {
        this.prec = prec;
    }

    public void setVal(Symbole val) {
        this.val = val;
    }

    public ElementListeChaine getNext() {
        return next;
    }

    public ElementListeChaine getPrec() {
        return prec;
    }

    public void add(Symbole s){
        if (this.next==null){
            this.next=new ElementListeChaine(s);
            this.next.setPrec(this);
        }
        else{
            if(this.next!=null){
                this.next.add(s);
             }

        }
    }
    
    public void del(Symbole s){
        if((this.val==s)){
            if(this.prec!=null){
                this.prec.setNext(this.next);
            }
            if(this.next!=null){
                this.next.setPrec(this.prec);
            }
            this.val=null;
        }
        else{
            if(this.next!=null){
                this.next.del(s);
            }


        }
    }

    public ElementListeChaine recherche(Symbole s){
        if(this.val==s){
            return this;
        }
        else{
            if(this.next!=null){
                return this.next.recherche(s);
            }
             else{
                return null;
            }
        }
    }

    public String rechercheType(String nom, String genre, String portee){
        if(this.val.compNom(nom) && this.val.compPortee(portee) && (this.val.compGenre(genre))){
            return this.val.type;
        }
         else{
            if(this.next!=null){
                return this.next.rechercheType(nom, genre, portee);
            }
            else{
                return null;
            }
        }
    }

    public String recherchePortee(String nom, String genre){
        if(this.val.compNom(nom) && this.val.compGenre(genre)){
            return this.val.portee;
        }
        else{
            if(this.next != null){
                return this.next.recherchePortee(nom, genre);
            }
             else{
                return null;
             }
        }

    }

    public String toString(){
        String res= val.toString()+" ->";
        if(this.next!=null){
            res+=next.toString();
        }
         else{
            res+= "null";
         }
        return res;
    }

}
