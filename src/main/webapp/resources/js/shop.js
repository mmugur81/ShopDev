
/**
 * Created by mugurel on 29.12.2016.
 */


var shopFilter = {

    updateCategoriesList : function (selectId) {
        // console.log('Getting categories...');
        var lsUrl = '/api/product/categories';

        var selectObj = jQuery('#'+selectId);
        selectObj.append('<option value=0>* ALL *</option>');

        jQuery.get(lsUrl, function (oData) {
            // console.log(oData['data']);

            var listitems;
            jQuery.each(oData['data'], function(key, item){
                listitems += '<option value=' + item['id'] + '>' + item['name'] + '</option>';
            });
            selectObj.append(listitems);
        });
    }

};

var shop = {

    searchProducts : function (container, templateDiv, searchParams) {
        // console.log('shop.searchProducts');
        // console.log(searchParams);

        var objContainer = jQuery('#')

        jQuery.ajax({
            type: "POST",
            url: '/api/product/search',
            data: JSON.stringify(searchParams),
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function (response) {
                // console.log(response);
                container.html(''); // clear container

                response.data.forEach(function (product) {
                    shop.displayProduct(container, templateDiv, product);
                });
            }
        });

        var product = {
            id: 10,
            name: 'Super product',
            imageUrl: 'zorrro',
            price: {
                price: 100,
                currency: 'EUR'
            }
        };

    },

    displayProduct : function (container, templateDiv, product) {
        // New product
        var newDiv = templateDiv.clone();
        newDiv.attr('id', newDiv.attr('id') + '-' + product.id);
        newDiv.css('display', 'inline-block');

        // Set title
        var prdTitle = newDiv.find('strong');
        prdTitle.attr('id', prdTitle.attr('id') + '-' + product.id);
        prdTitle.html(product.name);

        // Set imgage
        var prdImage = newDiv.find('img');
        prdImage.attr('id', prdImage.attr('id') + '-' + product.id);
        prdImage.attr('src', product.imageUrl);

        // Set price
        var prdPrice = newDiv.find('div span');
        prdPrice.attr('id', prdPrice.attr('id') + '-' + product.id);
        prdPrice.html(product.price.price + ' ' + product.price.currency);

        container.append(newDiv);
        // console.log(newDiv);
    }
};
