public class LegacyAPI {
    @Deprecated
    void oldFeature() {
        System.out.println("Deprecated Old Feature");
    }

    void newFeature() {
        System.out.println("New Feature");
    }

    public static void main(String[] args) {
        LegacyAPI feature = new LegacyAPI();
        feature.oldFeature();
        feature.newFeature();
    }
}