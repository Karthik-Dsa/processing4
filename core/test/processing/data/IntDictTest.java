package processing.data;

import org.junit.Assert;
import org.junit.Test;

public class IntDictTest {

  @Test
  public void testDefaultConstructor() {
    IntDict dict = new IntDict();
    Assert.assertEquals(0, dict.size());
  }

  @Test
  public void testSetAndGet() {
    IntDict dict = new IntDict();
    dict.set("a", 1);
    dict.set("b", 2);
    Assert.assertEquals(2, dict.size());
    Assert.assertEquals(1, dict.get("a"));
    Assert.assertEquals(2, dict.get("b"));
  }

  @Test
  public void testGetWithDefault() {
    IntDict dict = new IntDict();
    dict.set("x", 10);
    Assert.assertEquals(10, dict.get("x", 99));
    Assert.assertEquals(99, dict.get("missing", 99));
  }

  @Test
  public void testHasKey() {
    IntDict dict = new IntDict();
    dict.set("key", 42);
    Assert.assertTrue(dict.hasKey("key"));
    Assert.assertFalse(dict.hasKey("other"));
  }

  @Test
  public void testIncrement() {
    IntDict dict = new IntDict();
    dict.set("count", 5);
    dict.increment("count");
    Assert.assertEquals(6, dict.get("count"));

    dict.increment("new");
    Assert.assertEquals(1, dict.get("new"));
  }

  @Test
  public void testAdd() {
    IntDict dict = new IntDict();
    dict.set("val", 10);
    dict.add("val", 5);
    Assert.assertEquals(15, dict.get("val"));
  }

  @Test
  public void testSub() {
    IntDict dict = new IntDict();
    dict.set("val", 10);
    dict.sub("val", 3);
    Assert.assertEquals(7, dict.get("val"));
  }

  @Test
  public void testMult() {
    IntDict dict = new IntDict();
    dict.set("val", 4);
    dict.mult("val", 3);
    Assert.assertEquals(12, dict.get("val"));
  }

  @Test
  public void testDiv() {
    IntDict dict = new IntDict();
    dict.set("val", 12);
    dict.div("val", 4);
    Assert.assertEquals(3, dict.get("val"));
  }

  @Test
  public void testRemove() {
    IntDict dict = new IntDict();
    dict.set("a", 1);
    dict.set("b", 2);
    dict.remove("a");
    Assert.assertEquals(1, dict.size());
    Assert.assertFalse(dict.hasKey("a"));
  }

  @Test
  public void testMinAndMax() {
    IntDict dict = new IntDict();
    dict.set("low", 1);
    dict.set("mid", 5);
    dict.set("high", 10);
    Assert.assertEquals(1, dict.minValue());
    Assert.assertEquals(10, dict.maxValue());
    Assert.assertEquals("low", dict.minKey());
    Assert.assertEquals("high", dict.maxKey());
  }

  @Test
  public void testSum() {
    IntDict dict = new IntDict();
    dict.set("a", 3);
    dict.set("b", 7);
    Assert.assertEquals(10, dict.sum());
    Assert.assertEquals(10L, dict.sumLong());
  }

  @Test
  public void testKeyArray() {
    IntDict dict = new IntDict(new String[]{"a", "b"}, new int[]{1, 2});
    String[] keys = dict.keyArray();
    Assert.assertEquals(2, keys.length);
  }

  @Test
  public void testValueArray() {
    IntDict dict = new IntDict(new String[]{"a", "b"}, new int[]{10, 20});
    int[] vals = dict.valueArray();
    Assert.assertEquals(2, vals.length);
  }

  @Test
  public void testClear() {
    IntDict dict = new IntDict();
    dict.set("a", 1);
    dict.clear();
    Assert.assertEquals(0, dict.size());
  }

  @Test
  public void testSortKeys() {
    IntDict dict = new IntDict();
    dict.set("banana", 2);
    dict.set("apple", 1);
    dict.set("cherry", 3);
    dict.sortKeys();
    Assert.assertEquals("apple", dict.key(0));
    Assert.assertEquals("cherry", dict.key(2));
  }

  @Test
  public void testIndex() {
    IntDict dict = new IntDict();
    dict.set("a", 1);
    dict.set("b", 2);
    Assert.assertTrue(dict.index("a") >= 0);
    Assert.assertEquals(-1, dict.index("missing"));
  }

  @Test
  public void testEntryIterable() {
    IntDict dict = new IntDict();
    dict.set("x", 10);
    dict.set("y", 20);
    int total = 0;
    for (IntDict.Entry e : dict.entries()) {
      total += e.value;
    }
    Assert.assertEquals(30, total);
  }
}
