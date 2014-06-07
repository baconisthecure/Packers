package com.FourHillsLab.wardrobe;

import static org.junit.Assert.*;

import org.junit.Test;

import com.FourHillsLab.userManagement.User;
import com.FourHillsLab.utilities.FormalityType;
import com.FourHillsLab.wardrobe.ClothingItem;
import com.FourHillsLab.wardrobe.ClothingType;

public class ClothingItemTest extends ClothingItem {

	public ClothingItemTest(String m_name, String m_description,
			ClothingType m_type, FormalityType m_clothingType,
			Boolean m_activeItem) {
		super(m_type,  m_clothingType, m_description, m_activeItem);
		// TODO Auto-generated constructor stub
	}

	@Test
	public void testEquals() {
		fail("Not yet implemented");
	}

	@Test
	public void testToString() {
		fail("Not yet implemented");
	}

}
