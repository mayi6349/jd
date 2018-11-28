package com.nmtx.doc.core.api;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.nmtx.doc.bean.Api;
import com.nmtx.doc.core.config.JDocConfig;
import com.nmtx.doc.core.constant.Constant;
import com.nmtx.doc.core.exception.JDocException;
import com.sun.javadoc.ClassDoc;

public class ApiBuilder extends AbstractApiParser {

    private static AbstractApiParser apiParser;

    @Override
    public List<String> filterController(List<String> fileNames) {

        return apiParser.filterController(fileNames);
    }
    
    @Override
    public List<Api> generateApi(ClassDoc[] classDocs) {
      
        return apiParser.generateApi(classDocs);
    }

    public static AbstractApiParser builder() {
        if (apiParser == null) {
            String apiParserName = JDocConfig.getValue("parser.name");

            String apiParserClassName = "";

            if (StringUtils.equals(Constant.JFINAL, apiParserName)) {
                apiParserClassName = "com.nmtx.doc.core.api.jfinal.JFinalApiParser";
            }

            if (StringUtils.equals(Constant.SPRING_MVC, apiParserName)||StringUtils.equals(Constant.SPRING_BOOT, apiParserName)) {
                apiParserClassName = "com.nmtx.doc.core.api.springmvc.SpringMVCApiParser";
            }

            if (StringUtils.isBlank(apiParserClassName)) {
                throw new JDocException("not find apiParser,please check parser.name para");
            }

            try {
                apiParser = (AbstractApiParser) Class.forName(apiParserClassName).newInstance();
            } catch (InstantiationException | IllegalAccessException | ClassNotFoundException | NullPointerException e) {
                throw new JDocException("build apiParser exception,please check parser.name para");
            }
        }
        return apiParser;
    }

    

}
