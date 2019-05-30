package tests;

import org.junit.Assert;
import org.junit.Test;

import Control.Controller;
import Control.DeterministicNumber;
import Control.Enemies;
import Control.Warrior;

public class TestController {

	private Controller control=new Controller("C:\\Users\\User\\eclipse-workspace\\DungeonsAndDragons\\levels",
			new Warrior("Jon Snow", 300, 300, 30, 4,21, 3, 6), new DeterministicNumber());
	
	@Test
	public void checkInitializeEnemies() {
		control.initializeEnemies();
		//The amount of enemies at the first level is 2
		Assert.assertTrue(Controller.currentEnemies.size()==2);
	}
	
	@Test
	public void checkCombat() {
		Enemies enemy=Controller.currentEnemies.get(0);
		int health=enemy.getCurrentHealth();
		Controller.combat(Controller.currentPlayer, enemy);
		//in deterministic mode the attack points is 2 and the defense is 0 at first round
		//so the current health of the enemy should drop in 2 points
		Assert.assertTrue(enemy.getCurrentHealth()==health-2);
	}
	
	@Test
	public void checkGameOver1() {
		//The player still have life
		Assert.assertTrue(!control.isGameOver());
	}
	
	@Test
	public void checkGameOver2() {
		Controller.currentPlayer.setHealth(-9);
		//The player have no life
		Assert.assertTrue(control.isGameOver());
	}
	
	@Test
	public void checkMoveDown() {
		int x=Controller.currentPlayer.getPosition().getX();
		int y=Controller.currentPlayer.getPosition().getY();
		Controller.currentPlayer.moveDown();
		//the game unit should move down
		Assert.assertTrue(Controller.currentPlayer.getPosition().getX()==x && Controller.currentPlayer.getPosition().getY()==y+1); 
	}
	
	@Test
	public void checkMoveUp() {
		int x=Controller.currentPlayer.getPosition().getX();
		int y=Controller.currentPlayer.getPosition().getY();
		Controller.currentPlayer.moveUp();
		//the game unit should move up
		Assert.assertTrue(Controller.currentPlayer.getPosition().getX()==x && Controller.currentPlayer.getPosition().getY()==y-1); 
	}
	
	@Test
	public void checkMoveLeft() {
		int x=Controller.currentPlayer.getPosition().getX();
		int y=Controller.currentPlayer.getPosition().getY();
		Controller.currentPlayer.moveDown();
		//the game unit should move down
		Assert.assertTrue(Controller.currentPlayer.getPosition().getX()==x-1 && Controller.currentPlayer.getPosition().getY()==y); 
	}
	
	@Test
	public void checkMoveRight() {
		int x=Controller.currentPlayer.getPosition().getX();
		int y=Controller.currentPlayer.getPosition().getY();
		Controller.currentPlayer.moveDown();
		//the game unit should move down
		Assert.assertTrue(Controller.currentPlayer.getPosition().getX()==x+1 && Controller.currentPlayer.getPosition().getY()==y); 
	}
}
