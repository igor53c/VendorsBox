public class Main {
    public static void main(String[] args) {

        Vendor OTTO = new Vendor("0", "OTTO", 4.0, 17);
        Vendor Fashion_Mint = new Vendor("1", "Fashion-Mint", 2, 1);
        Vendor FashionCore = new Vendor("2", "FashionCore", 4.5, 6);
        Vendor Dynamic24 = new Vendor("3", "Dynamic24", 5, 2);

        OTTO.addArticleOffer(new ArticleOffer("99", 23.99, 150, 1));
        Fashion_Mint.addArticleOffer(new ArticleOffer("99", 23.99, 100, 7));
        FashionCore.addArticleOffer(new ArticleOffer("99", 23.99, 90, 7));
        Dynamic24.addArticleOffer(new ArticleOffer("99", 23.99, 5, 14));

        Vendor.addVendor(OTTO);
        Vendor.addVendor(Fashion_Mint);
        Vendor.addVendor(FashionCore);
        Vendor.addVendor(Dynamic24);

        VendorsBox vendorsBox = new VendorsBox();
        Article article = new Article("99");

        System.out.println(vendorsBox.findBestVendor(article).getName());

        System.out.println("----------------------------------------------------");

        for (int i = 0; i < 12; i++) {
            System.out.println(vendorsBox.findFairBestVendor(article).getName());
        }
    }
}