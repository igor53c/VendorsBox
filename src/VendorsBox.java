import java.util.*;

public class VendorsBox {
    private static final Map<String, Integer> nextFairIndices = new HashMap<>();

    public VendorsBox() {
        nextFairIndices.clear();
    }
    public Vendor findBestVendor(Article article) {
        List<Vendor> vendors = Vendor.getVendorsForArticle(article.getArticleId());

        if (vendors.isEmpty()) {
            return null;
        }

        Comparator<Vendor> compareByPrice = Comparator.comparing(vendor ->
                vendor.getArticleOffer(article.getArticleId()).getPrice());
        Comparator<Vendor> compareByShippingTime = Comparator.comparing(vendor ->
                vendor.getArticleOffer(article.getArticleId()).getShippingTime());
        Comparator<Vendor> compareByRating = Comparator.comparingDouble(Vendor::getRating).reversed();
        Comparator<Vendor> compareByAvailability = Comparator.comparing(vendor ->
                vendor.getNumberOfRatings() - vendor.getArticleOffer(article.getArticleId()).getAvailability());

        Collections.sort(
                vendors,
                compareByPrice
                        .thenComparing(compareByShippingTime)
                        .thenComparing(compareByRating)
                        .thenComparing(compareByAvailability)
        );

        return vendors.get(0);
    }
    public Vendor findFairBestVendor(Article article) {
        List<Vendor> vendors = Vendor.getVendorsForArticle(article.getArticleId());

        if (vendors.isEmpty()) {
            return null;
        }

        vendors.sort(Comparator.comparing(vendor -> vendor.getArticleOffer(article.getArticleId()).getPrice()));

        double lowestPrice = vendors.get(0).getArticleOffer(article.getArticleId()).getPrice();
        int numVendorsWithLowestPrice = 0;
        for (Vendor vendor : vendors) {
            if (vendor.getArticleOffer(article.getArticleId()).getPrice() == lowestPrice) {
                numVendorsWithLowestPrice++;
            } else {
                break;
            }
        }

        int fairIndex = 0;
        if (nextFairIndices.containsKey(article.getArticleId())) {
            fairIndex = nextFairIndices.get(article.getArticleId());
        }
        Vendor fairVendor = vendors.get(fairIndex);

        int nextFairIndex = (fairIndex + 1) % numVendorsWithLowestPrice;
        nextFairIndices.put(article.getArticleId(), nextFairIndex);

        return fairVendor;
    }
}
