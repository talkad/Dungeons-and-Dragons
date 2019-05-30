package tests;

import org.junit.Assert;
import org.junit.Test;

import Control.GameUnit;
import Control.Monster;

public class TestGameUnit {

	@Test
	public void checkSetPosition() {
		GameUnit gu=new Monster("White Walker", 2000, 2000, 150, 50, 2, 2, 'w', 6, 1000);
		gu.setPosition(5, 10);;
		Assert.assertTrue(gu.getPosition().getX()==5 && gu.getPosition().getY()==10); 
	}
		
}
