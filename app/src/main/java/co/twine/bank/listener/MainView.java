package co.twine.bank.listener;


import android.support.annotation.NonNull;

import java.util.List;

import co.twine.bank.model.Deposits;

public interface MainView {

    /**
     * Add Deposits to the adapter
     * @param results
     */
    void addItemsToList(@NonNull List<Deposits> results);

    /**
     * Clear the list and present user with right full information
     */
    void showEmptyList();

    /**
     * Prepare for showing new lists when user request with new search
     */
    void prepareForNewList();

    /**
     * To update monthly recurring amount
     * @param amount
     */
    void updateMonthlyTotal(String amount);

}
