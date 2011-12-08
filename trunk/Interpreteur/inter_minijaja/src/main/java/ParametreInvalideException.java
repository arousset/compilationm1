/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author rhork
 */
class ParametreInvalideException extends Exception {

    String s;
    
    public ParametreInvalideException(String s) {
        this.s=s;
    }
    
}
