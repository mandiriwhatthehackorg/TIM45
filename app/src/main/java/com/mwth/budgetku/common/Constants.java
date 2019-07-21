package com.mwth.budgetku.common;

public class Constants {

    public static String CID = "fb7c5557-92a5-4193-805d-3518ad315174";
    public static String CIS = "fc27e183-9d6f-45d0-a0a1-998e9808c002";

    private static String APPID = "d8cdb215-6702-4b0f-ad15-1be14cd051a2";

    private static String BASE_URI = "http://apigateway.mandiriwhatthehack.com/";

    public static final String TOKEN_URI = BASE_URI + "rest/pub/apigateway/jwt/getJsonWebToken?app_id=" + APPID;

    public static final String CUSTOMER_URI = BASE_URI + "gateway/ServicingAPI/1.0/customer/";
    public static final String CASA_URI = BASE_URI + "gateway/ServicingAPI/1.0/customer/casa/";

    public static final String BILL_URI = BASE_URI + "gateway/TrxAndPaymentAPI/1.0/bill";
    public static final String PAM_URI = BASE_URI + "gateway/TrxAndPaymentAPI/1.0/bill/pam/";
    public static final String PLN_URI = BASE_URI + "gateway/TrxAndPaymentAPI/1.0/bill/pln/post/";
    public static final String TELKOMSEL_URI = BASE_URI + "gateway/TrxAndPaymentAPI/1.0/bill/telkomsel/post/";

    public static final String LOGIN_USER = "TIM45";
    public static final String LOGIN_PASS = "12345";

    public static final String CIF_NO = "TIM45";
    public static final String ACCOUNT_NO = "1111006400160";

}