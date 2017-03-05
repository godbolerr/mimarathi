(function() {
    'use strict';

    angular
        .module('mimarathiApp')
        .controller('MwordMDeleteController',MwordMDeleteController);

    MwordMDeleteController.$inject = ['$uibModalInstance', 'entity', 'Mword'];

    function MwordMDeleteController($uibModalInstance, entity, Mword) {
        var vm = this;

        vm.mword = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Mword.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
