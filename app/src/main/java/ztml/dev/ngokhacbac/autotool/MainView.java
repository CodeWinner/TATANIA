package ztml.dev.ngokhacbac.autotool;

public interface MainView {
    /**
     * Trả về có sim hay không ?
     */
    void isSim(boolean isSim);

    /**
     * Trả về số điện thoại
     */
    void phoneNumber(boolean phoneNumber);

    /**
     * Trả về kết quả respone từ server
     */
    void requestSuccess();

    void requestFail();

}
