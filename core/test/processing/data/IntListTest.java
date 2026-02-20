package processing.data;

import org.junit.Assert;
import org.junit.Test;

public class IntListTest {

  @Test
  public void testFromRange() {
    IntList list = IntList.fromRange(5);
    Assert.assertEquals(5, list.size());
    Assert.assertEquals(0, list.get(0));
    Assert.assertEquals(4, list.get(4));

    IntList list2 = IntList.fromRange(3, 7);
    Assert.assertEquals(4, list2.size());
    Assert.assertEquals(3, list2.get(0));
    Assert.assertEquals(6, list2.get(3));
  }

  @Test
  public void testConstructorFromArray() {
    IntList list = new IntList(new int[]{10, 20, 30});
    Assert.assertEquals(3, list.size());
    Assert.assertEquals(10, list.get(0));
    Assert.assertEquals(20, list.get(1));
    Assert.assertEquals(30, list.get(2));
  }

  @Test
  public void testAppendAndGet() {
    IntList list = new IntList();
    list.append(5);
    list.append(10);
    Assert.assertEquals(2, list.size());
    Assert.assertEquals(5, list.get(0));
    Assert.assertEquals(10, list.get(1));
  }

  @Test
  public void testSet() {
    IntList list = new IntList(new int[]{1, 2, 3});
    list.set(1, 99);
    Assert.assertEquals(99, list.get(1));
  }

  @Test
  public void testRemoveByIndex() {
    IntList list = new IntList(new int[]{10, 20, 30});
    int removed = list.remove(1);
    Assert.assertEquals(20, removed);
    Assert.assertEquals(2, list.size());
    Assert.assertEquals(30, list.get(1));
  }

  @Test
  public void testRemoveValue() {
    IntList list = new IntList(new int[]{5, 10, 15, 10});
    int idx = list.removeValue(10);
    Assert.assertEquals(1, idx);
    Assert.assertEquals(3, list.size());
    Assert.assertEquals(15, list.get(1));
  }

  @Test
  public void testRemoveValueNotFound() {
    IntList list = new IntList(new int[]{1, 2, 3});
    int idx = list.removeValue(99);
    Assert.assertEquals(-1, idx);
    Assert.assertEquals(3, list.size());
  }

  @Test
  public void testMinAndMax() {
    IntList list = new IntList(new int[]{4, 1, 7, 2});
    Assert.assertEquals(1, list.min());
    Assert.assertEquals(7, list.max());
  }

  @Test
  public void testSum() {
    IntList list = new IntList(new int[]{1, 2, 3, 4});
    Assert.assertEquals(10, list.sum());
  }

  @Test
  public void testSort() {
    IntList list = new IntList(new int[]{3, 1, 4, 1, 5});
    list.sort();
    Assert.assertEquals(1, list.get(0));
    Assert.assertEquals(5, list.get(4));
  }

  @Test
  public void testSortReverse() {
    IntList list = new IntList(new int[]{3, 1, 4, 1, 5});
    list.sortReverse();
    Assert.assertEquals(5, list.get(0));
    Assert.assertEquals(1, list.get(4));
  }

  @Test
  public void testReverse() {
    IntList list = new IntList(new int[]{1, 2, 3});
    list.reverse();
    Assert.assertEquals(3, list.get(0));
    Assert.assertEquals(2, list.get(1));
    Assert.assertEquals(1, list.get(2));
  }

  @Test
  public void testCopy() {
    IntList original = new IntList(new int[]{7, 8, 9});
    IntList copy = original.copy();
    Assert.assertEquals(original.size(), copy.size());
    Assert.assertEquals(original.get(0), copy.get(0));
    copy.set(0, 99);
    Assert.assertEquals(7, original.get(0));
  }

  @Test
  public void testHasValueAndIndex() {
    IntList list = new IntList(new int[]{10, 20, 30});
    Assert.assertTrue(list.hasValue(20));
    Assert.assertFalse(list.hasValue(99));
    Assert.assertEquals(2, list.index(30));
    Assert.assertEquals(-1, list.index(99));
  }

  @Test
  public void testClear() {
    IntList list = new IntList(new int[]{1, 2, 3});
    list.clear();
    Assert.assertEquals(0, list.size());
  }

  @Test
  public void testPushAndPop() {
    IntList list = new IntList();
    list.push(42);
    Assert.assertEquals(1, list.size());
    int val = list.pop();
    Assert.assertEquals(42, val);
    Assert.assertEquals(0, list.size());
  }

  @Test(expected = RuntimeException.class)
  public void testPopEmptyThrows() {
    IntList list = new IntList();
    list.pop();
  }

  @Test(expected = ArrayIndexOutOfBoundsException.class)
  public void testGetOutOfBoundsThrows() {
    IntList list = new IntList(new int[]{1, 2});
    list.get(5);
  }
}
