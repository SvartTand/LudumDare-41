package com.svarttand.ld41.sprites;

public enum CardType {
	
	SPEEDBUFF(1.5f,1,1,0.8f,"The Enemy Used a SpeedBuff \n 2x faster mobs"),
	BASIC(1,1,1,1,""),
	ALLSTAR(2,2,0.5f,1.5f, "The Enemy Used a Ultimate Charge \n 1.5x more Health \n 2x faster mobs \n 2x more Damage your center will take"),
	SABOTAGE(1,1,0.5f,1, ""),
	MORE_DMG(1,2,1,1.5f, "The Enemy Used a DamageBuff \n 1.5x more Damage your center will take"),
	MORE_HP(1,1,1,2, "The Enemy Used a HealthBuff \n 2x Healthier Mobs");
	
	public float getHp() {
		return hp;
	}


	private float speed;
	private float dmg;
	private float sabotage;
	private float hp;
	private String explanation;
	
	
	private CardType(float speed, float dmg, float sab, float hp , String ex) {
		this.speed = speed;
		this.dmg = dmg;
		sabotage = sab;
		this.hp = hp;
		explanation = ex;
	}
	public String getExplanation(){
		return explanation;
	}


	public float getSpeed() {
		return speed;
	}


	public float getDmg() {
		return dmg;
	}


	public float getSabotage() {
		return sabotage;
	}
	
	

}
