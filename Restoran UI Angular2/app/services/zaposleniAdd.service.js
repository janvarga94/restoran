/**
 * Created by Stefan on 2/25/2017.
 */
"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
require("rxjs/add/operator/do");
require("rxjs/add/operator/catch");
require("rxjs/add/operator/map");
var ZaposleniAddService = (function () {
    function ZaposleniAddService(_http, _notificator, _router) {
        this._http = _http;
        this._notificator = _notificator;
        this._router = _router;
    }
    ZaposleniAddService.prototype.addZaposlen = function (email) {
    };
    return ZaposleniAddService;
}());
exports.ZaposleniAddService = ZaposleniAddService;
//# sourceMappingURL=zaposleniAdd.service.js.map