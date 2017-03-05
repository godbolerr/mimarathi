(function() {
    'use strict';
    angular
        .module('mimarathiApp')
        .factory('Mword', Mword);

    Mword.$inject = ['$resource'];

    function Mword ($resource) {
        var resourceUrl =  'api/mwords/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();
