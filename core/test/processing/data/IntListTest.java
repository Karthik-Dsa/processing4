package processing.data;

import org.junit.Assert;
import org.junit.Test;

public class IntListTest {

  @Test
  public void testDefaultConstructor() {
    IntList list = new IntList();
    Assert.assertEquals(0, list.size());
  }

  @Test
  public void testArrayConstructor() {
    IntList list = new IntList(new int[]{1, 2, 3});
    Assert.assertEquals(3, list.size());
    Assert.assertEquals(1, list.get(0));
    Assert.assertEquals(2, list.get(1));
    Assert.assertEquals(3, list.get(2));
  }

  @Test
  public void testFromRange() {
    IntList list = IntList.fromRange(5);
    Assert.assertEquals(5, list.size());
    for (int i = 0; i < 5; i++) {
      Assert.assertEquals(i, list.get(i));
    }

    IntList list2 = IntList.fromRange(3, 7);
    Assert.assertEquals(4, list2.size());
    Assert.assertEquals(3, list2.get(0));
    Assert.assertEquals(6, list2.get(3));
  }

  @Test
  public void testAppendAndGet() {
    IntList list = new IntList();
    list.append(10);
    list.append(20);
    Assert.assertEquals(2, list.size());
    Assert.assertEquals(10, list.get(0));
    Assert.assertEquals(20, list.get(1));
  }

  @Test
  public void testSet() {
    IntList list = new IntList(new int[]{1, 2, 3});
    list.set(1, 99);
    Assert.assertEquals(99, list.get(1));
  }

  @Test
  public void testPushAndPop() {
    IntList list = new IntList();
    list.push(5);
    list.push(10);
    Assert.assertEquals(10, list.pop());
    Assert.assertEquals(1, list.size());
  }

  @Test(expected = RuntimeException.class)
  public void testPopEmptyThrows() {
    new IntList().pop();
  }

  @Test
  public void testRemoveByIndex() {
    IntList list = new IntList(new int[]{1, 2, 3});
    int removed = list.remove(1);
    Assert.assertEquals(2, removed);
    Assert.assertEquals(2, list.size());
    Assert.assertEquals(3, list.get(1));
  }

  @Test
  public void testRemoveValue() {
    IntList list = new IntList(new int[]{10, 20, 30, 20});
    int idx = list.removeValue(20);
    Assert.assertEquals(1, idx);
    Assert.assertEquals(3, list.size());
    Assert.assertEquals(30, list.get(1));
  }

  @Test
  public void testRemoveValues() {
    IntList list = new IntList(new int[]{1, 2, 2, 3, 2});
    int count = list.removeValues(2);
    Assert.assertEquals(3, count);
    Assert.assertEquals(2, list.size());
  }

  @Test
  public void testAppendUnique() {
    IntList list = new IntList(new int[]{1, 2, 3});
    list.appendUnique(2);
    Assert.assertEquals(3, list.size());
    list.appendUnique(4);
    Assert.assertEquals(4, list.size());
  }

  @Test
  public void testInsert() {
    IntList list = new IntList(new int[]{1, 3, 4});
    list.insert(1, 2);
    Assert.assertEquals(4, list.size());
    Assert.assertEquals(2, list.get(1));
    Assert.assertEquals(3, list.get(2));
  }

  @Test
  public void testIndexAndHasValue() {
    IntList list = new IntList(new int[]{10, 20, 30});
    Assert.assertEquals(1, list.index(20));
    Assert.assertEquals(-1, list.index(99));
    Assert.assertTrue(list.hasValue(10));
    Assert.assertFalse(list.hasValue(99));
  }

  @Test
  public void testMinAndMax() {
    IntList list = new IntList(new int[]{3, 1, 4, 1, 5, 9});
    Assert.assertEquals(1, list.min());
    Assert.assertEquals(9, list.max());
  }

  @Test
  public void testSum() {
    IntList list = new IntList(new int[]{1, 2, 3, 4});
    Assert.assertEquals(10, list.sum());
    Assert.assertEquals(10L, list.sumLong());
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
    Assert.assertEquals(1, list.get(2));
  }

  @Test
  public void testCopy() {
    IntList list = new IntList(new int[]{1, 2, 3});
    IntList copy = list.copy();
    Assert.assertEquals(list.size(), copy.size());
    copy.set(0, 99);
    Assert.assertEquals(1, list.get(0));
  }

  @Test
  public void testArray() {
    IntList list = new IntList(new int[]{5, 6, 7});
    int[] arr = list.array();
    Assert.assertEquals(3, arr.length);
    Assert.assertEquals(5, arr[0]);
  }

  @Test
  public void testClear() {
    IntList list = new IntList(new int[]{1, 2, 3});
    list.clear();
    Assert.assertEquals(0, list.size());
  }

  @Test
  public void testResize() {
    IntList list = new IntList(new int[]{1, 2, 3});
    list.resize(5);
    Assert.assertEquals(5, list.size());
    Assert.assertEquals(0, list.get(4));

    list.resize(2);
    Assert.assertEquals(2, list.size());
  }

  @Test
  public void testIncrement() {
    IntList list = new IntList(new int[]{10, 20});
    list.increment(0);
    Assert.assertEquals(11, list.get(0));
  }

  @Test
  public void testAdd() {
    IntList list = new IntList(new int[]{10, 20});
    list.add(1, 5);
    Assert.assertEquals(25, list.get(1));
  }

  @Test
  public void testSub() {
    IntList list = new IntList(new int[]{10, 20});
    list.sub(0, 3);
    Assert.assertEquals(7, list.get(0));
  }

  @Test
  public void testMult() {
    IntList list = new IntList(new int[]{3, 4});
    list.mult(0, 3);
    Assert.assertEquals(9, list.get(0));
  }

  @Test
  public void testDiv() {
    IntList list = new IntList(new int[]{12, 8});
    list.div(0, 4);
    Assert.assertEquals(3, list.get(0));
  }

  @Test
  public void testIterator() {
    IntList list = new IntList(new int[]{1, 2, 3});
    int sum = 0;
    for (int v : list) {
      sum += v;
    }
    Assert.assertEquals(6, sum);
  }
}
