package com.universe.exploration.ueui.data.container;

import java.util.ArrayList;

import com.universe.exploration.ueui.data.DataPair;

/**
 * <p>
 * Holds an <tt>ArrayList</tt> of {@link DataPair}.
 * </p>
 * 
 * @author 4.10.2015 Teemu Puurunen
 *
 */
class DataPairContainer implements IDataPairContainer {

    private ArrayList<DataPair> pairList;

    protected Object auxiliaryDataContainer;

    /**
	 * 
	 */
    public DataPairContainer() {
	pairList = new ArrayList<DataPair>();
    }

    public DataPairContainer(Object auxiliaryDataContainer) {
	pairList = new ArrayList<DataPair>();
	this.auxiliaryDataContainer = auxiliaryDataContainer;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.universe.exploration.ui.data.IDataAndValueContainer#createPairs()
     */
    @Override
    public void createPairs() {

    }

    /**
     * Add value pair to list.
     * 
     * @param pair
     */
    public void add(DataPair pair) {
	pairList.add(pair);
    }

    /**
     * Update label value according to predetermined ID.
     * 
     * @param id
     * @param newVal
     */
    public void update(String id, String newVal) {
	for (DataPair pair : pairList) {
	    if (pair.getId() == id) {
		pair.updateValue(newVal);
	    }
	}
    }

    /**
     * @return
     */
    public ArrayList<DataPair> getPairList() {
	return pairList;
    }

}