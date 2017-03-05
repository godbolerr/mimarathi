(function() {
    'use strict';

    angular
        .module('mimarathiApp')
        .controller('MwordMDialogController', MwordMDialogController);

    MwordMDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Mword'];

    function MwordMDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Mword) {
        var vm = this;

        vm.mword = entity;
        vm.clear = clear;
        vm.save = save;

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.mword.id !== null) {
                Mword.update(vm.mword, onSaveSuccess, onSaveError);
            } else {
                Mword.save(vm.mword, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('mimarathiApp:mwordUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
