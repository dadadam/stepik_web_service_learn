package templater;


import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;

public class PageGenerator {
    private static final String TEMPLATE_DIR = "templates";

    private static PageGenerator pageGenerator;
    private final Configuration cfg;

    public static  PageGenerator instance() {
        if (pageGenerator == null) {
            pageGenerator = new PageGenerator();
        }

        return pageGenerator;
    }

    private PageGenerator() {
        this.cfg = new Configuration();
    }

    public String getPage(String filename, Map<String, Object> data) {
        Writer stream = new StringWriter();
        try {
            Template template = cfg.getTemplate(TEMPLATE_DIR + File.separator + filename);
            template.process(data, stream);
        } catch (IOException | TemplateException e) {
            e.printStackTrace();
        }
        return stream.toString();
    }
}
