package org.example.stepDefinitions;

import io.cucumber.java.After;
import org.example.TestSuitBase;

public class Hooks extends TestSuitBase {
    @After
    public void tearDown() {
        driverDispose();
    }
}