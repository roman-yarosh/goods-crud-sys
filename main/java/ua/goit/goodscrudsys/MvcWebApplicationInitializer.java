package ua.goit.goodscrudsys;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * First step always is to create initilizer for
 * registering of {@link org.springframework.web.servlet.DispatcherServlet}
 *
 * @author Roman Yarosh
 */
public class MvcWebApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer{

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[0];
    }


    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[0];
    }

    @Override
    protected String[] getServletMappings() {
        return new String[0];
    }
}
