package com.merribiAdil.ArrayList;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SimpleArrayListUnitTest {
	
	SimpleArrayList arrayList1, arrayList2;

	public static final String ITEM_1 = "ITEM_1", ITEM_2 = "ITEM_2", ITEM_3 = "ITEM_3";

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		arrayList1 = new MyArrayList(); //Construct a Simple Array List here
		
		arrayList2 = new MyArrayList();//Construct a Simple Array List here
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	public void afterAdd(SimpleArrayList arrayList) {
		arrayList.add(new String(ITEM_1));

		arrayList.add(new String(ITEM_2));
	}

	
	public void afterRemove(SimpleArrayList arrayList) {
		arrayList.add(new String(ITEM_1));

		arrayList.add(new String(ITEM_2));

		arrayList.remove(new String(ITEM_2));
	}

	
	public void afterRemoveToEmpty(SimpleArrayList arrayList) {
		arrayList.add(new String(ITEM_1));

		arrayList.add(new String(ITEM_2));

		arrayList.remove(new String(ITEM_2));

		arrayList.remove(new String(ITEM_1));
	}

	@Test
	public void sizeAfterCreation() {
		assertTrue(arrayList1.size() == 0);
	}

	@Test
	public void sizeAfterAdd() {
		afterAdd(arrayList1);
		assertTrue(arrayList1.size() == 2);
	}

	@Test
	public void sizeAfterRemove() {
		afterRemove(arrayList1);

		assertTrue(arrayList1.size() == 1);
	}

	@Test
	public void sizeAfterRemoveToEmpty() {
		afterRemoveToEmpty(arrayList1);

		assertTrue(arrayList1.size() == 0);
	}

	@Test
	public void isEmptyAfterCreation() {
		assertTrue(arrayList1.isEmpty());
	}

	@Test
	public void isEmptyAfterAdd() {
		afterAdd(arrayList1);

		assertFalse(arrayList1.isEmpty());
	}

	@Test
	public void isEmptyAfterRemove() {
		afterRemove(arrayList1);

		assertFalse(arrayList1.isEmpty());
	}

	@Test
	public void isEmptyAfterRemoveToEmpty() {
		afterRemoveToEmpty(arrayList1);

		assertTrue(arrayList1.isEmpty());
	}

	@Test
	public void containsCreation() {
		assertFalse(arrayList1.contains(new String(ITEM_1)));

		assertFalse(arrayList1.contains(new String(ITEM_2)));
	}

	@Test
	public void containsAfterAdd() {
		afterAdd(arrayList1);

		assertTrue(arrayList1.contains(new String(ITEM_1)));

		assertTrue(arrayList1.contains(new String(ITEM_2)));
	}

	@Test
	public void containsAfterRemove() {
		afterRemove(arrayList1);

		assertTrue(arrayList1.contains(new String(ITEM_1)));

		assertFalse(arrayList1.contains(new String(ITEM_2)));
	}

	@Test
	public void containsAfterRemoveToEmpty() {
		afterRemoveToEmpty(arrayList1);

		assertFalse(arrayList1.contains(new String(ITEM_1)));

		assertFalse(arrayList1.contains(new String(ITEM_2)));
	}
	
	@Test
	public void toArrayCreation() {
		assertArrayEquals(arrayList1.toArray(), new Object[]{});
	}

	@Test
	public void toArrayAfterAdd() {
		afterAdd(arrayList1);
		
		assertArrayEquals(arrayList1.toArray(), new Object[]{new String(ITEM_1), new String(ITEM_2)});
	}

	@Test
	public void toArrayAfterRemove() {
		afterRemove(arrayList1);
		
		assertArrayEquals(arrayList1.toArray(), new Object[]{new String(ITEM_1)});
	}

	@Test
	public void toArrayAfterRemoveToEmpty() {
		afterRemoveToEmpty(arrayList1);

		assertArrayEquals(arrayList1.toArray(), new Object[]{});
	}
	
	@Test
	public void clearCreation() {
		arrayList1.clear();
		assertTrue(arrayList1.size() == 0);
	}

	@Test
	void clearAfterAdd() {
		afterAdd(arrayList1);
		
		arrayList1.clear();
		
		assertTrue(arrayList1.size() == 0);
	}
	
	@Test
	public void getCreation() {
		try {
			assertNull(arrayList1.get(0));
		}catch (Exception e) {
		}
	}

	@Test
	public void getAfterAdd() {
		afterAdd(arrayList1);
		
		assertEquals(arrayList1.get(0), new String(ITEM_1));
	}

	@Test
	public void getAfterRemove() {
		afterRemove(arrayList1);
		
		assertEquals(arrayList1.get(0), new String(ITEM_1));
	}
	
	@Test
	public void addAtCreation() {
		arrayList1.add(0, new String(ITEM_3));
		
		assertEquals(arrayList1.indexOf(new String(ITEM_3)), 0);
	}

	@Test
	public void addAtAfterAdd() {
		afterAdd(arrayList1);
		
		arrayList1.add(0, new String(ITEM_3));
		
		assertEquals(arrayList1.indexOf(new String(ITEM_3)), 0);
	}

	@Test
	public void addAtAfterRemove() {
		afterRemove(arrayList1);
		
		arrayList1.add(0, new String(ITEM_3));
		
		assertEquals(arrayList1.indexOf(new String(ITEM_3)), 0);
	}
	
	@Test
	public void addAtAfterRemoveToEmpty() {
		arrayList1.add(0, new String(ITEM_3));
		
		afterRemoveToEmpty(arrayList1);
		
		assertEquals(arrayList1.indexOf(new String(ITEM_3)), 0);
	}

	@Test
	public void indexOfAfterAdd() {
		afterAdd(arrayList1);
				
		assertEquals(arrayList1.indexOf(new String(ITEM_1)), 0);
	}

	@Test
	public void indexOfAfterRemove() {
		afterRemove(arrayList1);
				
		assertEquals(arrayList1.indexOf(ITEM_1), 0);
	}
	
	@Test
	public void indexOfRemoveToEmpty() {
		afterRemoveToEmpty(arrayList1);
		
		assertEquals(arrayList1.indexOf(new String(ITEM_3)), -1);
	}
	
	@Test
	public void lastIndexOfAfterAdd() {
		afterAdd(arrayList1);
		afterAdd(arrayList1);
				
		assertEquals(arrayList1.lastIndexOf(new String(ITEM_1)), 2);
	}

	@Test
	public void lastIndexOfAfterRemove() {
		arrayList1.add(new String(ITEM_1));
		arrayList1.add(new String(ITEM_1));
				
		assertEquals(arrayList1.lastIndexOf(ITEM_1), 1);
	}
	
	@Test
	public void lastIndexOfRemoveToEmpty() {
		afterRemoveToEmpty(arrayList1);
		
		assertEquals(arrayList1.lastIndexOf(new String(ITEM_3)), -1);
	}
	
	@Test
	public void subStringAfterAdd1() {
		afterAdd(arrayList1);
		
		assertTrue(arrayList1.subList(0, 1).contains(ITEM_1));
	}
	
	@Test
	public void subStringAfterAdd2() {
		afterAdd(arrayList1);
		
		assertTrue(arrayList1.subList(0, 2).contains(ITEM_1));
		assertTrue(arrayList1.subList(0, 2).contains(ITEM_2));
	}
	
	@Test
	public void equalsCreation() {		
		assertTrue(arrayList1.equals(arrayList2));
	}

	@Test
	public void equalsAfterAdd() {
		afterAdd(arrayList1);
		
		afterAdd(arrayList2);
		
		assertTrue(arrayList1.equals(arrayList2));
	}
	
	@Test
	public void equalsAfterRemove() {
		afterRemove(arrayList1);
		
		afterRemove(arrayList2);
				
		assertTrue(arrayList1.equals(arrayList2));
	}
	
	@Test
	public void equalsRemoveToEmpty() {
		afterRemoveToEmpty(arrayList1);
		
		afterRemoveToEmpty(arrayList2);
		
		assertTrue(arrayList1.equals(arrayList2));
	}
	
	/********************
	 * Array List Tests *
	 ********************/

	@Test
	public void reatinAllAfterAdd1() {
		afterAdd(arrayList1);
		
		arrayList2.add(ITEM_2);
		
		arrayList1.retainAll(arrayList2);
		
		assertFalse(arrayList1.contains(ITEM_1));
		
		assertTrue(arrayList1.contains(ITEM_2));
	}

	@Test
	public void reatinAllAfterAdd2() {
		afterAdd(arrayList1);
		
		arrayList2.add(ITEM_3);
		
		arrayList1.retainAll(arrayList2);
		
		assertFalse(arrayList1.contains(ITEM_1));
		
		assertFalse(arrayList1.contains(ITEM_2));
	}
	
	@Test
	public void reatinAllAfterAdd3() {
		afterAdd(arrayList1);
		
		afterAdd(arrayList1);
		
		arrayList2.add(ITEM_3);
		
		arrayList1.retainAll(arrayList2);
		
		assertFalse(arrayList1.contains(ITEM_1));
		
		assertFalse(arrayList1.contains(ITEM_2));
	}
	
	@Test
	public void reatinAllAfterAdd4() {
		afterAdd(arrayList1);
		
		afterAdd(arrayList1);
		
		arrayList2.add(ITEM_1);
		
		arrayList1.retainAll(arrayList2);
		
		assertTrue(arrayList1.size() == 2);
		
		assertTrue(arrayList1.contains(ITEM_1));
	}
	
	@Test
	public void removeAllAfterAdd1() {
		afterAdd(arrayList1);
		
		arrayList2.add(ITEM_2);
		
		arrayList1.removeAll(arrayList2);
		
		assertTrue(arrayList1.contains(ITEM_1));
		
		assertFalse(arrayList1.contains(ITEM_2));
	}

	@Test
	public void removeAllAfterAdd2() {
		afterAdd(arrayList1);
		
		arrayList2.add(ITEM_3);
		
		arrayList1.removeAll(arrayList2);
		
		assertTrue(arrayList1.contains(ITEM_1));
		
		assertTrue(arrayList1.contains(ITEM_2));
	}
	
	@Test
	public void removeAllAfterAdd3() {
		afterAdd(arrayList1);
		
		afterAdd(arrayList1);
		
		arrayList2.add(ITEM_3);
		
		arrayList1.removeAll(arrayList2);
		
		assertTrue(arrayList1.contains(ITEM_1));
		
		assertTrue(arrayList1.contains(ITEM_2));
	}
	
	@Test
	public void removeAllAfterAdd4() {
		afterAdd(arrayList1);
		
		afterAdd(arrayList1);
		
		arrayList2.add(ITEM_1);
		
		arrayList1.removeAll(arrayList2);
		
		assertTrue(arrayList1.size() == 2);
		
		assertTrue(arrayList1.contains(ITEM_2));
	}
	
	@Test
	public void containsAll1() {
		afterAdd(arrayList1);
		
		afterAdd(arrayList2);
						
		assertTrue(arrayList1.containsAll(arrayList2));
	}
	
	@Test
	public void containsAll2() {
		afterAdd(arrayList1);
		
		arrayList2.add(ITEM_3);
						
		assertFalse(arrayList1.containsAll(arrayList2));
	}
	
	@Test
	public void addAllAfterAdd() {
		afterAdd(arrayList1);
		
		afterAdd(arrayList2);
		
		arrayList1.addAll(arrayList2);
				
		assertTrue(arrayList1.size() == 4);
	}
	
	@Test
	public void addAllAtAfterAdd() {
		afterAdd(arrayList1);
		
		afterAdd(arrayList2);
		
		arrayList1.addAll(2, arrayList2);
				
		assertTrue(arrayList1.size() == 4);
	}

}
