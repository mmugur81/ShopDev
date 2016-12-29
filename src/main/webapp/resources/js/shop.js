
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


