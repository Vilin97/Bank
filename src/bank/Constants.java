package bank;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Currency;

public class Constants {
    // a class to hold constants to be used in the bank. E.g. how big the fees are, etc.
    private static double withdrawalFeeFraction = 0.05;
    private static double depositFeeFraction = 0.05;
    private static double openAccountFeeFraction = 0.05;
    private static double closeAccountFeeFraction = 0.05;
    private static double transferBetweenAccountsFeeFraction = 0.01;

    private static double fundsNeededToOpenSecuritiesAccount = 5000;
    private static double fundsToTransferToSecuritiesAccount = 1000;
    private static double fundsToMaintainInSavingsToOpenSecurities = 2500;

    private static double savingsAccountInterestThresholdInUSD = 1000;
    private static double savingsAccountInterestRate = 0.01;
    private static double loanInterestRate = 0.05;

    private static double conversionRateEUR = 1.2;
    private static double conversionRateRUB = 1/80.0;
    
    public static final ArrayList<String> curencies = new ArrayList<String>(Arrays.asList("USD","EUR","RUB"));

    public static double getTransferBetweenAccountsFeeFraction() {
        return transferBetweenAccountsFeeFraction;
    }

    public static void setTransferBetweenAccountsFeeFraction(double transferBetweenAccountsFeeFraction) {
        Constants.transferBetweenAccountsFeeFraction = transferBetweenAccountsFeeFraction;
    }

    public static double getWithdrawalFeeFraction() {
        return withdrawalFeeFraction;
    }

    public static void setWithdrawalFeeFraction(double withdrawalFeeFraction) {
        Constants.withdrawalFeeFraction = withdrawalFeeFraction;
    }

    public static double getDepositFeeFraction() {
        return depositFeeFraction;
    }

    public static void setDepositFeeFraction(double depositFeeFraction) {
        Constants.depositFeeFraction = depositFeeFraction;
    }

    public static double getOpenAccountFeeFraction() {
        return openAccountFeeFraction;
    }

    public static void setOpenAccountFeeFraction(double openAccountFeeFraction) {
        Constants.openAccountFeeFraction = openAccountFeeFraction;
    }

    public static double getCloseAccountFeeFraction() {
        return closeAccountFeeFraction;
    }

    public static void setCloseAccountFeeFraction(double closeAccountFeeFraction) {
        Constants.closeAccountFeeFraction = closeAccountFeeFraction;
    }

    public static double getFundsNeededToOpenSecuritiesAccount() {
        return fundsNeededToOpenSecuritiesAccount;
    }

    public static void setFundsNeededToOpenSecuritiesAccount(double fundsNeededToOpenSecuritiesAccount) {
        Constants.fundsNeededToOpenSecuritiesAccount = fundsNeededToOpenSecuritiesAccount;
    }

    public static double getFundsToTransferToSecuritiesAccount() {
        return fundsToTransferToSecuritiesAccount;
    }

    public static void setFundsToTransferToSecuritiesAccount(double fundsToTransferToSecuritiesAccount) {
        Constants.fundsToTransferToSecuritiesAccount = fundsToTransferToSecuritiesAccount;
    }

    public static double getFundsToMaintainInSavingsToOpenSecurities() {
        return fundsToMaintainInSavingsToOpenSecurities;
    }

    public static void setFundsToMaintainInSavingsToOpenSecurities(double fundsToMaintainInSavingsToOpenSecurities) {
        Constants.fundsToMaintainInSavingsToOpenSecurities = fundsToMaintainInSavingsToOpenSecurities;
    }

    public static double getSavingsAccountInterestThresholdInUSD() {
        return savingsAccountInterestThresholdInUSD;
    }

    public static void setSavingsAccountInterestThresholdInUSD(double savingsAccountInterestThresholdInUSD) {
        Constants.savingsAccountInterestThresholdInUSD = savingsAccountInterestThresholdInUSD;
    }

    public static double getSavingsAccountInterestRate() {
        return savingsAccountInterestRate;
    }

    public static void setSavingsAccountInterestRate(double savingsAccountInterestRate) {
        Constants.savingsAccountInterestRate = savingsAccountInterestRate;
    }

    public static double getLoanInterestRate() {
        return loanInterestRate;
    }

    public static void setLoanInterestRate(double loanInterestRate) {
        Constants.loanInterestRate = loanInterestRate;
    }

    public static double getConversionRateEUR() {
        return conversionRateEUR;
    }

    public static void setConversionRateEUR(double conversionRateEUR) {
        Constants.conversionRateEUR = conversionRateEUR;
    }

    public static double getConversionRateRUB() {
        return conversionRateRUB;
    }

    public static void setConversionRateRUB(double conversionRateRUB) {
        Constants.conversionRateRUB = conversionRateRUB;
    }

    private static double exchangeRateToUSD(Currency c) {
        // returns exchange rate from c to USD (i.e. how many dollars 1 unit of c is)
        // TODO
        if (c.equals(Currency.getInstance("EUR"))) return conversionRateEUR;
        else if (c.equals(Currency.getInstance("RUB"))) return conversionRateRUB;
        return 1.0;
    }

    public static double exchangeCurrencyToUSD(Currency from, double amount){
        return exchangeRateToUSD(from)*amount;
    }

    public static double exchangeUSDtoCurrency(Currency to, double amount){
        return amount/exchangeRateToUSD(to);
    }

    public static double exchangeCurrency(Currency from, Currency to, double amount){
        double dollarsEquivalent = exchangeCurrencyToUSD(from, amount);
        return exchangeUSDtoCurrency(to, dollarsEquivalent);
    }

}
