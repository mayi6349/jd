package com.nmtx.doc.core;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.nmtx.doc.core.api.ApiBuilder;
import com.nmtx.doc.core.config.JDocConfig;
import com.nmtx.doc.core.exception.JDocException;
import com.nmtx.doc.core.parser.DocumentParser;
import com.nmtx.doc.utils.FileUtils;

public class JDocCore {
    
    private String configFileName;
    
    
    public void builder(){
        /**
         * 初始化加载配置文件
         */
        
        JDocConfig.instance(configFileName);
        /**
         * 获取扫描的包名
         */
        String packageName = JDocConfig.getValue("package.name");
        
        if(StringUtils.isBlank(packageName)){
            throw new JDocException("package.name para is not allow empty");
        }
        
        List<String> files = FileUtils.getJavaFileNameByPackage(packageName);
        
        files = ApiBuilder.builder().filterController(files);
        
        /**
         * -doclet执行把所有的文档注释放到DocCoreHandler参数里，调用start方法
         */
        files.add(0, "-doclet");
        files.add(1, DocumentParser.class.getName());

        String[] docArgs = files.toArray(new String[files.size()]);

        com.sun.tools.javadoc.Main.execute(docArgs);
        
    }
    
    
    public JDocCore setConfigFileName(String configFileName) {
        this.configFileName = configFileName;
        return this;
    }

    
    public String getConfigFileName() {
        return configFileName;
    }
    
    

}
