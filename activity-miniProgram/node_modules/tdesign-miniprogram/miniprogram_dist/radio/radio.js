var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
import config from '../common/config';
import { SuperComponent, wxComponent } from '../common/src/index';
import Props from './props';
const { prefix } = config;
const name = `${prefix}-radio`;
let Radio = class Radio extends SuperComponent {
    constructor() {
        super(...arguments);
        this.externalClasses = [
            `${prefix}-class`,
            `${prefix}-class-label`,
            `${prefix}-class-icon`,
            `${prefix}-class-content`,
            `${prefix}-class-border`,
        ];
        this.behaviors = ['wx://form-field'];
        this.relations = {
            '../radio-group/radio-group': {
                type: 'ancestor',
                linked(parent) {
                    if (parent.data.borderless) {
                        this.setData({ borderless: true });
                    }
                },
            },
        };
        this.options = {
            multipleSlots: true,
        };
        this.lifetimes = {
            attached() {
                this.initStatus();
            },
            ready() {
                var _a, _b, _c, _d;
                this.setData({
                    _placement: (_d = (_a = this.data.placement) !== null && _a !== void 0 ? _a : (_c = (_b = this.$parent) === null || _b === void 0 ? void 0 : _b.data) === null || _c === void 0 ? void 0 : _c.placement) !== null && _d !== void 0 ? _d : 'left',
                });
            },
        };
        this.properties = Object.assign(Object.assign({}, Props), { borderless: {
                type: Boolean,
                value: false,
            } });
        this.controlledProps = [
            {
                key: 'checked',
                event: 'change',
            },
        ];
        this.data = {
            prefix,
            classPrefix: name,
            customIcon: false,
            slotIcon: false,
            optionLinked: false,
            iconVal: [],
            _placement: '',
        };
        this.methods = {
            handleTap(e) {
                if (this.data.disabled)
                    return;
                const { target } = e.currentTarget.dataset;
                if (target === 'text' && this.data.contentDisabled)
                    return;
                this.doChange();
            },
            doChange() {
                const { value, checked } = this.data;
                if (this.$parent) {
                    this.$parent.updateValue(value);
                }
                else {
                    this._trigger('change', { checked: !checked });
                }
            },
            initStatus() {
                var _a, _b;
                const { icon } = this.data;
                const isIdArr = Array.isArray(((_a = this.$parent) === null || _a === void 0 ? void 0 : _a.icon) || icon);
                this.setData({
                    customIcon: isIdArr,
                    slotIcon: icon === 'slot',
                    iconVal: isIdArr ? ((_b = this.$parent) === null || _b === void 0 ? void 0 : _b.icon) || icon : [],
                });
            },
            setDisabled(disabled) {
                this.setData({
                    disabled: this.data.disabled || disabled,
                });
            },
        };
    }
};
Radio = __decorate([
    wxComponent()
], Radio);
export default Radio;
