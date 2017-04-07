package org.frallan.openmpparser.language;

import org.frallan.openmpparser.language.ast.Dag;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

import java.util.Collection;

/**
 * Test for the Dag class and its subclasses (DagNode, Path).
 *
 * Tries to stimulate all methods of the class under test.
 */
public class DagTest {

  private Dag<Integer> fDag;
  private Collection<Dag<Integer>.DagNode> nodesList;
  private Integer data1 = 1;
  private Dag<Integer>.DagNode dNode1;
  private Integer data2 = 2;
  private Dag<Integer>.DagNode dNode2;
  private Integer data3 = 3;
  private Dag<Integer>.DagNode dNode3;
  private Integer data4 = 4;
  private Dag<Integer>.DagNode dNode4;

  /**
   * Setup a DAG to be used for following tests
   */
  @Before
  public void setUp() {

    // Create an empty Dag
    fDag = new Dag<Integer>();
    assertNotNull(fDag);

    // Fill the Dag with data:
    // 1] create nodes (NOTE)
    dNode1 = fDag.new DagNode(data1);
    dNode2 = fDag.new DagNode(data2);
    dNode3 = fDag.new DagNode(data3);
    dNode4 = fDag.new DagNode(data4);
    // 2] connect nodes
    fDag.addEdge(dNode1, dNode2);
    fDag.addEdge(dNode1, dNode3);
    fDag.addEdge(dNode2, dNode4);
    fDag.addEdge(dNode3, dNode4);

    nodesList = fDag.getAllChildren(dNode1);
    assertEquals("setUp: unexpected size od Dag", 4, nodesList.size());

  }

  /*
   * TESTSUITE FOR DAG CLASS:
   */

  @Test
  public void test_rootPath() {
    Dag<Integer>.Path path1;
    path1 = fDag.rootPath(dNode2);
    
    assertEquals("rootPath: unexpected path: ", "{{ 2,1 }}", path1.toString());
    
  }

  /**
   * Test Dag.addEdge and removeEdge
   */
  @Test
  public void test_addEdge_removeEdge() {

    nodesList = fDag.getAllChildren(dNode4);
    assertEquals("test_addEdge_removeEdge: unexpected number of children", 1, nodesList.size());

    Integer data5 = 5;
    Dag<Integer>.DagNode dNode5 = fDag.new DagNode(data5);
    fDag.addEdge(dNode4, dNode5);

    nodesList = fDag.getAllChildren(dNode4);
    assertEquals("test_addEdge_removeEdge: unexpected number of children", 2, nodesList.size());

    fDag.removeEdge(dNode4, dNode5);

    nodesList = fDag.getAllChildren(dNode4);
    assertEquals("test_addEdge_removeEdge: unexpected number of children", 1, nodesList.size());

  }

  /**
   * Test Dag.getAllChildren
   */
  @Test
  public void test_getAllchildren() {

    nodesList = fDag.getAllChildren(dNode1);
    assertEquals("test_getAllchildren: unexpected size of Dag", 4, nodesList.size());

  }

  /**
   * Test Dag.makeOrGet
   * 
   * NOTE: the makeOrGet method associate a node with a specific key provided by the user. It creates new Nodes of the
   * key type for each time this method is called. It can be considered a bit as a "stack" where nodes are stored.
   */
  @Test
  public void test_makeOrGet() {

    // Create new node in stack
    Dag<Integer>.DagNode dNode = fDag.makeOrGet(1);
    assertEquals("test_makeOrGet: popped unexpected DagNode from stack", 1, dNode.get().intValue());

    // Check the new node is still in the stack and not duplicated
    dNode = fDag.makeOrGet(1);
    assertEquals("test_makeOrGet: popped unexpected DagNode from stack", 1, dNode.get().intValue());

  }

  /*
   * TESTSUITE FOR DAG.DAGNODE CLASS:
   */

  /*
   * TESTSUITE FOR DAG.PATH CLASS:
   */

}
