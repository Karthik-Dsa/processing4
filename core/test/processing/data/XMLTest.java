package processing.data;

import org.junit.Assert;
import org.junit.Test;

public class XMLTest {

  private static final String SIMPLE_XML =
    "<root attr1=\"hello\" attr2=\"42\">" +
    "<child name=\"first\">content1</child>" +
    "<child name=\"second\">content2</child>" +
    "</root>";

  @Test
  public void testParseAndGetName() throws Exception {
    XML xml = XML.parse(SIMPLE_XML);
    Assert.assertEquals("root", xml.getName());
  }

  @Test
  public void testGetChildCount() throws Exception {
    XML xml = XML.parse(SIMPLE_XML);
    Assert.assertEquals(2, xml.getChildCount());
  }

  @Test
  public void testHasChildren() throws Exception {
    XML xml = XML.parse(SIMPLE_XML);
    Assert.assertTrue(xml.hasChildren());
    XML leaf = XML.parse("<leaf/>");
    Assert.assertFalse(leaf.hasChildren());
  }

  @Test
  public void testGetChildByIndex() throws Exception {
    XML xml = XML.parse(SIMPLE_XML);
    XML child = xml.getChild(0);
    Assert.assertEquals("child", child.getName());
  }

  @Test
  public void testGetChildByName() throws Exception {
    XML xml = XML.parse(SIMPLE_XML);
    XML child = xml.getChild("child");
    Assert.assertNotNull(child);
    Assert.assertEquals("child", child.getName());
  }

  @Test
  public void testGetChildren() throws Exception {
    XML xml = XML.parse(SIMPLE_XML);
    XML[] children = xml.getChildren();
    Assert.assertEquals(2, children.length);
  }

  @Test
  public void testGetChildrenByName() throws Exception {
    XML xml = XML.parse(SIMPLE_XML);
    XML[] children = xml.getChildren("child");
    Assert.assertEquals(2, children.length);
  }

  @Test
  public void testListChildren() throws Exception {
    XML xml = XML.parse(SIMPLE_XML);
    String[] names = xml.listChildren();
    Assert.assertEquals(2, names.length);
    Assert.assertEquals("child", names[0]);
  }

  @Test
  public void testGetStringAttribute() throws Exception {
    XML xml = XML.parse(SIMPLE_XML);
    Assert.assertEquals("hello", xml.getString("attr1"));
    Assert.assertEquals("default", xml.getString("missing", "default"));
  }

  @Test
  public void testGetIntAttribute() throws Exception {
    XML xml = XML.parse(SIMPLE_XML);
    Assert.assertEquals(42, xml.getInt("attr2"));
    Assert.assertEquals(99, xml.getInt("missing", 99));
  }

  @Test
  public void testGetFloatAttribute() throws Exception {
    XML xml = XML.parse("<item value=\"3.14\"/>");
    Assert.assertEquals(3.14f, xml.getFloat("value"), 0.001f);
    Assert.assertEquals(1.0f, xml.getFloat("missing", 1.0f), 0.001f);
  }

  @Test
  public void testSetStringAttribute() throws Exception {
    XML xml = XML.parse("<item/>");
    xml.setString("key", "value");
    Assert.assertEquals("value", xml.getString("key"));
  }

  @Test
  public void testSetIntAttribute() throws Exception {
    XML xml = XML.parse("<item/>");
    xml.setInt("count", 7);
    Assert.assertEquals(7, xml.getInt("count"));
  }

  @Test
  public void testGetContent() throws Exception {
    XML xml = XML.parse("<note>hello world</note>");
    Assert.assertEquals("hello world", xml.getContent());
  }

  @Test
  public void testGetIntContent() throws Exception {
    XML xml = XML.parse("<value>123</value>");
    Assert.assertEquals(123, xml.getIntContent());
    Assert.assertEquals(123, xml.getIntContent(0));
  }

  @Test
  public void testGetFloatContent() throws Exception {
    XML xml = XML.parse("<value>2.5</value>");
    Assert.assertEquals(2.5f, xml.getFloatContent(), 0.001f);
  }

  @Test
  public void testAddChild() throws Exception {
    XML xml = XML.parse("<root/>");
    XML child = xml.addChild("item");
    Assert.assertEquals(1, xml.getChildCount());
    Assert.assertEquals("item", child.getName());
  }

  @Test
  public void testRemoveChild() throws Exception {
    XML xml = XML.parse("<root><a/><b/></root>");
    XML childB = xml.getChild("b");
    xml.removeChild(childB);
    Assert.assertEquals(1, xml.getChildCount());
    Assert.assertEquals("a", xml.getChild(0).getName());
  }

  @Test
  public void testGetParent() throws Exception {
    XML xml = XML.parse(SIMPLE_XML);
    XML child = xml.getChild(0);
    Assert.assertNotNull(child.getParent());
    Assert.assertEquals("root", child.getParent().getName());
  }

  @Test
  public void testHasAttribute() throws Exception {
    XML xml = XML.parse("<item foo=\"bar\"/>");
    Assert.assertTrue(xml.hasAttribute("foo"));
    Assert.assertFalse(xml.hasAttribute("baz"));
  }

  @Test
  public void testGetAttributeCount() throws Exception {
    XML xml = XML.parse("<item a=\"1\" b=\"2\" c=\"3\"/>");
    Assert.assertEquals(3, xml.getAttributeCount());
  }

  @Test
  public void testListAttributes() throws Exception {
    XML xml = XML.parse("<item x=\"1\" y=\"2\"/>");
    String[] attrs = xml.listAttributes();
    Assert.assertEquals(2, attrs.length);
  }

  @Test
  public void testSetName() throws Exception {
    XML xml = XML.parse("<old/>");
    xml.setName("new");
    Assert.assertEquals("new", xml.getName());
  }

  @Test
  public void testConstructorWithName() {
    XML xml = new XML("myElement");
    Assert.assertEquals("myElement", xml.getName());
    Assert.assertEquals(0, xml.getChildCount());
  }

  @Test
  public void testLongAttribute() throws Exception {
    XML xml = XML.parse("<item value=\"9876543210\"/>");
    Assert.assertEquals(9876543210L, xml.getLong("value", 0L));
  }

  @Test
  public void testDoubleAttribute() throws Exception {
    XML xml = XML.parse("<item value=\"1.23456789\"/>");
    Assert.assertEquals(1.23456789, xml.getDouble("value"), 0.00000001);
    Assert.assertEquals(0.5, xml.getDouble("missing", 0.5), 0.001);
  }
}
