package com.universe.exploration.common.tools;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class TextManipulationToolsTest {

    @Test
    public void testImplodeListAsStringJoinsStringsProperlyWithSeparator() throws Exception {
        ArrayList<String> testStringList = new ArrayList<>();
        testStringList.add("blaa");
        testStringList.add("bloo");
        testStringList.add("blee");

        String joined = TextManipulationTools.implodeListAsString(testStringList, ", ");
        System.out.println("bkporgrkpo");
        Assert.assertEquals("blaa, bloo, blee", joined);
    }

}
