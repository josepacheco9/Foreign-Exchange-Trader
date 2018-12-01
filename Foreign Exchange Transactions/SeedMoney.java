package ittc.machineProblem;

public class SeedMoney {
	
	private double sPHP;
	private double sUSD;
	private double sGBP;
	private double sEUR;
	private double sCHF;
		
	public void setSPHP(double sPHP) {
		this.sPHP = sPHP;
	}
	public double getSPHP() {
		return sPHP;
	}
	public void setSUSD(double sUSD){
		this.sUSD = sUSD;
	}
	public double getSUSD() {
		return sUSD;
	}
	public void setSGBP(double sGBP) {
		this.sGBP = sGBP;
	}
	public double getSGBP() {
		return sGBP;
	}
	public void setSEUR(double sEUR) {
		this.sEUR = sEUR;
	}
	public double getSEUR() {
		return sEUR;
	}
	public void setSCHF(double sCHF) {
		this.sCHF = sCHF;
	}
	public double getSCHF() {
		return sCHF;
	}
	public double addSPHP(double aPHP) {
		sPHP = sPHP + aPHP;
		return sPHP;
	}
	public double addSUSD(double aUSD) {
		sUSD = sUSD + aUSD;
		return sUSD;
	}
	public double addSGBP(double aGBP) {
		sGBP = sGBP + aGBP;
		return sGBP;
	}
	public double addSEUR(double aEUR) {
		sEUR = sEUR + aEUR;
		return sEUR;
	}
	public double addSCHF(double aCHF) {
		sCHF = sCHF + aCHF;
		return sCHF;
	}
}
