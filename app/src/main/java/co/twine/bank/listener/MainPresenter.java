package co.twine.bank.listener;

public interface MainPresenter {

    /**
     * Attach to the View, this will bind a communication between UI
     */
    void onAttach();

    /**
     * Get photos for the given input query from server
     */
    void getDepositsList();

    /**
     * Prepare to exit the UI, stop any schedulers and cleanup any other views
     */
    void prepareToExit();

    /**
     * Reset any values and make room for GC to cleanup any used resources
     */
    void onDetach();
}
