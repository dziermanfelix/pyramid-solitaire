public final class Common {
    private static Common instance;

    public static Common getInstance() {
        if (instance == null) instance = new Common();
        return instance;
    }

    public static void debugPrint(String message) {
        System.out.println("YEEBOB: " + message);
    }
}
