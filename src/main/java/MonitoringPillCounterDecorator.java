import java.util.ArrayList;
import java.util.List;

/**
 * Decorator that monitors the number of times addPill is called before each reset.
 */
public class MonitoringPillCounterDecorator extends PillCounterDecorator {
  private List<Integer> addCounts;
  private int currentAddCount;

  /**
   * Constructor that takes a PillCounter delegate.
   *
   * @param delegate the PillCounter to decorate
   */
  public MonitoringPillCounterDecorator(PillCounter delegate) {
    super(delegate);
    this.addCounts = new ArrayList<>();
    this.currentAddCount = 0;
  }

  @Override
  public void addPill(int count) {
    currentAddCount++;
    delegate.addPill(count);
  }

  @Override
  public void reset() {
    addCounts.add(currentAddCount);
    currentAddCount = 0;
    delegate.reset();
  }

  /**
   * Returns the list of add counts recorded across all resets.
   *
   * @return list of add counts
   */
  public List<Integer> getAddCounts() {
    return new ArrayList<>(addCounts);
  }
}