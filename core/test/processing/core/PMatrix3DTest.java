package processing.core;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PMatrix3DTest {

  private PMatrix3D m;

  @Before
  public void setUp() {
    m = new PMatrix3D();
  }

  @Test
  public void testIdentity() {
    assertEquals(1, m.m00, 0.0001f);
    assertEquals(0, m.m01, 0.0001f);
    assertEquals(0, m.m02, 0.0001f);
    assertEquals(0, m.m03, 0.0001f);
    assertEquals(0, m.m10, 0.0001f);
    assertEquals(1, m.m11, 0.0001f);
    assertEquals(0, m.m12, 0.0001f);
    assertEquals(0, m.m13, 0.0001f);
    assertEquals(0, m.m20, 0.0001f);
    assertEquals(0, m.m21, 0.0001f);
    assertEquals(1, m.m22, 0.0001f);
    assertEquals(0, m.m23, 0.0001f);
    assertEquals(0, m.m30, 0.0001f);
    assertEquals(0, m.m31, 0.0001f);
    assertEquals(0, m.m32, 0.0001f);
    assertEquals(1, m.m33, 0.0001f);
  }

  @Test
  public void testTranslate3D() {
    m.translate(3, 5, 7);
    assertEquals(3, m.m03, 0.0001f);
    assertEquals(5, m.m13, 0.0001f);
    assertEquals(7, m.m23, 0.0001f);
  }

  @Test
  public void testTranslate2D() {
    m.translate(4, 6);
    assertEquals(4, m.m03, 0.0001f);
    assertEquals(6, m.m13, 0.0001f);
    assertEquals(0, m.m23, 0.0001f);
  }

  @Test
  public void testRotateZ() {
    m.rotateZ(PConstants.HALF_PI);
    assertEquals(0,  m.m00, 0.0001f);
    assertEquals(-1, m.m01, 0.0001f);
    assertEquals(1,  m.m10, 0.0001f);
    assertEquals(0,  m.m11, 0.0001f);
    assertEquals(1,  m.m22, 0.0001f);
  }

  @Test
  public void testRotateX() {
    m.rotateX(PConstants.HALF_PI);
    assertEquals(1,  m.m00, 0.0001f);
    assertEquals(0,  m.m11, 0.0001f);
    assertEquals(-1, m.m12, 0.0001f);
    assertEquals(1,  m.m21, 0.0001f);
    assertEquals(0,  m.m22, 0.0001f);
  }

  @Test
  public void testRotateY() {
    m.rotateY(PConstants.HALF_PI);
    assertEquals(0,  m.m00, 0.0001f);
    assertEquals(1,  m.m02, 0.0001f);
    assertEquals(1,  m.m11, 0.0001f);
    assertEquals(-1, m.m20, 0.0001f);
    assertEquals(0,  m.m22, 0.0001f);
  }

  @Test
  public void testScale() {
    m.scale(2, 3, 4);
    assertEquals(2, m.m00, 0.0001f);
    assertEquals(3, m.m11, 0.0001f);
    assertEquals(4, m.m22, 0.0001f);
    assertEquals(1, m.m33, 0.0001f);
  }

  @Test
  public void testScaleUniform() {
    m.scale(5);
    assertEquals(5, m.m00, 0.0001f);
    assertEquals(5, m.m11, 0.0001f);
    assertEquals(5, m.m22, 0.0001f);
  }

  @Test
  public void testApply() {
    PMatrix3D m2 = new PMatrix3D(1, 2, 3, 4,
                                  5, 6, 7, 8,
                                  9, 10, 11, 12,
                                  13, 14, 15, 16);
    m.apply(m2);
    assertEquals(m2.m00, m.m00, 0.0001f);
    assertEquals(m2.m11, m.m11, 0.0001f);
    assertEquals(m2.m22, m.m22, 0.0001f);
    assertEquals(m2.m33, m.m33, 0.0001f);
  }

  @Test
  public void testMultPVector() {
    PVector src = new PVector(1, 2, 3);
    PVector result = m.mult(src, null);
    assertEquals(1, result.x, 0.0001f);
    assertEquals(2, result.y, 0.0001f);
    assertEquals(3, result.z, 0.0001f);
  }

  @Test
  public void testMultPVectorWithTranslation() {
    m.translate(10, 20, 30);
    PVector src = new PVector(1, 1, 1);
    PVector result = m.mult(src, null);
    assertEquals(11, result.x, 0.0001f);
    assertEquals(21, result.y, 0.0001f);
    assertEquals(31, result.z, 0.0001f);
  }

  @Test
  public void testDeterminantAndInvert() {
    m.set(2, 0, 0, 0,
          0, 3, 0, 0,
          0, 0, 4, 0,
          0, 0, 0, 1);
    float det = m.determinant();
    assertEquals(24, det, 0.0001f);

    boolean invertible = m.invert();
    assertTrue("Diagonal matrix should be invertible", invertible);
    assertEquals(0.5f, m.m00, 0.0001f);
    assertEquals(1f/3f, m.m11, 0.0001f);
    assertEquals(0.25f, m.m22, 0.0001f);
  }

  @Test
  public void testTranspose() {
    m.set(1, 2, 3, 4,
          5, 6, 7, 8,
          9, 10, 11, 12,
          13, 14, 15, 16);
    m.transpose();
    assertEquals(1,  m.m00, 0.0001f);
    assertEquals(5,  m.m01, 0.0001f);
    assertEquals(9,  m.m02, 0.0001f);
    assertEquals(13, m.m03, 0.0001f);
    assertEquals(2,  m.m10, 0.0001f);
    assertEquals(6,  m.m11, 0.0001f);
  }

  @Test
  public void testGet() {
    PMatrix3D copy = m.get();
    assertEquals(m.m00, copy.m00, 0.0001f);
    assertEquals(m.m33, copy.m33, 0.0001f);
    assertNotSame(m, copy);
  }

  @Test
  public void testReset() {
    m.translate(5, 5, 5);
    m.reset();
    assertEquals(1, m.m00, 0.0001f);
    assertEquals(0, m.m03, 0.0001f);
    assertEquals(0, m.m13, 0.0001f);
  }
}
