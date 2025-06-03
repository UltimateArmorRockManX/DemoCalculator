package ThaiTan;

public class InsuranceClaim {

    private String claimId;
    private double amount;
    private String claimStatus;

    // Constructor
    public InsuranceClaim(String claimId, double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero");
        }
        if (claimId == null || claimId.isEmpty()) {
            throw new IllegalArgumentException("Claim ID cannot be null or empty");
        }
        this.claimId = claimId;
        this.amount = amount;
        this.claimStatus = "Pending"; // Default status
    }

    // Getter for claimId
    public String getClaimId() {
        return claimId;
    }

    // Getter for amount
    public double getAmount() {
        return amount;
    }

    // Getter for claimStatus
    public String getClaimStatus() {
        return claimStatus;
    }

    // Process claim status
    public boolean processClaim(String newStatus) {
        if ("Pending".equals(this.claimStatus)) {
            this.claimStatus = newStatus;
            return true;
        }
        return false;
    }

    // Calculate payout based on claim status
    public double calculatePayout() {
        if ("Approved".equals(this.claimStatus)) {
            return amount * 0.85; // Assuming an 85% payout rate for approved claims
        }
        return 0.0;
    }

    // Update claim amount
    public void updateClaimAmount(double newAmount) {
        if (newAmount <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero");
        }
        this.amount = newAmount;
    }

    @Override
    public String toString() {
        return "InsuranceClaim{" +
                "claimId='" + claimId + '\'' +
                ", amount=" + amount +
                ", claimStatus='" + claimStatus + '\'' +
                '}';
    }
}

