/**
 * Basic decorator for PillCounter interface.
 */
public class PillCounterDecorator implements PillCounter {
  protected PillCounter delegate;

  /**
   * Constructor that takes a PillCounter delegate.
   *
   * @param delegate the PillCounter to decorate
   */
  public PillCounterDecorator(PillCounter delegate) {
    this.delegate = delegate;
  }

  @Override
  public void addPill(int count) {
    delegate.addPill(count);
  }

  @Override
  public void removePill() {
    delegate.removePill();
  }

  @Override
  public void reset() {
    delegate.reset();
  }

  @Override
  public int getPillCount() {
    return delegate.getPillCount();
  }
}