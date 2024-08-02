package scramble.model.common.api;

/**
 * Interface for the generic class Pair.
 * 
 * @param <X> type of first element
 * @param <Y> type of second element
 */
public interface Pair<X, Y> {

    /**
     * This method is a setter for both generic elements to be done at the same
     * time.
     * 
     * @param firstElement  is the field that refers to the first generic element
     *                      (any type is accepted)
     * @param secondElement is the field that refers to the second generic element
     *                      (any type is accepted)
     */
    void setPair(X firstElement, Y secondElement);

    /**
     * Setter for the first element.
     * 
     * @param firstElement
     */

    void setFirstElement(X firstElement);

    /**
     * Setter for the first element.
     * 
     * @param secondElement
     */
    void setSecondElement(Y secondElement);

    /**
     * Getter for the first element.
     * 
     * @return returns the first element
     */
    X getFirstElement();

    /**
     * Getter for the first.
     * 
     * @return returns the second element
     */
    Y getSecondElement();
}
