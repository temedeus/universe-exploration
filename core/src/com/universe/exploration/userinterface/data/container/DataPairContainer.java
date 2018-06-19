package com.universe.exploration.userinterface.data.container;

import com.universe.exploration.userinterface.data.DataPair;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * Holds an <tt>ArrayList</tt> of {@link DataPair}.
 * </p>
 *
 * @author 4.10.2015 Teemu Puurunen
 */
public class DataPairContainer implements IDataPairContainer {

    // TODO: this would have likely been wiser as HashMap. However the
    // implementation is kind of goofy so this would result in redundant
    // information (id as key for HashMap and also in DataPair).
    private List<DataPair> pairList;

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

    public void add(String id, String label, String value) {
        pairList.add(new DataPair(id, label, value));
    }

    public void addNoId(String label, String value) {
        pairList.add(new DataPair(null, label, value));
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
    public List<DataPair> getPairList() {
        return pairList;
    }

}