package sets;

public enum BillingStatus {
    WAITING_PAYMENT(0),
    PAID(1),
    CANCELED(2);
    private int code;

    private BillingStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static BillingStatus valueOf(int code) {
        for (BillingStatus value : BillingStatus.values()) {
            if (value.getCode() == code) {
                return value;
            }
        }
        throw new IllegalArgumentException("Invalid Billing Status code");
    }
}
