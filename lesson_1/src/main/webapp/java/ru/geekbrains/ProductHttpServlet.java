package ru.geekbrains;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ProductHttpServlet", urlPatterns = "/")
public class ProductHttpServlet extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(ProductHttpServlet.class);

    @Inject
    private ProductService productService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer total = Integer.getInteger(req.getParameter("total"));

        if (total == null) {
            return;
        }

        List<Product> productList = productService.getProducts(total);

        req.setAttribute("productList", productList);
        getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
    }
}
