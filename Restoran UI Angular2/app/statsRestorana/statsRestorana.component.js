"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};
Object.defineProperty(exports, "__esModule", { value: true });
var router_1 = require("@angular/router");
var stat_service_1 = require("./../services/stat.service");
var core_1 = require("@angular/core");
var StatsRestoranaComponent = (function () {
    function StatsRestoranaComponent(_statsService, _router, _activeRoute) {
        this._statsService = _statsService;
        this._router = _router;
        this._activeRoute = _activeRoute;
        this.restoran = 'TODO';
    }
    StatsRestoranaComponent.prototype.ngOnInit = function () {
        var _this = this;
        this._activeRoute.params.subscribe(function (params) {
            var idRestorana = params['idRestorana'];
            _this._statsService.getAllStats(idRestorana).subscribe(function (stats) {
                console.log(stats);
                var poDanuY = stats.poDanu.map(function (x) { return x.zbir; });
                var poDanuX = stats.poDanu.map(function (x) { return new Date(x.vreme).toISOString(); });
                c3.generate({
                    bindto: '#chart',
                    data: {
                        x: 'x',
                        xFormat: '%Y-%m-%dT%H:%M:%S.%LZ',
                        columns: [
                            ['x'].concat(poDanuX),
                            ['posete po danu'].concat(poDanuY)
                        ],
                        type: 'line'
                    },
                    axis: {
                        x: {
                            type: 'timeseries',
                            tick: {
                                format: '%Y-%m-%d'
                            }
                        }
                    }
                });
            });
        });
    };
    return StatsRestoranaComponent;
}());
__decorate([
    core_1.ViewChild('chart'),
    __metadata("design:type", Object)
], StatsRestoranaComponent.prototype, "userProfile", void 0);
StatsRestoranaComponent = __decorate([
    core_1.Component({
        templateUrl: 'app/statsRestorana/statsRestorana.component.html'
    }),
    __metadata("design:paramtypes", [stat_service_1.StatService, router_1.Router, router_1.ActivatedRoute])
], StatsRestoranaComponent);
exports.StatsRestoranaComponent = StatsRestoranaComponent;
//# sourceMappingURL=statsRestorana.component.js.map