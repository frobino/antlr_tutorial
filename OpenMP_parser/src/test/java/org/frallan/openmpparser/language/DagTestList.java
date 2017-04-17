package org.frallan.openmpparser.language;

import org.frallan.openmpparser.language.ast.Dag;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertEquals;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * Test for the Dag class and its subclasses (DagNode, Path).
 *
 * Tries to stimulate all methods of the class under test.
 */
public class DagTestList {

  private Dag<ListEl> fDag;
  private Collection<Dag<ListEl>.DagNode> nodesList;
  private ListEl data1 = new ListEl("data1");
  private Dag<ListEl>.DagNode dNode1;
  private ListEl data2 = new ListEl("data2");
  private Dag<ListEl>.DagNode dNode2;
  private ListEl data3 = new ListEl("data3");
  private Dag<ListEl>.DagNode dNode3;
  private ListEl data4 = new ListEl("data4");
  private Dag<ListEl>.DagNode dNode4;

  /**
   * Setup a DAG to be used for following tests
   */
  @Before
  public void setUp() {

    // Create an empty Dag
    fDag = new Dag<ListEl>();
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
    Dag<ListEl>.Path path1;
    path1 = fDag.rootPath(dNode2);
    
    assertEquals("rootPath: unexpected path: ", dNode1, path1.root());
    
    assertNull(path1.root().get().next);
    path1.root().get().setNext(data2);
    
    assertEquals(data2,path1.root().get().next);
    
  }

  /**
   * Test Dag.addEdge and removeEdge
   */
  @Test
  public void test_addEdge_removeEdge() {

    nodesList = fDag.getAllChildren(dNode4);
    assertEquals("test_addEdge_removeEdge: unexpected number of children", 1, nodesList.size());

    ListEl data5 = new ListEl("data5");
    Dag<ListEl>.DagNode dNode5 = fDag.new DagNode(data5);
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
	ListEl testData = new ListEl("testData");
    Dag<ListEl>.DagNode dNode = fDag.makeOrGet(testData);
    assertEquals("test_makeOrGet: popped unexpected DagNode from stack", "testData", dNode.get().id);

    // Check the new node is still in the stack and not duplicated
    dNode = fDag.makeOrGet(testData);
    assertEquals("test_makeOrGet: popped unexpected DagNode from stack", "testData", dNode.get().id);

    // by default should be null
    assertNull(dNode.get().getNext());
    // try to change next
    dNode.get().setNext(data1);
    
    dNode = fDag.makeOrGet(testData);
    assertEquals("test_makeOrGet: popped unexpected DagNode from stack", "testData", dNode.get().id);
    // by default should not be null
    assertNotNull(dNode.get().getNext());
    
  }

  /*
   * TESTSUITE FOR DAG.DAGNODE CLASS:
   */

  /*
   * TESTSUITE FOR DAG.PATH CLASS:
   */
  
  /*
   * HELPERS
   */
  
	private class ListEl {
		
		private String id;
		private ListEl next;
		
		public ListEl (String id){
			this.id = id;
			next = null;
		}
		
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public ListEl getNext() {
			return next;
		}
		public void setNext(ListEl next) {
			this.next = next;
		}


	}

}
