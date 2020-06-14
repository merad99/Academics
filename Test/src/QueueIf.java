
public interface QueueIf<Item> {
	void enqueuer(Item item);
    Item dequeuer();
    boolean	isEmpty();
    int	size();

}
