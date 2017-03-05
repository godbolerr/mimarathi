(function() {
    'use strict';

    angular
        .module('mimarathiApp')
        .controller('MwordMDetailController', MwordMDetailController);

    MwordMDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Mword'];

    function MwordMDetailController($scope, $rootScope, $stateParams, previousState, entity, Mword) {
        var vm = this;

        vm.mword = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('mimarathiApp:mwordUpdate', function(event, result) {
            vm.mword = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
