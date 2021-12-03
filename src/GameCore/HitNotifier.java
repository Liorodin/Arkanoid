package GameCore;

/**
 * @author Leonardo Rodin 207377151
 * GameCore.HitNotifier interface
 */
public interface HitNotifier {

    /**
     * adds a GameCore.HitListener object.
     *
     * @param hl the object which we want to add
     */
    void addHitListener(HitListener hl);

    /**
     * removes a GameCore.HitListener object.
     *
     * @param hl the object which we want to remove
     */
    void removeHitListener(HitListener hl);
}
