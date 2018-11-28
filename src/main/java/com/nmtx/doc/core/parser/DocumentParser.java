package com.nmtx.doc.core.parser;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.nmtx.doc.bean.Api;
import com.nmtx.doc.core.analysis.ApiAnalysis;
import com.nmtx.doc.core.api.AbstractApiParser;
import com.nmtx.doc.core.api.ApiBuilder;
import com.nmtx.doc.core.config.JDocConfig;
import com.nmtx.doc.core.formater.Formater;
import com.nmtx.doc.core.formater.FormaterBuilder;
import com.sun.javadoc.ClassDoc;
import com.sun.javadoc.RootDoc;

/**
 * 文档解析器
 * 
 * @author lianghao
 *
 */
public class DocumentParser {

    public static boolean start(RootDoc root) {
        AbstractApiParser apiParser = ApiBuilder.builder();

        ClassDoc[] classDocs = root.classes();

        List<Api> apis = apiParser.generateApi(classDocs);

        String useAnalysisStr = JDocConfig.getValue("use.analysis");
        boolean useAnalysis = false;
        if (StringUtils.isNotBlank(useAnalysisStr)) {
            useAnalysis = Boolean.parseBoolean(useAnalysisStr);
        }
        if (useAnalysis) {
            new ApiAnalysis(apis).start();
        } else {
            Formater formater = new FormaterBuilder().builder();
            formater.output(apis);
        }
        return true;
    }

}
