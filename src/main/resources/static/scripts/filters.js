angularApp.filter('prettyJSON', function(){
    function prettyPrintJson(json) {
        return JSON ? JSON.stringify(json, null, '  ') : "Your Browser does not support JSON therefore cannot pretty print";
    }
    return prettyPrintJson;
});