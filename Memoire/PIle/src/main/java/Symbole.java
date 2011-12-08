

/**
 *
 * @author anais
 */
public class Symbole {

    public String nom;
    public String valeur;
    public String genre;
    public String type;
    public String conteneur;
    public String portee;

    public Symbole(String nom, String valeur, String genre, String type, String conteneur, String portee){
        this.nom=nom;
        this.valeur=valeur;
        this.genre=genre;
        this.type=type;
        this.conteneur=conteneur;
        this.portee=portee;
    }

public Symbole(String nom,  String genre, String type, String conteneur, String portee){
        this.nom=nom;
        this.genre=genre;
        this.type=type;
        this.conteneur=conteneur;
        this.portee=portee;
    }

public Symbole(String nom,  String genre, String type, String portee){
        this.nom=nom;
        this.genre=genre;
        this.type=type;
        this.portee=portee;
    }

    public boolean compNom(String nom){
        return this.nom.equals(nom);

    }

    public boolean compVal(String val){
        return this.valeur.equals(val);
    }

    public boolean compType(String type){
        return this.type.equals(type);
    }

    public boolean compConteneur(String cont){
        return this.conteneur.equals(cont);
    }

    public boolean compPortee(String port){
        return this.portee.equals(port);
    }

    public boolean compGenre(String genre){
        return this.genre.equals(genre);
    }

    @Override
    public String toString(){
        return "<" +this.nom+ ", " + this.valeur + "," + genre + "," + type  + "," +this.portee+ ">";
    }

}

