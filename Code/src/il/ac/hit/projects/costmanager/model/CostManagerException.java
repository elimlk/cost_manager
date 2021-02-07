/*
Submitted by:
Itamar Yacoby 203023338
Eliran Malka 302830617
Idan Shturm 208535674
 */

package il.ac.hit.projects.costmanager.model;
public class CostManagerException extends Exception {

    public CostManagerException (String message) {
        super(message);
    }

    public CostManagerException (String message, Throwable cause) {
        super(message,cause);
    }
}
