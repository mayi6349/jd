package com.nmtx.doc.core.formater.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.nmtx.doc.bean.Api;
import com.nmtx.doc.core.config.JDocConfig;
import com.nmtx.doc.core.constant.Constant;
import com.nmtx.doc.core.formater.Formater;
import com.nmtx.doc.core.template.FreemarketTemplate;

public class HtmlFormater implements Formater{

    @Override
    public void output(List<Api> apis) {
         String json = JSON.toJSONString(apis, new SerializerFeature[]{SerializerFeature.DisableCircularReferenceDetect});
         Map<Object,Object> paras = new HashMap<Object,Object>();
         paras.put("api", json);
         FreemarketTemplate.output(Constant.HTML_TEMPALTE,JDocConfig.getValue("out.path"), paras);
        
    }

}
