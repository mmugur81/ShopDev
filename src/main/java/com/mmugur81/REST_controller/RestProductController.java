package com.mmugur81.REST_controller;

import com.mmugur81.REST_model.RestProduct;
import com.mmugur81.REST_model.RestProductSearchCriteria;
import com.mmugur81.REST_model.RestResponse;
import com.mmugur81.model.Product;
import com.mmugur81.model.ProductSearchCriteria;
import com.mmugur81.service.ProductService;
import com.mmugur81.service.UploadService;
import org.omg.CORBA.Object;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mugurel on 27.11.2016.
 * REST controller for products
 */

@RestController
@RequestMapping("/api/product")
public class RestProductController {

    private final String imageUrlPrefix = "/media/" + UploadService.Target.PRODUCT + "/";

    private ProductService productService;

    @Autowired
    public RestProductController(ProductService productService) {
        this.productService = productService;
    }

    @ExceptionHandler(Throwable.class)
    @ResponseBody
    RestResponse<Object> handleControllerException(HttpServletRequest req, Throwable ex) {
        RestResponse<Object> resp = new RestResponse<>(false);
        resp.addError(ex.getMessage());
        return resp;
    }

    /******************************************************************************************************************/

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public RestResponse<List<RestProduct>> search(
        @Valid @RequestBody final RestProductSearchCriteria restProductSearch,
        BindingResult bindingResult
    ) {
        RestResponse<List<RestProduct>> resp = new RestResponse<>();

        if (bindingResult.hasErrors()) {
            resp.setSuccess(false);
            for (ObjectError error : bindingResult.getAllErrors()) {
                resp.addError(error.toString());
            }
        }
        else {
            // Get the list of products by search criteria
            List<Product> products = productService.searchByCriteria(
                productService.convertProductSearchCriteriaFromRest(restProductSearch)
            );

            // Convert to a list of RestProducts
            List<RestProduct> restProducts = new ArrayList<>();
            for (Product p : products) {
                RestProduct rp = p.getRestProduct();
                rp.setImageUrl(imageUrlPrefix + rp.getId());
                restProducts.add(rp);
            }

            // Response
            resp.setSuccess(true);
            resp.setData(restProducts);
        }

        return resp;
    }
}
