/**
 * Decorator that batches addPill calls to improve performance.
 */
public class BatchAddingPillCounterDecorator extends PillCounterDecorator {
  private int pendingPills;

  /**
   * Constructor that takes a PillCounter delegate.
   *
   * @param delegate the PillCounter to decorate
   */
  public BatchAddingPillCounterDecorator(PillCounter delegate) {
    super(delegate);
    this.pendingPills = 0;
  }

  @Override
  public void addPill(int count) {
    pendingPills += count;
  }

  @Override
  public void removePill() {
    flushPendingPills();
    delegate.removePill();
  }

  @Override
  public int getPillCount() {
    flushPendingPills();
    return delegate.getPillCount();
  }

  @Override
  public void reset() {
    flushPendingPills();
    delegate.reset();
  }

  /**
   * Helper method to reset all pending pills
   */
  private void flushPendingPills() {
    if (pendingPills > 0) {
      delegate.addPill(pendingPills);
      pendingPills = 0;
    }
  }
}