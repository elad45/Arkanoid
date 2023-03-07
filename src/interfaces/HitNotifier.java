//Elad Vizenblit
package interfaces;


/**
 * This is the Hit Notifier interface. adding\removing listener to our hit notify.
 */
public interface HitNotifier {

    /**
     * Add hl as a listener to hit events.
     * @param hl HitListener to add as a listener to hit events
     */
    void addHitListener(HitListener hl);


    /**
     * Removes hl from the list of listeners to hit events.
     * @param hl HitListener we want to remove from being a listener to hit events.
     */
    void removeHitListener(HitListener hl);
}