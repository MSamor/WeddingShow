var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
import { SuperComponent, wxComponent } from '../common/src/index';
import config from '../common/config';
import props from './props';
const { prefix } = config;
const name = `${prefix}-checkbox-group`;
let CheckBoxGroup = class CheckBoxGroup extends SuperComponent {
    constructor() {
        super(...arguments);
        this.externalClasses = [`${prefix}-class`];
        this.relations = {
            '../checkbox/checkbox': {
                type: 'descendant',
            },
        };
        this.data = {
            prefix,
            classPrefix: name,
            checkboxOptions: [],
        };
        this.properties = Object.assign(Object.assign({}, props), { borderless: {
                type: Boolean,
                value: false,
            } });
        this.observers = {
            value() {
                this.updateChildren();
            },
            options() {
                this.initWithOptions();
            },
        };
        this.lifetimes = {
            ready() {
                this.setCheckall();
            },
        };
        this.controlledProps = [
            {
                key: 'value',
                event: 'change',
            },
        ];
        this.$checkAll = null;
        this.methods = {
            getChilds() {
                let items = this.$children;
                if (!items.length) {
                    items = this.selectAllComponents(`.${prefix}-checkbox-option`);
                }
                return items || [];
            },
            updateChildren() {
                const items = this.getChilds();
                const { value } = this.data;
                if (items.length > 0) {
                    items.forEach((item) => {
                        !item.data.checkAll &&
                            item.setData({
                                checked: value === null || value === void 0 ? void 0 : value.includes(item.data.value),
                            });
                    });
                    if (items.some((item) => item.data.checkAll)) {
                        this.setCheckall();
                    }
                }
            },
            updateValue({ value, checked, checkAll, indeterminate }) {
                let { value: newValue } = this.data;
                const { max } = this.data;
                const keySet = new Set(this.getChilds().map((item) => item.data.value));
                newValue = newValue.filter((value) => keySet.has(value));
                if (max && checked && newValue.length === max)
                    return;
                if (checkAll) {
                    const items = this.getChilds();
                    newValue =
                        !checked && indeterminate
                            ? items
                                .filter(({ data }) => !(data.disabled && !newValue.includes(data.value)))
                                .map((item) => item.data.value)
                            : items
                                .filter(({ data }) => {
                                if (data.disabled) {
                                    return newValue.includes(data.value);
                                }
                                return checked && !data.checkAll;
                            })
                                .map(({ data }) => data.value);
                }
                else if (checked) {
                    newValue = newValue.concat(value);
                }
                else {
                    const index = newValue.findIndex((v) => v === value);
                    newValue.splice(index, 1);
                }
                this._trigger('change', { value: newValue });
            },
            initWithOptions() {
                const { options, value } = this.data;
                if (!(options === null || options === void 0 ? void 0 : options.length) || !Array.isArray(options))
                    return;
                const checkboxOptions = options.map((item) => {
                    const isLabel = ['number', 'string'].includes(typeof item);
                    return isLabel
                        ? {
                            label: `${item}`,
                            value: item,
                            checked: value === null || value === void 0 ? void 0 : value.includes(item),
                        }
                        : Object.assign(Object.assign({}, item), { checked: value === null || value === void 0 ? void 0 : value.includes(item.value) });
                });
                this.setData({
                    checkboxOptions,
                });
            },
            handleInnerChildChange(e) {
                var _a;
                const { item } = e.target.dataset;
                const { checked } = e.detail;
                const rect = {};
                if (item.checkAll) {
                    rect.indeterminate = (_a = this.$checkAll) === null || _a === void 0 ? void 0 : _a.data.indeterminate;
                }
                this.updateValue(Object.assign(Object.assign(Object.assign({}, item), { checked }), rect));
            },
            setCheckall() {
                const items = this.getChilds();
                if (!this.$checkAll) {
                    this.$checkAll = items.find((item) => item.data.checkAll);
                }
                if (!this.$checkAll)
                    return;
                const { value } = this.data;
                const valueSet = new Set(value.filter((val) => val !== this.$checkAll.data.value));
                const isCheckall = items.every((item) => (item.data.checkAll ? true : valueSet.has(item.data.value)));
                this.$checkAll.setData({
                    checked: valueSet.size > 0,
                    indeterminate: !isCheckall,
                });
            },
        };
    }
};
CheckBoxGroup = __decorate([
    wxComponent()
], CheckBoxGroup);
export default CheckBoxGroup;
