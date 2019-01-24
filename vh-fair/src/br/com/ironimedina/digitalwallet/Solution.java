package br.com.ironimedina.digitalwallet;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


/*
 * Create TransactionException, DigitalWallet, and DigitalWalletTransaction classes here.
 */

class TransactionException extends Exception {

	private static final long serialVersionUID = -2711363784897761513L;
	
	private final String errorMessage;
    private final String errorCode;

    public TransactionException(String errorMessage, String errorCode) {
    	super(errorMessage);
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return this.errorCode;
    }

}

class DigitalWallet {
	
	private String walletId;
	private String userName;
	private String userAcessCode;
	private int walletBalance;
	
	public DigitalWallet(String walletId, String userName) {
		this.walletId = walletId;
		this.userName = userName;
	}
	
	public DigitalWallet(String walletId, String userName, String userAcessCode) {
		this.walletId = walletId;
		this.userName = userName;
		this.userAcessCode = userAcessCode;
	}
	
	public String getWalletId() {
		return this.walletId;
	}
	
	public String getUsername() {
		return this.userName;
	}
	
	public String getUserAccessToken() {
		return this.userAcessCode;
	}
	
	public int getWalletBalance() {
		return this.walletBalance;
	}
	
	public void setWalletBalance(int walletBalance) {
		this.walletBalance = walletBalance;
	}
}

class DigitalWalletTransaction {
	
	public void addMoney(DigitalWallet digitalWallet, int amount) throws TransactionException {
		validateTransaction(digitalWallet, amount, false);
		digitalWallet.setWalletBalance(digitalWallet.getWalletBalance() + amount);
	}
	
	public void payMoney(DigitalWallet digitalWallet, int amount) throws TransactionException {
		validateTransaction(digitalWallet, amount, true);
		digitalWallet.setWalletBalance(digitalWallet.getWalletBalance() - amount);
	}
	
	private void validateTransaction(DigitalWallet digitalWallet, int amount, boolean isPayment) throws TransactionException {
		validateAccessCode(digitalWallet);
		validateAmount(amount);
		if(isPayment) {
			validateBalance(digitalWallet, amount);
		}
	}
	
	private void validateAccessCode(DigitalWallet digitalWallet) throws TransactionException {
		if(digitalWallet.getUserAccessToken() == null) {
			throw new TransactionException("User not authorized", "USER_NOT_AUTHORIZED");
		}
	}

	private void validateAmount(int amount) throws TransactionException {
		if(amount <= 0) {
			throw new TransactionException("Amount should be greater than zero", "INVALID_AMOUNT");
		}
	}

	private void validateBalance(DigitalWallet digitalWallet, int amount) throws TransactionException {
		int balance = digitalWallet.getWalletBalance();
		if(amount > balance) {
			throw new TransactionException("Insufficient balance", "INSUFFICIENT_BALANCE");
		}
	}
	
}

public class Solution {
    private static final Scanner INPUT_READER = new Scanner(System.in);
    private static final DigitalWalletTransaction DIGITAL_WALLET_TRANSACTION = new DigitalWalletTransaction();
    
    private static final Map<String, DigitalWallet> DIGITAL_WALLETS = new HashMap<>();
    
    public static void main(String[] args) {
        int numberOfWallets = Integer.parseInt(INPUT_READER.nextLine());
        while (numberOfWallets-- > 0) {
            String[] wallet = INPUT_READER.nextLine().split(" ");
            DigitalWallet digitalWallet;
            
            if (wallet.length == 2) {
                digitalWallet = new DigitalWallet(wallet[0], wallet[1]);
            } else {
                digitalWallet = new DigitalWallet(wallet[0], wallet[1], wallet[2]);
            }
            
            DIGITAL_WALLETS.put(wallet[0], digitalWallet);
        }
        
        int numberOfTransactions = Integer.parseInt(INPUT_READER.nextLine());
        while (numberOfTransactions-- > 0) {
            String[] transaction = INPUT_READER.nextLine().split(" ");
            DigitalWallet digitalWallet = DIGITAL_WALLETS.get(transaction[0]);
            
            if (transaction[1].equals("add")) {
                try {
                    DIGITAL_WALLET_TRANSACTION.addMoney(digitalWallet, Integer.parseInt(transaction[2]));
                    System.out.println("Wallet successfully credited.");
                } catch (TransactionException ex) {
                    System.out.println(ex.getErrorCode() + ": " + ex.getMessage() + ".");
                }
            } else {
                try {
                    DIGITAL_WALLET_TRANSACTION.payMoney(digitalWallet, Integer.parseInt(transaction[2]));
                    System.out.println("Wallet successfully debited.");
                } catch (TransactionException ex) {
                    System.out.println(ex.getErrorCode() + ": " + ex.getMessage() + ".");
                }
            }
        }
        
        System.out.println();
        
        DIGITAL_WALLETS.keySet()
                .stream()
                .sorted()
                .map((digitalWalletId) -> DIGITAL_WALLETS.get(digitalWalletId))
                .forEachOrdered((digitalWallet) -> {
                    System.out.println(digitalWallet.getWalletId()
                            + " " + digitalWallet.getUsername()
                            + " " + digitalWallet.getWalletBalance());
                });
    }
}
