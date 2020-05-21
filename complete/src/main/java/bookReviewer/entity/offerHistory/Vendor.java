package bookReviewer.entity.offerHistory;

public class Vendor {
    private String vendor;

    public Vendor() {
    }

    public Vendor(String vendor) {
        this.vendor = vendor;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    @Override
    public String toString() {
        return "Vendor{" +
                "vendor='" + vendor + '\'' +
                '}';
    }
}
