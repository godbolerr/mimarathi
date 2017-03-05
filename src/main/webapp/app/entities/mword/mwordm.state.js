(function() {
    'use strict';

    angular
        .module('mimarathiApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('mword-m', {
            parent: 'entity',
            url: '/mword-m?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'mimarathiApp.mword.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/mword/mwordsm.html',
                    controller: 'MwordMController',
                    controllerAs: 'vm'
                }
            },
            params: {
                page: {
                    value: '1',
                    squash: true
                },
                sort: {
                    value: 'id,asc',
                    squash: true
                },
                search: null
            },
            resolve: {
                pagingParams: ['$stateParams', 'PaginationUtil', function ($stateParams, PaginationUtil) {
                    return {
                        page: PaginationUtil.parsePage($stateParams.page),
                        sort: $stateParams.sort,
                        predicate: PaginationUtil.parsePredicate($stateParams.sort),
                        ascending: PaginationUtil.parseAscending($stateParams.sort),
                        search: $stateParams.search
                    };
                }],
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('mword');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('mword-m-detail', {
            parent: 'mword-m',
            url: '/mword-m/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'mimarathiApp.mword.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/mword/mwordm-detail.html',
                    controller: 'MwordMDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('mword');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Mword', function($stateParams, Mword) {
                    return Mword.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'mword-m',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('mword-m-detail.edit', {
            parent: 'mword-m-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/mword/mwordm-dialog.html',
                    controller: 'MwordMDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Mword', function(Mword) {
                            return Mword.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('mword-m.new', {
            parent: 'mword-m',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/mword/mwordm-dialog.html',
                    controller: 'MwordMDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                mword: null,
                                meaning: null,
                                wordlength: null,
                                first: null,
                                second: null,
                                third: null,
                                fourth: null,
                                fifth: null,
                                isValid: null,
                                status: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('mword-m', null, { reload: 'mword-m' });
                }, function() {
                    $state.go('mword-m');
                });
            }]
        })
        .state('mword-m.edit', {
            parent: 'mword-m',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/mword/mwordm-dialog.html',
                    controller: 'MwordMDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Mword', function(Mword) {
                            return Mword.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('mword-m', null, { reload: 'mword-m' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('mword-m.delete', {
            parent: 'mword-m',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/mword/mwordm-delete-dialog.html',
                    controller: 'MwordMDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Mword', function(Mword) {
                            return Mword.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('mword-m', null, { reload: 'mword-m' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
