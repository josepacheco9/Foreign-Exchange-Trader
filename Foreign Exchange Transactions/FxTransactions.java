package ittc.machineProblem;
import java.io.File;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.Reader;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FxTransactions {
	private static final int ENTER_BUY_SELL_PERCENT				=  1;
	private static final int GET_BUYING_RATES					=  2;
	private static final int GET_SELLING_RATES					=  3;
	private static final int GET_PUBLISHED_RATES	  			=  4;
	private static final int REPLENISH_SEED_MONEY  				=  5;
	private static final int BUY_AND_SELL_CURRENCY				=  6;
	private static final int GENERATE_BALANCE_REPORT			=  7;
	private static final int VIEW_TRANSACTIONS_SUMMARY_REPORT	=  8;
	private static final int ENTER_SEED_MONEY					=  9;
	private static final int GET_BUY_SELL_PERCENT				= 10;
	private static final int EXIT								= 11;
		
	private static Scanner scanner = new Scanner(System.in);
	private static File pRates = new File("PublishedRates.txt");
	private static int row = 6;
	private static int col = 6;
	private static String [][] entries = new String[row][col];
	private static int choice;
	private static String fileSource = new String("transactions.txt");
	private static BuySellPercent fx;
	private static double buyPrcnt;
	private static double sellPrcnt;
	private static double inPHP;
	private static double inUSD;
	private static double inGBP;
	private static double inEUR;
	private static double inCHF;
	private static double purPHP;
	private static double purUSD;
	private static double purGBP;
	private static double purEUR;
	private static double purCHF;
	private static double soldPHP;
	private static double soldUSD;
	private static double soldGBP;
	private static double soldEUR;
	private static double soldCHF;
	private static double onHandPHP;
	private static double onHandUSD;
	private static double onHandGBP;
	private static double onHandEUR;
	private static double onHandCHF;
	private static String pubRates[][] = {
			{"     ", " PHP ", " USD ", " GBP ", " EUR ", " CHF "},
			{"PHP  ", "1.000", "45.00", "71.72", "50.72", "46.81"},
			{"USD  ", "0.220", "1.000", "0.640", "1.100", "1.020"},
			{"GBP  ", "0.010", "1.560", "1.000", "0.710", "0.650"},
			{"EUR  ", "0.0197", "0.909", "1.408", "1.000", "0.920"},
			{"CHF  ", "0.021", "0.980", "1.538", "1.087", "1.000"}
	};
	
	public static void main(String[] args) throws Exception {
		
		scanner = new Scanner(System.in);
		boolean lContinue = true;
		
		while(lContinue) {
			switch (menu()) {
			case ENTER_BUY_SELL_PERCENT : enterBuySellPercent(); break;
			case GET_BUYING_RATES : getBuyingRates(); break;
			case GET_SELLING_RATES : getSellingRates(); break;
			case GET_PUBLISHED_RATES : getPublishedRates(); break;
			case REPLENISH_SEED_MONEY : replenishSeedMoney(); break;
			case BUY_AND_SELL_CURRENCY : performBuyAndSell(); break;
			case GENERATE_BALANCE_REPORT : viewCurrencyAvailabilityReport(); break;
			case VIEW_TRANSACTIONS_SUMMARY_REPORT: viewTransactionsSummaryReport(); break;
			case ENTER_SEED_MONEY: enterSeedMoney(); break;
			case GET_BUY_SELL_PERCENT: getBuySellPercent(); break;
			case EXIT:
				lContinue = false;
				break;
			default:
				System.out.println("\nInvalid Menu option");
			}
		}
		System.out.println("\nEnd of program...");
	}

	public static int menu() {
	
		System.out.println("\nFX Trader Menu");
		System.out.println(" (1)  Enter Buy and Sell Percentages");
		System.out.println(" (2)  View Buying Rates");
		System.out.println(" (3)  View Selling Rates");
		System.out.println(" (4)  View Published Rates");
		System.out.println(" (5)  Replenish Seed Money (additional seed money!)");
		System.out.println(" (6)  Buy and Sell Foreign Exchange");
		System.out.println(" (7)  View Currency Availability Report");
		System.out.println(" (8)  View Transactions Summary Report");
		System.out.println(" (9)  Enter Seed Money (For initial use only!)");
		System.out.println("(10)  View Buy and Sell Percentages");
		System.out.println("(11)  Exit Program");
		System.out.println("\nEnter the number : ");
		System.out.println();
		choice = scanner.nextInt();
		return choice;	
	}
	
	public static void getPublishedRates() throws FileNotFoundException {

		for (int i = 0; i < pubRates.length; i++) {
			for (int j = 0; j < pubRates.length; j++){
				System.out.print(pubRates[i][j] + " ");
			}
			System.out.println("\n");
		}		
	}
	
	public static void enterBuySellPercent() {
		System.out.println("Enter Buying Percentage (in decimal notation) : "); buyPrcnt = scanner.nextDouble();
		System.out.println("Enter Selling Percentage (in decimal notation) : "); sellPrcnt = scanner.nextDouble();
	}
	
	public static void getBuySellPercent() {
		System.out.println("Buying Percentage (in decimal notation) : " + buyPrcnt);
		System.out.println("Selling Percentage (in decimal notation) : " + sellPrcnt);
	}
	
	public static void viewCurrencyAvailabilityReport() {
		onHandPHP = inPHP + purPHP - soldPHP;
		onHandUSD = inUSD + purUSD - soldUSD;
		onHandGBP = inGBP + purGBP - soldGBP;
		onHandEUR = inEUR + purEUR - soldEUR;
		onHandCHF = inCHF + purCHF - soldCHF;
		
		System.out.println(" Currency  Cash On Hand  Seed Money");
		System.out.println("    PHP :     " + onHandPHP + "        " + inPHP);
		System.out.println("    USD :     " + onHandUSD + "        " + inUSD);
		System.out.println("    GBP :     " + onHandGBP + "        " + inGBP);
		System.out.println("    EUR :     " + onHandEUR + "        " + inEUR);
		System.out.println("    CHF :     " + onHandCHF + "        " + inCHF);
	}
	
	public static void performBuyAndSell() {
		System.out.println();
		System.out.println("Currency sold (PHP=1, USD=2, GBP=3, EUR=4, CHF=5) :"); 
		int currency = scanner.nextInt();
		switch (currency) {
		case 1 : 		System.out.println("Amount of PHP sold : ");
						soldPHP = scanner.nextDouble();
						break;
		case 2 : 		System.out.println("Amount of USD sold : ");
						soldUSD = scanner.nextDouble();
						break;
		case 3 : 		System.out.println("Amount of GBP sold : ");
						soldGBP = scanner.nextDouble();
						break;
		case 4 : 		System.out.println("Amount of EUR sold : ");
						soldEUR = scanner.nextDouble();
						break;
		case 5 : 		System.out.println("Amount of CHF sold : ");
						soldCHF = scanner.nextDouble();
						break;
		default:     System.out.println("Not enough currency on hand.");
		}
		System.out.println();
		
		System.out.println("Currency bought (PHP=1, USD=2, GBP=3, EUR=4, CHF=5) :");
		int currency1 = scanner.nextInt();
		switch (currency1) {
		case 1 : 	System.out.println("Amount of PHP bought : ");
						purPHP = scanner.nextDouble();
						break;
		case 2 :	System.out.println("Amount of USD bought : ");
						purUSD = scanner.nextDouble();
						break;
		case 3 :	System.out.println("Amount of GBP bought : ");
						purGBP = scanner.nextDouble();
						break;
		case 4 :	System.out.println("Amount of EUR bought : ");
						purEUR = scanner.nextDouble();
						break;
		case 5 : 	System.out.println("Amount of CHF bought : ");
						purCHF = scanner.nextDouble();
						break;
		default    :	System.out.println();
		}	
	}
		 
	public static void viewTransactionsSummaryReport() {
		onHandPHP = inPHP + purPHP - soldPHP;
		onHandUSD = inUSD + purUSD - soldUSD;
		onHandGBP = inGBP + purGBP - soldGBP;
		onHandEUR = inEUR + purEUR - soldEUR;
		onHandCHF = inCHF + purCHF - soldCHF;
		
		double totAmountInPHP = onHandPHP + onHandUSD * Double.parseDouble(pubRates[1][2]) + onHandGBP * Double.parseDouble(pubRates[1][3]) + onHandEUR * Double.parseDouble(pubRates[1][4]) + onHandCHF * Double.parseDouble(pubRates[1][5]);
		double totAmountSeedInPHP = inPHP + inUSD * Double.parseDouble(pubRates[1][2]) + inGBP * Double.parseDouble(pubRates[1][3]) + inEUR * Double.parseDouble(pubRates[1][4]) + inCHF * Double.parseDouble(pubRates[1][5]);
		double profit = totAmountInPHP - totAmountSeedInPHP; 
		
		System.out.println(" Currency     Cash On Hand        Seed Amount");
		System.out.println("   PHP :      " + onHandPHP + "                   " +  inPHP);
		System.out.println("   USD :      " + onHandUSD + "                   " +  inUSD);
		System.out.println("   GBP :      " + onHandGBP + "                   " +  inGBP);
		System.out.println("   EUR :      " + onHandEUR + "                   " +  inEUR);
		System.out.println("   CHF :      " + onHandCHF + "                   " +  inCHF);
		System.out.println();
		System.out.println("Total Amount (in PHP):     " + totAmountInPHP + "        " + totAmountSeedInPHP);
		System.out.println("Profit (in PHP)      :     " + profit);
		
	}

	public static void enterSeedMoney() {
		SeedMoney entry = new SeedMoney();
		if (entry == null) {
			entry = new SeedMoney();
		}
		System.out.println("\nPHP : "); entry.setSPHP(scanner.nextDouble());
		System.out.println("USD : "); entry.setSUSD(scanner.nextDouble());
		System.out.println("GBP : "); entry.setSGBP(scanner.nextDouble());
		System.out.println("EUR : "); entry.setSEUR(scanner.nextDouble());
		System.out.println("CHF : "); entry.setSCHF(scanner.nextDouble());
		
		inPHP = entry.getSPHP(); System.out.println("PHP : " + inPHP);
		inUSD = entry.getSUSD(); System.out.println("USD : " + inUSD);
		inGBP = entry.getSGBP(); System.out.println("GBP : " + inGBP);
		inEUR = entry.getSEUR(); System.out.println("EUR : " + inEUR);
		inCHF = entry.getSCHF(); System.out.println("CHF : " + inCHF);
	}
	
	public static void replenishSeedMoney() {
		SeedMoney reEntry = new SeedMoney();
		System.out.println("Amount to add to PHP: "); reEntry.addSPHP(scanner.nextDouble());
		inPHP =  inPHP + reEntry.getSPHP(); System.out.println("PHP Balance: " + inPHP + "\n");
		System.out.println("Amount to add to USD: "); reEntry.addSUSD(scanner.nextDouble());
		inUSD =  inUSD + reEntry.getSUSD(); System.out.println("USD Balance: " + inUSD +"\n");
		System.out.println("Amount to add to GBP: "); reEntry.addSGBP(scanner.nextDouble());
		inGBP = inGBP + reEntry.getSGBP(); System.out.println("GBP Balance: " + inGBP + "\n");
		System.out.println("Amount to add to EUR: "); reEntry.addSEUR(scanner.nextDouble());
		inEUR = inEUR + reEntry.getSEUR(); System.out.println("EUR Balance: " + inEUR + "\n");
		System.out.println("Amount to add to CHF: "); reEntry.addSCHF(scanner.nextDouble());
		inCHF = inCHF + reEntry.getSCHF(); System.out.println("CHF Balance: " + inCHF + "\n");
	}
	
	public static void getBuyingRates() {
		double[][] bR = new double [row][col];
		
		
		bR[1][2] = Double.parseDouble(pubRates[1][2]) * (1 - (1 * buyPrcnt));
		bR[1][3] = Double.parseDouble(pubRates[1][3]) * (1 - (1 * buyPrcnt));
		bR[1][4] = Double.parseDouble(pubRates[1][4]) * (1 - (1 * buyPrcnt));
		bR[1][5] = Double.parseDouble(pubRates[1][5]) * (1 - (1 * buyPrcnt));
		bR[2][3] = Double.parseDouble(pubRates[2][3]) * (1 - (1 * buyPrcnt));
		bR[2][4] = Double.parseDouble(pubRates[2][4]) * (1 - (1 * buyPrcnt));
		bR[2][5] = Double.parseDouble(pubRates[2][5]) * (1 - (1 * buyPrcnt));
		bR[3][4] = Double.parseDouble(pubRates[3][4]) * (1 - (1 * buyPrcnt));
		bR[3][5] = Double.parseDouble(pubRates[3][5]) * (1 - (1 * buyPrcnt));
		bR[4][5] = Double.parseDouble(pubRates[4][5]) * (1 - (1 * buyPrcnt));
		bR[2][1] = Double.parseDouble(pubRates[2][1]) * (1 - (1 * buyPrcnt));
		bR[3][1] = Double.parseDouble(pubRates[3][1]) * (1 - (1 * buyPrcnt));
		bR[3][2] = Double.parseDouble(pubRates[3][2]) * (1 - (1 * buyPrcnt));
		bR[4][1] = Double.parseDouble(pubRates[4][1]) * (1 - (1 * buyPrcnt));
		bR[4][2] = Double.parseDouble(pubRates[4][2]) * (1 - (1 * buyPrcnt));
		bR[4][3] = Double.parseDouble(pubRates[4][3]) * (1 - (1 * buyPrcnt));
		bR[5][1] = Double.parseDouble(pubRates[5][1]) * (1 - (1 * buyPrcnt));
		bR[5][2] = Double.parseDouble(pubRates[5][2]) * (1 - (1 * buyPrcnt));
		bR[5][3] = Double.parseDouble(pubRates[5][3]) * (1 - (1 * buyPrcnt));
		bR[5][4] = Double.parseDouble(pubRates[5][4]) * (1 - (1 * buyPrcnt));
		
		String[][] b = new String [row][col];
		
		String b12 = Double.toString(bR[1][2]);
		String b13 = Double.toString(bR[1][3]);
		String b14 = Double.toString(bR[1][4]);
		String b15 = Double.toString(bR[1][5]);
		String b23 = Double.toString(bR[2][3]);
		String b24 = Double.toString(bR[2][4]);
		String b25 = Double.toString(bR[2][5]);
		String b34 = Double.toString(bR[3][4]);
		String b35 = Double.toString(bR[3][5]);
		String b45 = Double.toString(bR[4][5]);
		String b21 = Double.toString(bR[2][1]);
		String b31 = Double.toString(bR[3][1]);
		String b32 = Double.toString(bR[3][2]);
		String b41 = Double.toString(bR[4][1]);
		String b42 = Double.toString(bR[4][2]);
		String b43 = Double.toString(bR[4][3]);
		String b51 = Double.toString(bR[5][1]);
		String b52 = Double.toString(bR[5][2]);
		String b53 = Double.toString(bR[5][3]);
		String b54 = Double.toString(bR[5][4]);
		
		b[1][2] = b12; b[0][0] = "   ";
		b[1][3] = b13; b[0][1] = "PHP";
		b[1][4] = b14; b[0][2] = "USD";
		b[1][5] = b15; b[0][3] = "GBP";
		b[2][3] = b23; b[0][4] = "EUR";
		b[2][4] = b24; b[0][5] = "CHF";
		b[2][5] = b25; b[1][0] = "PHP";
		b[3][4] = b34; b[2][0] = "USD";
		b[3][5] = b35; b[3][0] = "GBP";
		b[4][5] = b45; b[4][0] = "EUR";
		b[2][1] = b21; b[5][0] = "CHF";
		b[3][1] = b31; b[1][1] = "---";
		b[3][2] = b32; b[2][2] = "---";
		b[4][1] = b41; b[3][3] = "---";
		b[4][2] = b42; b[4][4] = "---";
		b[4][3] = b43; b[5][5] = "---";
		b[5][1] = b51;
		b[5][2] = b52;
		b[5][3] = b53;
		b[5][4] = b54;
		
		for (int i = 0; i < b.length; i++) {
			for (int j = 0; j < b[i].length; j++) {
				System.out.printf("%10.5s", b[i][j]);
			}
			System.out.println();
		}
	}
	
	public static void getSellingRates() {
		double[][] sR = new double [row][col];
		
		 
		sR[1][2] = Double.parseDouble(pubRates[1][2]) * (1 + (1 * sellPrcnt));
		sR[1][3] = Double.parseDouble(pubRates[1][3]) * (1 + (1 * sellPrcnt));
		sR[1][4] = Double.parseDouble(pubRates[1][4]) * (1 + (1 * sellPrcnt));
		sR[1][5] = Double.parseDouble(pubRates[1][5]) * (1 + (1 * sellPrcnt));
		sR[2][3] = Double.parseDouble(pubRates[2][3]) * (1 + (1 * sellPrcnt));
		sR[2][4] = Double.parseDouble(pubRates[2][4]) * (1 + (1 * sellPrcnt));
		sR[2][5] = Double.parseDouble(pubRates[2][5]) * (1 + (1 * sellPrcnt));
		sR[3][4] = Double.parseDouble(pubRates[3][4]) * (1 + (1 * sellPrcnt));
		sR[3][5] = Double.parseDouble(pubRates[3][5]) * (1 + (1 * sellPrcnt));
		sR[4][5] = Double.parseDouble(pubRates[4][5]) * (1 + (1 * sellPrcnt));
		sR[2][1] = Double.parseDouble(pubRates[2][1]) * (1 + (1 * sellPrcnt));
		sR[3][1] = Double.parseDouble(pubRates[3][1]) * (1 + (1 * sellPrcnt));
		sR[3][2] = Double.parseDouble(pubRates[3][2]) * (1 + (1 * sellPrcnt));
		sR[4][1] = Double.parseDouble(pubRates[4][1]) * (1 + (1 * sellPrcnt));
		sR[4][2] = Double.parseDouble(pubRates[4][2]) * (1 + (1 * sellPrcnt));
		sR[4][3] = Double.parseDouble(pubRates[4][3]) * (1 + (1 * sellPrcnt));
		sR[5][1] = Double.parseDouble(pubRates[5][1]) * (1 + (1 * sellPrcnt));
		sR[5][2] = Double.parseDouble(pubRates[5][2]) * (1 + (1 * sellPrcnt));
		sR[5][3] = Double.parseDouble(pubRates[5][3]) * (1 + (1 * sellPrcnt));
		sR[5][4] = Double.parseDouble(pubRates[5][4]) * (1 + (1 * sellPrcnt));
		
		String[][] s = new String [row][col];
		
		String s12 = Double.toString(sR[1][2]);
		String s13 = Double.toString(sR[1][3]);
		String s14 = Double.toString(sR[1][4]);
		String s15 = Double.toString(sR[1][5]);
		String s23 = Double.toString(sR[2][3]);
		String s24 = Double.toString(sR[2][4]);
		String s25 = Double.toString(sR[2][5]);
		String s34 = Double.toString(sR[3][4]);
		String s35 = Double.toString(sR[3][5]);
		String s45 = Double.toString(sR[4][5]);
		String s21 = Double.toString(sR[2][1]);
		String s31 = Double.toString(sR[3][1]);
		String s32 = Double.toString(sR[3][2]);
		String s41 = Double.toString(sR[4][1]);
		String s42 = Double.toString(sR[4][2]);
		String s43 = Double.toString(sR[4][3]);
		String s51 = Double.toString(sR[5][1]);
		String s52 = Double.toString(sR[5][2]);
		String s53 = Double.toString(sR[5][3]);
		String s54 = Double.toString(sR[5][4]);
		
		s[1][2] = s12; s[0][0] = "   ";
		s[1][3] = s13; s[0][1] = "PHP";
		s[1][4] = s14; s[0][2] = "USD";
		s[1][5] = s15; s[0][3] = "GBP";
		s[2][3] = s23; s[0][4] = "EUR";
		s[2][4] = s24; s[0][5] = "CHF";
		s[2][5] = s25; s[1][0] = "PHP";
		s[3][4] = s34; s[2][0] = "USD";
		s[3][5] = s35; s[3][0] = "GBP";
		s[4][5] = s45; s[4][0] = "EUR";
		s[2][1] = s21; s[5][0] = "CHF";
		s[3][1] = s31; s[1][1] = "---";
		s[3][2] = s32; s[2][2] = "---";
		s[4][1] = s41; s[3][3] = "---";
		s[4][2] = s42; s[4][4] = "---";
		s[4][3] = s43; s[5][5] = "---";
		s[5][1] = s51;
		s[5][2] = s52;
		s[5][3] = s53;
		s[5][4] = s54;
		
		for (int i = 0; i < s.length; i++) {
			for (int j = 0; j < s[i].length; j++) {
				System.out.printf("%10.5s", s[i][j]);
			}
			System.out.println();
		}
	}
}
