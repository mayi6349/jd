package com.nmtx.doc.core.api;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.nmtx.doc.bean.Api;
import com.nmtx.doc.bean.ApiRequestParam;
import com.nmtx.doc.bean.ApiResponseParam;
import com.nmtx.doc.core.exception.JDocException;
import com.sun.javadoc.ClassDoc;
import com.sun.javadoc.Tag;

public  abstract class AbstractApiParser {
    /**
     * 过滤出Controller文档
     * @param classDocs
     * @return
     */
    public abstract List<String> filterController(List<String> fileNames);
    
    
    public abstract List<Api> generateApi(ClassDoc[] classDocs);
    
    public List<ApiRequestParam> generateApiReqeustParam(Tag[] tags){
        List<ApiRequestParam> apiRequestParams = new ArrayList<ApiRequestParam>();
        List<Tag> paramTags = getTagsByTag(tags,"@param");
        for(Tag tag:paramTags){
            ApiRequestParam apiRequestParam = new ApiRequestParam();
            String tagText = tag.text();
            String [] descs= tagText.split("\\|");
            if(descs.length<4){
                throw new JDocException("the param formater dose not match jdoc formater "+tagText);
            }
            
            apiRequestParam.setName(descs[0]);
            apiRequestParam.setDesc(descs[1]);
            apiRequestParam.setType(descs[2]);
            apiRequestParam.setRequired(descs[3]);
            apiRequestParams.add(apiRequestParam);
        }
        return apiRequestParams;
    }
    
    public List<ApiResponseParam> generateApiResponseParam(Tag[] tags){
        List<ApiResponseParam> apiResponseParams = new ArrayList<ApiResponseParam>();
        List<Tag> paramTags = getTagsByTag(tags,"@respParam");
        for(Tag tag:paramTags){
            ApiResponseParam apiResponseParam = new ApiResponseParam();
            String tagText = tag.text();
            String [] descs= tagText.split("\\|");
            if(descs.length<4){
                throw new JDocException("the param formater dose not match jdoc formater"+tagText);
            }
            
            apiResponseParam.setName(descs[0]);
            apiResponseParam.setDesc(descs[1]);
            apiResponseParam.setType(descs[2]);
            apiResponseParam.setRequired(descs[3]);
            
            apiResponseParams.add(apiResponseParam);
        }
        return apiResponseParams;
    }
    
    public String getTitle(Tag[] tags){
        List<Tag> targetTags = getTagsByTag(tags,"@title");
        if(targetTags!=null&&!targetTags.isEmpty()){
            return targetTags.get(0).text();
        };
        
        return "";
    }
    
    public String getRespBody(Tag[] tags){
        List<Tag> targetTags = getTagsByTag(tags,"@respBody");
        if(targetTags!=null&&!targetTags.isEmpty()){
            return targetTags.get(0).text();
        };
        
        return "";
    }
    
    
    public List<Tag> getTagsByTag(Tag[] tags,String tagName){
        List<Tag> targetTags = new ArrayList<Tag>();
        for(Tag tag:tags){
           if(StringUtils.equals(tag.name(),tagName)){
               targetTags.add(tag);
           };
        }
        return targetTags;
    }
}
