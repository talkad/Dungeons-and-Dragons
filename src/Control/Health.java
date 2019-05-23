package Control;

public class Health {

	private Integer healthPool;
	private Integer currentHealth;

	public Health(int healthpool, int currenthealth) {
		this.healthPool = healthpool;
		this.currentHealth = currenthealth;
	}

	public Integer getHealthPool() {
		return healthPool;
	}

	public void setHealthPool(Integer healthPool) {
		this.healthPool = healthPool;
	}

	public Integer getCurrentHealth() {
		return currentHealth;
	}

	public void setCurrentHealth(Integer currentHealth) {
		this.currentHealth = currentHealth;
	}

}
