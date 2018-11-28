package com.nmtx.test;

import org.junit.Test;

import com.nmtx.doc.core.api.jfinal.JFinalApiDocConfig;

public class JDocTest {

    @Test
    public void buildJFinalDoc() {
        new JFinalApiDocConfig("jdoc.properties").setUseClearSuffix(false).add("/jfinal", JFinalController.class).start();
    }
}
