package tests;

import org.junit.Assert;
import org.junit.Test;


import Control.Player;
import Control.Warrior;

public class TestPlayer {

	
	@Test
	public void checkLevelUp() {
		Player pl=	new Warrior("The Hound", 400, 400, 20, 6, 0, 0, 4);
		pl.levelUp();
		//The level of the player might not change
		Assert.assertTrue(pl.getLevel()==1);
	}
	
	@Test
	public void checkSpecialAbility() {
		Player pl=	new Warrior("The Hound", 400, 400, 20, 6, 0, 0, 4);
		int current=pl.getCurrentHealth();
		int defense=pl.getDefense();
		pl.specialAbility();
		//the current health should increase by two times the defense points.		
		Assert.assertTrue(pl.getCurrentHealth()==current+2*defense);
	}
}
