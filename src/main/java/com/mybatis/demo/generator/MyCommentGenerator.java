package com.mybatis.demo.generator;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.config.PropertyRegistry;
import org.mybatis.generator.internal.DefaultCommentGenerator;
import org.mybatis.generator.internal.util.StringUtility;


import java.util.Properties;

/**
 * 自定义注释工具
 */
public class MyCommentGenerator extends DefaultCommentGenerator {
    private boolean addRemarkComments;
    private boolean suppressAllComments;

    /**
     * 设置用户配置参数
     */
    public void addConfigurationProperties(Properties properties) {
        // 调用父类方法
        super.addConfigurationProperties(properties);

        // 获取suppressAllComments参数值
        suppressAllComments = StringUtility.isTrue(properties.getProperty(PropertyRegistry.COMMENT_GENERATOR_SUPPRESS_ALL_COMMENTS));

        // 获取addRemarkComments参数值
        addRemarkComments = StringUtility.isTrue(properties.getProperty(PropertyRegistry.COMMENT_GENERATOR_ADD_REMARK_COMMENTS));
    }

    /**
     * 给字段添加注释信息
     *
     */
    public void addFieldComment(Field field, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
        // 如果阻止生成注释则返回
        if (suppressAllComments) {
            return;
        }

        // 文档注释开始
        field.addJavaDocLine("/**");

        // 获取数据库字段的备注信息
        String remarks = introspectedColumn.getRemarks();

        // 根据参数和备注信息判断是否添加备注信息
        if (addRemarkComments && StringUtility.stringHasValue(remarks)) {
            String[] remarkLines = remarks.split(System.getProperty("line.separator"));
            for (String remarkLine : remarkLines) {
                field.addJavaDocLine(" * " + remarkLine + "(" + introspectedColumn.getActualColumnName() + ")");
            }
        }

        field.addJavaDocLine(" */"); //$NON-NLS-1$
    }
}
