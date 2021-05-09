package com.applecat.blog.utils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.commonmark.Extension;
import org.commonmark.ext.gfm.tables.TableBlock;
import org.commonmark.ext.gfm.tables.TablesExtension;
import org.commonmark.ext.heading.anchor.HeadingAnchorExtension;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.AttributeProvider;
import org.commonmark.renderer.html.HtmlRenderer;

public class MarkdownUtils {

    /**
     *  markdown 转html
     * @param markdown 源markdown字符串
     * @return 转换结果
     */
    public static String markdownToHtml(String markdown) {
        Parser parser = Parser.builder().build();
        Node document = parser.parse(markdown);
        HtmlRenderer renderer = HtmlRenderer.builder().build();
        return renderer.render(document);
    }

    public static String markdownToHtmlExtensions(String markdown) {
        //h标题生成id
        Set<Extension> headingAnchExtensions = Collections.singleton(HeadingAnchorExtension.create());
        //转化table的html
        List<Extension> tablExtensions = Arrays.asList(TablesExtension.create());
        Parser parser = Parser.builder().extensions(tablExtensions).build();

        Node document = parser.parse(markdown);

        HtmlRenderer renderer = HtmlRenderer.builder().extensions(headingAnchExtensions).extensions(tablExtensions).
        attributeProviderFactory((context) -> {return new CustomAttributeProvider();}).build();
        return renderer.render(document);
    }

    static class CustomAttributeProvider implements AttributeProvider {

        @Override
        public void setAttributes(Node node, String tagName, Map<String, String> attributes) {
            if (node instanceof TableBlock) {
                attributes.put("class", "ui celled table");
            }
        }

    }

}
