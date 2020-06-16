package bookReviewer.application.useCase.query.getOffersOfBookUseCase;

public enum Vendor {
    BUCHLADEN123DE("Buchladen123.de"),
    BUCHVERKAUF24("Buchverkauf 24"),
    YOUR_FAVORITE_BOOK_VENDOR("Your favorite book vendor");

    String vendorName;

    Vendor(String vendorName) {

        this.vendorName  = vendorName;
    }

    public String getVendorName() {

        return vendorName;
    }
}
