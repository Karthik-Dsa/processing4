package processing.core;

import org.junit.Assert;
import org.junit.Test;

public class PAppletMathTest {

  private static final float DELTA = 0.0001f;

  // --- abs ---

  @Test
  public void testAbsFloat() {
    Assert.assertEquals(3.5f, PApplet.abs(3.5f), DELTA);
    Assert.assertEquals(3.5f, PApplet.abs(-3.5f), DELTA);
    Assert.assertEquals(0f, PApplet.abs(0f), DELTA);
  }

  @Test
  public void testAbsInt() {
    Assert.assertEquals(5, PApplet.abs(5));
    Assert.assertEquals(5, PApplet.abs(-5));
    Assert.assertEquals(0, PApplet.abs(0));
  }

  // --- sq / sqrt ---

  @Test
  public void testSq() {
    Assert.assertEquals(9f, PApplet.sq(3f), DELTA);
    Assert.assertEquals(9f, PApplet.sq(-3f), DELTA);
    Assert.assertEquals(0f, PApplet.sq(0f), DELTA);
  }

  @Test
  public void testSqrt() {
    Assert.assertEquals(3f, PApplet.sqrt(9f), DELTA);
    Assert.assertEquals(0f, PApplet.sqrt(0f), DELTA);
  }

  // --- max / min (two arguments) ---

  @Test
  public void testMaxTwoInt() {
    Assert.assertEquals(7, PApplet.max(3, 7));
    Assert.assertEquals(7, PApplet.max(7, 3));
    Assert.assertEquals(5, PApplet.max(5, 5));
  }

  @Test
  public void testMaxTwoFloat() {
    Assert.assertEquals(7f, PApplet.max(3f, 7f), DELTA);
    Assert.assertEquals(7f, PApplet.max(7f, 3f), DELTA);
  }

  @Test
  public void testMinTwoInt() {
    Assert.assertEquals(3, PApplet.min(3, 7));
    Assert.assertEquals(3, PApplet.min(7, 3));
    Assert.assertEquals(5, PApplet.min(5, 5));
  }

  @Test
  public void testMinTwoFloat() {
    Assert.assertEquals(3f, PApplet.min(3f, 7f), DELTA);
    Assert.assertEquals(3f, PApplet.min(7f, 3f), DELTA);
  }

  // --- max / min (three arguments) ---

  @Test
  public void testMaxThreeInt() {
    Assert.assertEquals(9, PApplet.max(1, 9, 5));
    Assert.assertEquals(9, PApplet.max(9, 1, 5));
    Assert.assertEquals(9, PApplet.max(5, 1, 9));
  }

  @Test
  public void testMinThreeInt() {
    Assert.assertEquals(1, PApplet.min(1, 9, 5));
    Assert.assertEquals(1, PApplet.min(9, 1, 5));
    Assert.assertEquals(1, PApplet.min(5, 9, 1));
  }

  // --- max / min (array) ---

  @Test
  public void testMaxArray() {
    Assert.assertEquals(8, PApplet.max(new int[]{3, 8, 1, 5}));
    Assert.assertEquals(8f, PApplet.max(new float[]{3f, 8f, 1f, 5f}), DELTA);
  }

  @Test
  public void testMinArray() {
    Assert.assertEquals(1, PApplet.min(new int[]{3, 8, 1, 5}));
    Assert.assertEquals(1f, PApplet.min(new float[]{3f, 8f, 1f, 5f}), DELTA);
  }

  // --- constrain ---

  @Test
  public void testConstrainFloat() {
    Assert.assertEquals(5f, PApplet.constrain(5f, 0f, 10f), DELTA);
    Assert.assertEquals(0f, PApplet.constrain(-3f, 0f, 10f), DELTA);
    Assert.assertEquals(10f, PApplet.constrain(15f, 0f, 10f), DELTA);
  }

  @Test
  public void testConstrainInt() {
    Assert.assertEquals(5, PApplet.constrain(5, 0, 10));
    Assert.assertEquals(0, PApplet.constrain(-3, 0, 10));
    Assert.assertEquals(10, PApplet.constrain(15, 0, 10));
  }

  // --- ceil / floor / round ---

  @Test
  public void testCeil() {
    Assert.assertEquals(4, PApplet.ceil(3.2f));
    Assert.assertEquals(3, PApplet.ceil(3f));
    Assert.assertEquals(-3, PApplet.ceil(-3.7f));
  }

  @Test
  public void testFloor() {
    Assert.assertEquals(3, PApplet.floor(3.9f));
    Assert.assertEquals(3, PApplet.floor(3f));
    Assert.assertEquals(-4, PApplet.floor(-3.2f));
  }

  @Test
  public void testRound() {
    Assert.assertEquals(4, PApplet.round(3.5f));
    Assert.assertEquals(3, PApplet.round(3.4f));
    Assert.assertEquals(-3, PApplet.round(-3.4f));
  }

  // --- dist ---

  @Test
  public void testDist2D() {
    Assert.assertEquals(5f, PApplet.dist(0, 0, 3, 4), DELTA);
    Assert.assertEquals(0f, PApplet.dist(1, 1, 1, 1), DELTA);
  }

  @Test
  public void testDist3D() {
    Assert.assertEquals(3f, PApplet.dist(0, 0, 0, 1, 2, 2), DELTA);
    Assert.assertEquals(0f, PApplet.dist(2, 3, 4, 2, 3, 4), DELTA);
  }

  // --- lerp ---

  @Test
  public void testLerp() {
    Assert.assertEquals(0f,  PApplet.lerp(0f, 10f, 0f), DELTA);
    Assert.assertEquals(10f, PApplet.lerp(0f, 10f, 1f), DELTA);
    Assert.assertEquals(5f,  PApplet.lerp(0f, 10f, 0.5f), DELTA);
    Assert.assertEquals(2f,  PApplet.lerp(0f, 10f, 0.2f), DELTA);
  }

  // --- norm ---

  @Test
  public void testNorm() {
    Assert.assertEquals(0.5f, PApplet.norm(5f, 0f, 10f), DELTA);
    Assert.assertEquals(0f,   PApplet.norm(0f, 0f, 10f), DELTA);
    Assert.assertEquals(1f,   PApplet.norm(10f, 0f, 10f), DELTA);
  }

  // --- map ---

  @Test
  public void testMap() {
    Assert.assertEquals(50f, PApplet.map(5f, 0f, 10f, 0f, 100f), DELTA);
    Assert.assertEquals(0f,  PApplet.map(0f, 0f, 10f, 0f, 100f), DELTA);
    Assert.assertEquals(100f, PApplet.map(10f, 0f, 10f, 0f, 100f), DELTA);
    // reversed output range
    Assert.assertEquals(50f, PApplet.map(5f, 0f, 10f, 100f, 0f), DELTA);
  }
}
