/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.saden1.pit;

import org.testng.annotations.Test;

/**
 *
 * @author Sharmarke Aden
 */
public class MyClassNGTest {
    
    MyClass sut = new MyClass();
    
    @Test
    public void testMethod() {
        sut.method("hello");
    }
    
}
