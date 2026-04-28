class KCustomerAccess {
    public static void main(String[] args) {
        System.out.println(KCustomer.LEVEL);
        KCustomer.login();

        //@JvmStatic 에너테이션을 사용하지 않을 때 접근방법
        KCustomer.Companion.login();
        KCustomer.Companion.setTest(100);
        System.out.println(KCustomer.Companion.getTest());
    }
}