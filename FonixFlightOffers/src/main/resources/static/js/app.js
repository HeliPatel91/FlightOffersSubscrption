var app = angular.module('offerApp',['ngRoute','ngResource'])

app.factory("offerFactory", function ($resource) {
    return $resource('/subscribe', {}, {
        query: {
            method: 'POST',
            params: {},
            isArray: false
        }
    })
});
