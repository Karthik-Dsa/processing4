package processing.data;

import org.junit.Assert;
import org.junit.Test;

public class StringListTest {

  @Test
  public void testDefaultConstructor() {
    StringList list = new StringList();
    Assert.assertEquals(0, list.size());
  }

  @Test
  public void testArrayConstructor() {
    StringList list = new StringList(new String[]{"a", "b", "c"});
    Assert.assertEquals(3, list.size());
    Assert.assertEquals("a", list.get(0));
    Assert.assertEquals("c", list.get(2));
  }

  @Test
  public void testAppendAndGet() {
    StringList list = new StringList();
    list.append("hello");
    list.append("world");
    Assert.assertEquals(2, list.size());
    Assert.assertEquals("hello", list.get(0));
    Assert.assertEquals("world", list.get(1));
  }

  @Test
  public void testSet() {
    StringList list = new StringList(new String[]{"a", "b", "c"});
    list.set(1, "z");
    Assert.assertEquals("z", list.get(1));
  }

  @Test
  public void testPushAndPop() {
    StringList list = new StringList();
    list.push("first");
    list.push("second");
    Assert.assertEquals("second", list.pop());
    Assert.assertEquals(1, list.size());
  }

  @Test(expected = RuntimeException.class)
  public void testPopEmptyThrows() {
    new StringList().pop();
  }

  @Test
  public void testRemoveByIndex() {
    StringList list = new StringList(new String[]{"a", "b", "c"});
    String removed = list.remove(1);
    Assert.assertEquals("b", removed);
    Assert.assertEquals(2, list.size());
    Assert.assertEquals("c", list.get(1));
  }

  @Test
  public void testRemoveValue() {
    StringList list = new StringList(new String[]{"x", "y", "z", "y"});
    int idx = list.removeValue("y");
    Assert.assertEquals(1, idx);
    Assert.assertEquals(3, list.size());
  }

  @Test
  public void testRemoveValues() {
    StringList list = new StringList(new String[]{"a", "b", "b", "c"});
    int count = list.removeValues("b");
    Assert.assertEquals(2, count);
    Assert.assertEquals(2, list.size());
  }

  @Test
  public void testAppendUnique() {
    StringList list = new StringList(new String[]{"a", "b"});
    list.appendUnique("b");
    Assert.assertEquals(2, list.size());
    list.appendUnique("c");
    Assert.assertEquals(3, list.size());
  }

  @Test
  public void testInsert() {
    StringList list = new StringList(new String[]{"a", "c"});
    list.insert(1, "b");
    Assert.assertEquals(3, list.size());
    Assert.assertEquals("b", list.get(1));
    Assert.assertEquals("c", list.get(2));
  }

  @Test
  public void testIndexAndHasValue() {
    StringList list = new StringList(new String[]{"foo", "bar", "baz"});
    Assert.assertEquals(1, list.index("bar"));
    Assert.assertEquals(-1, list.index("qux"));
    Assert.assertTrue(list.hasValue("foo"));
    Assert.assertFalse(list.hasValue("qux"));
  }

  @Test
  public void testSort() {
    StringList list = new StringList(new String[]{"banana", "apple", "cherry"});
    list.sort();
    Assert.assertEquals("apple", list.get(0));
    Assert.assertEquals("cherry", list.get(2));
  }

  @Test
  public void testSortReverse() {
    StringList list = new StringList(new String[]{"banana", "apple", "cherry"});
    list.sortReverse();
    Assert.assertEquals("cherry", list.get(0));
    Assert.assertEquals("apple", list.get(2));
  }

  @Test
  public void testReverse() {
    StringList list = new StringList(new String[]{"a", "b", "c"});
    list.reverse();
    Assert.assertEquals("c", list.get(0));
    Assert.assertEquals("a", list.get(2));
  }

  @Test
  public void testLowerAndUpper() {
    StringList list = new StringList(new String[]{"Hello", "World"});
    list.lower();
    Assert.assertEquals("hello", list.get(0));
    Assert.assertEquals("world", list.get(1));

    list.upper();
    Assert.assertEquals("HELLO", list.get(0));
  }

  @Test
  public void testCopy() {
    StringList list = new StringList(new String[]{"a", "b"});
    StringList copy = list.copy();
    Assert.assertEquals(list.size(), copy.size());
    copy.set(0, "z");
    Assert.assertEquals("a", list.get(0));
  }

  @Test
  public void testArray() {
    StringList list = new StringList(new String[]{"x", "y", "z"});
    String[] arr = list.array();
    Assert.assertEquals(3, arr.length);
    Assert.assertEquals("x", arr[0]);
  }

  @Test
  public void testClear() {
    StringList list = new StringList(new String[]{"a", "b"});
    list.clear();
    Assert.assertEquals(0, list.size());
  }

  @Test
  public void testJoin() {
    StringList list = new StringList(new String[]{"a", "b", "c"});
    Assert.assertEquals("a,b,c", list.join(","));
    Assert.assertEquals("a b c", list.join(" "));
  }

  @Test
  public void testGetSubset() {
    StringList list = new StringList(new String[]{"a", "b", "c", "d"});
    StringList sub = list.getSubset(1, 2);
    Assert.assertEquals(2, sub.size());
    Assert.assertEquals("b", sub.get(0));
    Assert.assertEquals("c", sub.get(1));
  }

  @Test
  public void testGetUnique() {
    StringList list = new StringList(new String[]{"a", "b", "a", "c", "b"});
    String[] unique = list.getUnique();
    Assert.assertEquals(3, unique.length);
  }

  @Test
  public void testReplaceValue() {
    StringList list = new StringList(new String[]{"a", "b", "b", "c"});
    int count = list.replaceValues("b", "z");
    Assert.assertEquals(2, count);
    Assert.assertEquals("z", list.get(1));
    Assert.assertEquals("z", list.get(2));
  }

  @Test
  public void testIterator() {
    StringList list = new StringList(new String[]{"x", "y", "z"});
    StringBuilder sb = new StringBuilder();
    for (String s : list) {
      sb.append(s);
    }
    Assert.assertEquals("xyz", sb.toString());
  }
}
