package bank;

public class Constants {
    // a class to hold constants to be used in the bank. E.g. how big the fees are, etc.
    private static double withdrawalFeeFraction = 0.05;
    private static double depositFeeFraction = 0.05;
    private static double openAccountFeeFraction = 0.05;
    private static double closeAccountFeeFraction = 0.05;
    private static double fundsNeededToOpenSecuritiesAccount = 5000;
    private static double fundsToTransferToSecuritiesAccount = 1000;
    private static double fundsToMaintainInSavingsToOpenSecurities = 2500;

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
}
