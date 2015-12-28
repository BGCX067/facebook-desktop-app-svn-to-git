package de.lebk.facebook;


import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ListUtilTest {

	private List<simplePojo> list;
	private simplePojo simplePojo4;

	@Before
	public void setUp() throws Exception {
		simplePojo simplePojo = new simplePojo("eins");
		simplePojo simplePojo2 = new simplePojo("zwei");
		simplePojo simplePojo3 = new simplePojo("drei");
		simplePojo4 = new simplePojo("vier");
		simplePojo simplePojo5 = new simplePojo("lang");
		simplePojo simplePojo6 = new simplePojo("weilig");
		list = new ArrayList<>();
		list.add(simplePojo);
		list.add(simplePojo2);
		list.add(simplePojo3);
		list.add(simplePojo4);
		list.add(simplePojo5);
		list.add(simplePojo6);
		
	
	}

	@Test
	public void testFindListEntry() throws Exception {
		assertEquals(simplePojo4.getName(), ListUtil.findListEntry(list, "name", "vier").getName());
	}
	
	class simplePojo{
		private String name;

		public simplePojo(String name) {
			super();
			this.name = name;
		}

		public String getName() {
			return name;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getOuterType().hashCode();
			result = prime * result + ((name == null) ? 0 : name.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			simplePojo other = (simplePojo) obj;
			if (!getOuterType().equals(other.getOuterType()))
				return false;
			if (name == null) {
				if (other.name != null)
					return false;
			} else if (!name.equals(other.name))
				return false;
			return true;
		}

		private ListUtilTest getOuterType() {
			return ListUtilTest.this;
		}
		
	}
}
