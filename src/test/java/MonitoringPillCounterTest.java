import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Test for the MonitoringPillCounterDecorator.
 */
public class MonitoringPillCounterTest {

  @Test
  public void testMonitoringDecorator() {
    MonitoringPillCounterDecorator counter =
            new MonitoringPillCounterDecorator(new LoggingPillCounter());

    boolean result = conveyerBelt(counter);
    assertTrue(result);

    List<Integer> addCounts = counter.getAddCounts();
    System.out.println("Total bottles processed: " + addCounts.size());

    System.out.println("First 10 bottles (100 pills each, 1 at a time):");
    for (int idx = 0; idx < Math.min(10, addCounts.size()); idx++) {
      System.out.println("Bottle " + (idx + 1) + ": " + addCounts.get(idx) + " addPill() calls");
    }

    System.out.println("Bottles 101-110 (20 pills each, 4 at a time):");
    for (int idx = 100; idx < Math.min(110, addCounts.size()); idx++) {
      System.out.println("Bottle " + (idx + 1) + ": " + addCounts.get(idx) + " addPill() calls");
    }

    System.out.println("Bottles 1101-1110 (200 pills each, 2 at a time):");
    for (int idx = 1100; idx < Math.min(1110, addCounts.size()); idx++) {
      System.out.println("Bottle " + (idx + 1) + ": " + addCounts.get(idx) + " addPill() calls");
    }
  }

  private boolean conveyerBelt(PillCounter counter) {
    //make 100 bottles of 100 pills each
    for (int bottle = 0; bottle < 100; bottle += 1) {
      for (int pill = 0; pill < 100; pill += 1) {
        counter.addPill(1); //1 pill at a time
      }
      assertEquals(100, counter.getPillCount());
      counter.reset(); //for the next bottle
    }

    //make 1000 bottles of 20 pills each
    for (int bottle = 0; bottle < 1000; bottle += 1) {
      for (int pill = 0; pill < 20; pill += 4) {
        counter.addPill(4); //4 pills at a time (newer machine)
      }
      assertEquals(20, counter.getPillCount());
      counter.reset(); //for the next bottle
    }

    //make 500 bottles of 200 pills each
    for (int bottle = 0; bottle < 500; bottle += 1) {
      for (int pill = 0; pill < 200; pill += 2) {
        counter.addPill(2); //2 pills at a time (third machine)
      }
      assertEquals(200, counter.getPillCount());
      counter.reset(); //for the next bottle
    }
    return true;
  }
}